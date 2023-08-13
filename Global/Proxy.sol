// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/**
 * Enable optimization runs:900000
 */

import "@openzeppelin/contracts@4.6.0/utils/Address.sol";
import "@openzeppelin/contracts@4.6.0/token/ERC20/IERC20.sol";

contract ProxyAnhydrite {

    struct VoteResult {
        address[] isTrue;
        address[] isFalse;
        uint256 timestamp;
    }

    address public implementation;
    bool private stopped = false;

    mapping(address => bool) public owners;
    mapping(address => uint256) public balanceOwner;
    uint public totalOwners;
    
    IERC20 public immutable token;
    uint256 public tokensNeededForOwnership;

    mapping(address => uint256) public initiateOwners;
    mapping(address => bool) private isOwnerVotedOut;
    mapping(address => bool) public blackList;

    address private proposedImplementation;
    VoteResult private votesForNewImplementation;

    bool private proposedStopped = false;
    VoteResult private votesForStopped;

    uint256 private proposedTokensNeeded;
    VoteResult private votesForTokensNeeded;

    address private proposedOwner;
    VoteResult private votesForNewOwner;
    
    address private proposedRemoveOwner;
    VoteResult private votesForRemoveOwner;

    event VotingCompleted(string indexed votingName, address votingSubject, string result, uint votesFor, uint votesAgainst, uint timestamp);
    event VotingCompletedForStopped(bool indexed propose, string result, uint votesFor, uint votesAgainst, uint timestamp);
    event VotingCompletedForNeeded(uint indexed propose, string result, uint votesFor, uint votesAgainst, uint timestamp);

    constructor() {
        implementation = address(0);
        owners[msg.sender] = true;
        totalOwners++;
        token = IERC20(0x578b350455932aC3d0e7ce5d7fa62d7785872221);
        tokensNeededForOwnership = 1 * 10 **18;
    }

    function voteForStopped(bool _proposed, bool vote) public onlyOwner canYouVote(votesForStopped) {
        require(stopped == _proposed, "This vote will not change the Stop status");

        if (_proposed != proposedStopped) {
            proposedStopped = _proposed;
            votesForStopped.timestamp = block.timestamp;
        }

        if (vote) {
            votesForStopped.isTrue.push(msg.sender);
        } else {
            votesForStopped.isFalse.push(msg.sender);
        }

        uint votestrue = votesForStopped.isTrue.length;
        uint votesfalse = votesForStopped.isFalse.length;

        if (votestrue * 100 / totalOwners >= 60) {
            stopped = proposedStopped;
            resetVote(votesForStopped);
            emit VotingCompletedForStopped(proposedStopped, "true", votestrue, votesfalse, block.timestamp);
            
       } else if (votesfalse * 100 / totalOwners > 40) {
            resetVote(votesForStopped);
            emit VotingCompletedForStopped(proposedStopped, "false", votestrue, votesfalse, block.timestamp);
            proposedStopped = !proposedStopped;
        }
        _increaseByOnePercent();
    }

    function voteForNeededForOwnership(uint256 _proposed, bool vote) public onlyOwner canYouVote(votesForTokensNeeded) {
        _proposed = _proposed * 10 ** 18;
        require(proposedTokensNeeded == 0 || proposedTokensNeeded ==  _proposed, "Voting is open for another amount");

        if (proposedTokensNeeded == 0) {
            proposedTokensNeeded = _proposed;
            votesForTokensNeeded.timestamp = block.timestamp;
        }

        if (vote) {
            votesForTokensNeeded.isTrue.push(msg.sender);
        } else {
            votesForTokensNeeded.isFalse.push(msg.sender);
        }

        uint votestrue = votesForTokensNeeded.isTrue.length;
        uint votesfalse = votesForTokensNeeded.isFalse.length;

        if (votestrue * 100 / totalOwners >= 60) {
            tokensNeededForOwnership = proposedTokensNeeded;
            emit VotingCompletedForNeeded(proposedTokensNeeded, "true", votestrue, votesfalse, block.timestamp);
            resetVote(votesForTokensNeeded);
            proposedTokensNeeded = 0;
       } else if (votesfalse * 100 / totalOwners > 40) {
            emit VotingCompletedForNeeded(proposedTokensNeeded, "false", votestrue, votesfalse, block.timestamp);
            resetVote(votesForTokensNeeded);
            proposedTokensNeeded = 0;
        }
        _increaseByOnePercent();
    }

    function voteForNewImplementation(address _implementation, bool vote) public onlyOwner canYouVote(votesForNewImplementation) {
        require(proposedImplementation == address(0) || proposedImplementation ==  _implementation, "Voting for another address is now open");
 
        if (proposedImplementation == address(0)) {
            proposedImplementation = _implementation;
            votesForNewImplementation.timestamp = block.timestamp;
        }

        if (vote) {
            votesForNewImplementation.isTrue.push(msg.sender);
        } else {
            votesForNewImplementation.isFalse.push(msg.sender);
        }

        uint votestrue = votesForNewImplementation.isTrue.length;
        uint votesfalse = votesForNewImplementation.isFalse.length;

        if (votestrue * 100 / totalOwners >= 60) {
            implementation = proposedImplementation;
            resetVote(votesForNewImplementation);
            emit VotingCompleted("Implementation", proposedImplementation, "true", votestrue, votesfalse, block.timestamp);
            proposedImplementation = address(0);
        } else if (votesfalse * 100 / totalOwners > 40) {
            resetVote(votesForNewImplementation);
            emit VotingCompleted("Implementation", proposedImplementation, "false", votestrue, votesfalse, block.timestamp);
            proposedImplementation = address(0);
        }
        _increaseByOnePercent();
    }

    function initiateOwnershipRequest() public {
        require(!owners[msg.sender], "Already an owner");
        require(block.timestamp >= initiateOwners[msg.sender] + 30 days, "Voting is still open");
        require(token.allowance(msg.sender, address(this)) >= tokensNeededForOwnership, "Not enough tokens allowed for transfer");
        require(token.balanceOf(msg.sender) >= tokensNeededForOwnership, "Not enough tokens for transfer");

        initiateOwners[msg.sender] = block.timestamp;
        token.transferFrom(msg.sender, address(this), tokensNeededForOwnership);
        balanceOwner[msg.sender] += tokensNeededForOwnership;

        proposedOwner = msg.sender;
        votesForNewOwner = VoteResult(new address[](0), new address[](0), block.timestamp);
    }

    function voteForNewOwner(address _owner, bool vote) public onlyOwner canYouVote(votesForNewOwner) {
        require(proposedOwner != address(0) && proposedOwner ==  _owner, "There are no votes at this address");

        if (vote) {
            votesForNewOwner.isTrue.push(msg.sender);
        } else {
            votesForNewOwner.isFalse.push(msg.sender);
        }

        uint votestrue = votesForNewOwner.isTrue.length;
        uint votesfalse = votesForNewOwner.isFalse.length;

        if (votestrue * 100 / totalOwners >= 60) {
            owners[proposedOwner] = true;
            totalOwners++;
            resetVote(votesForNewOwner);
            emit VotingCompleted("Add New Owner", proposedOwner, "true", votestrue, votesfalse, block.timestamp);
            proposedOwner = address(0);
        } else if (votesfalse * 100 / totalOwners > 40) {
            token.transfer(proposedOwner, balanceOwner[msg.sender]);
            resetVote(votesForNewOwner);
            emit VotingCompleted("Add New Owner", proposedOwner, "false", votestrue, votesfalse, block.timestamp);
            proposedOwner = address(0);
        }
        _increaseByOnePercent();
    }

    function voteForRemoveOwner(address _owner, bool vote) public onlyOwner canYouVote(votesForRemoveOwner) {
        require(proposedRemoveOwner == address(0) || proposedRemoveOwner ==  _owner, "Voting for another address is now open");

        if (proposedRemoveOwner == address(0)) {
            proposedRemoveOwner = _owner;
            votesForRemoveOwner.timestamp = block.timestamp;
            isOwnerVotedOut[_owner] = true;
        }

        if (vote) {
            votesForRemoveOwner.isTrue.push(msg.sender);
        } else {
            votesForRemoveOwner.isFalse.push(msg.sender);
        }

        uint votestrue = votesForRemoveOwner.isTrue.length;
        uint votesfalse = votesForRemoveOwner.isFalse.length;

        if (votestrue * 100 / totalOwners >= 60) {
            owners[proposedRemoveOwner] = false;
            totalOwners--;
            resetVote(votesForRemoveOwner);
            balanceOwner[msg.sender] = 0;
            isOwnerVotedOut[proposedRemoveOwner] = false;
            blackList[proposedRemoveOwner] = true;
            emit VotingCompleted("Remove Owner", proposedRemoveOwner, "true", votestrue, votesfalse, block.timestamp);
            proposedRemoveOwner = address(0);
        } else if (votesfalse * 100 / totalOwners > 40) {
            owners[proposedRemoveOwner] = false;
            resetVote(votesForRemoveOwner);
            isOwnerVotedOut[_owner] = false;
            emit VotingCompleted("Remove Owner", proposedRemoveOwner, "false", votestrue, votesfalse, block.timestamp);
            proposedRemoveOwner = address(0);
        }
        _increaseByOnePercent();
    }

    function depositTokens(uint256 amount) public onlyOwner {
        require(amount > 0, "Invalid amount");

        token.transferFrom(msg.sender, address(this), amount);
        balanceOwner[msg.sender] += amount;
    }

    function voluntarilyExit() public onlyOwner {
        require(!isOwnerVotedOut[msg.sender], "You have been voted out");
        isOwnerVotedOut[msg.sender] = true;

        if (balanceOwner[msg.sender] > 0) {
            if(token.balanceOf(address(this)) >= balanceOwner[msg.sender]){
                token.transfer(msg.sender, balanceOwner[msg.sender]);
            } else {
                _executeTransaction("sendTokens(address,uint256)", abi.encode(msg.sender, balanceOwner[msg.sender]));
            }
            balanceOwner[msg.sender] = 0;
        isOwnerVotedOut[msg.sender] = false;
        }

        owners[msg.sender] = false;
        totalOwners--;

        emit VotingCompleted("Voluntarily Exit", msg.sender, "true", 0, 0, block.timestamp);
    }

    function _executeTransaction(string memory _methodName, bytes memory _arguments) private {
        bytes memory callData = abi.encodeWithSignature(_methodName, _arguments);
        (bool success,) = implementation.delegatecall(callData);
        require(success, "Execution failed");
    }



    function addressExists(VoteResult memory addresses, address targetAddress) public pure returns (bool) {
        for (uint256 i = 0; i < addresses.isTrue.length; i++) {
            if (addresses.isTrue[i] == targetAddress) {
                return true;
            }
        }
        for (uint256 i = 0; i < addresses.isFalse.length; i++) {
            if (addresses.isFalse[i] == targetAddress) {
                return true;
            }
        }
        return false;
    }

    function resetVote(VoteResult storage vote) internal {
        vote.isTrue = new address[](0);
        vote.isFalse = new address[](0);
        vote.timestamp = 0;
    }

    function _increaseByOnePercent() private {
        uint256 onePercent = tokensNeededForOwnership * 1 / 100;
        balanceOwner[msg.sender] += onePercent;
    }

    // Functions to close voting if it has been more than 3 days and no decision has been reached

    function closeVoteForStopped() public onlyOwner {
        require(stopped != proposedStopped, "There is no open vote");
        emit VotingCompletedForStopped(proposedStopped, "close", votesForStopped.isTrue.length, votesForStopped.isFalse.length, block.timestamp);
        resetVote(votesForStopped);
        proposedStopped = stopped;
    }

    function closeVoteForTokensNeeded() public onlyOwner {
        require(proposedTokensNeeded != 0, "There is no open vote");
        emit VotingCompletedForNeeded(proposedTokensNeeded, "close", votesForTokensNeeded.isTrue.length, votesForTokensNeeded.isFalse.length, block.timestamp);
        resetVote(votesForTokensNeeded);
        proposedTokensNeeded = 0;
    }

    function closeVoteForNewImplementation() public onlyOwner {
        _closeVote(votesForNewImplementation, proposedImplementation, "Implementation");
    }

    function closeVoteForNewOwner() public onlyOwner {
        token.transfer(proposedOwner, balanceOwner[msg.sender]);
        _closeVote(votesForNewOwner, proposedOwner, "Add New Owner");
    }

    function closeVoteForRemoveOwner() public onlyOwner {
        isOwnerVotedOut[proposedRemoveOwner] = false;
        _closeVote(votesForRemoveOwner, proposedRemoveOwner, "Remove Owner");
    }

    function _closeVote(VoteResult storage vote, address propose, string memory voteName) private canClose(propose, vote.timestamp) {
        emit VotingCompleted(voteName, propose, "close", vote.isTrue.length, vote.isFalse.length, block.timestamp);
        resetVote(vote);
        propose = address(0);
    }

    // Function to get the status of voting for new Tokens Needed
    function getVoteForNewTokensNeeded() public view returns (uint256, uint256, uint256, uint256) {
        return (
            proposedTokensNeeded, 
            votesForNewOwner.isTrue.length, 
            votesForNewOwner.isFalse.length, 
            votesForNewOwner.timestamp
        );
    }

        // Function to get the status of voting for new implementation
    function getVoteForNewImplementationStatus() public view returns (address, uint256, uint256, uint256) {
        return _getVote(votesForNewImplementation, proposedImplementation);
    }

    // Function to get the status of voting for new owner
    function getVoteForNewOwnerStatus() public view returns (address, uint256, uint256, uint256) {
        return _getVote(votesForNewOwner, proposedOwner);
    }

    // Function to get the status of voting for remove owner
    function getVoteForRemoveOwnerStatus() public view returns (address, uint256, uint256, uint256) {
        return _getVote(votesForRemoveOwner, proposedRemoveOwner);
    }

    function _getVote(VoteResult memory vote, address addresess) private pure returns (address, uint256, uint256, uint256) {
        return (
            addresess, 
            vote.isTrue.length, 
            vote.isFalse.length, 
            vote.timestamp
        );
    }

    modifier canClose(address addresess, uint256 timestamp) {
        require(addresess != address(0), "There is no open vote");
        require(block.timestamp >= timestamp + 3 days, "Voting is still open");
        _;
    }

    modifier canYouVote(VoteResult memory result) {
        require(!addressExists(result, msg.sender), "Already voted");
        require(balanceOwner[msg.sender] >= tokensNeededForOwnership, "Insufficient tokens in staking balance");
        _;
    }

    modifier onlyOwner() {
        require(owners[msg.sender], "Not an owner");
        require(!isOwnerVotedOut[msg.sender], "This owner is being voted out");
        _;
    }

    fallback() external payable {
        require(!stopped, "Contract is currently stopped.");
        address _impl = implementation;
        require(_impl != address(0), "Implementation == address(0)");

        assembly {
            let ptr := mload(0x40)
            calldatacopy(ptr, 0, calldatasize())
            let result := delegatecall(gas(), _impl, ptr, calldatasize(), 0, 0)
            let size := returndatasize()
            returndatacopy(ptr, 0, size)

            switch result
            case 0 { revert(ptr, size) }
            default { return(ptr, size) }
        }
    }
    
    receive() external payable {
        Address.sendValue(payable(address(token)), msg.value);
    }

}
