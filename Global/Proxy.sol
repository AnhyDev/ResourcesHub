// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/utils/Strings.sol";
import "@openzeppelin/contracts@4.6.0/utils/Address.sol";
import "@openzeppelin/contracts@4.6.0/token/ERC20/IERC20.sol";

contract Proxy {

    struct VoteResult {
        address[] isTrue;
        address[] isFalse;
        uint256 timestamp;
    }

    address public implementation;
    bool private stopped = false;

    mapping(address => bool) public owners;
    uint public totalOwners;
    mapping(address => uint256) public balanceOwner;
    IERC20 public immutable token;
    uint256 public tokensNeededForOwnership;
    mapping(address => uint256) public initiateOwners;
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
    mapping(address => bool) private isOwnerVotedOut;


    constructor() {
        implementation = address(0);
        owners[msg.sender] = true;
        totalOwners++;
        token = IERC20(0x578b350455932aC3d0e7ce5d7fa62d7785872221);
        tokensNeededForOwnership = 1 * 10 **18;
    }

    function voteForStopped(bool _proposed, bool vote) public canYouVote(votesForStopped) {
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

        string memory isstop = "false";
        if (proposedStopped) isstop = "true";
        if (votestrue * 100 / totalOwners >= 60) {
            stopped = proposedStopped;
            resetVote(votesForStopped);
            emit VotingCompleted("Votes For Stopped", isstop, "true", votestrue, votesfalse, block.timestamp);
            
       } else if (votesfalse * 100 / totalOwners > 40) {
            resetVote(votesForStopped);
            emit VotingCompleted("Votes For Stopped", isstop, "false", votestrue, votesfalse, block.timestamp);
            proposedStopped = !proposedStopped;
        }
    }

    function voteForNeededForOwnership(uint256 _proposed, bool vote) public canYouVote(votesForTokensNeeded) {
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
            resetVote(votesForTokensNeeded);
            emit VotingCompleted("Tokens Needed For Ownership", Strings.toString(proposedTokensNeeded), "true", votestrue, votesfalse, block.timestamp);
            proposedTokensNeeded = 0;
       } else if (votesfalse * 100 / totalOwners > 40) {
            resetVote(votesForTokensNeeded);
            emit VotingCompleted("Tokens Needed For Ownership", Strings.toString(proposedTokensNeeded), "false", votestrue, votesfalse, block.timestamp);
            proposedTokensNeeded = 0;
        }
    }

    function voteForNewImplementation(address _implementation, bool vote) public canYouVote(votesForNewImplementation) {
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
            emit VotingCompleted("Implementation", addressToString(proposedImplementation), "true", votestrue, votesfalse, block.timestamp);
            proposedImplementation = address(0);
        } else if (votesfalse * 100 / totalOwners > 40) {
            resetVote(votesForNewImplementation);
            emit VotingCompleted("Implementation", addressToString(proposedImplementation), "false", votestrue, votesfalse, block.timestamp);
            proposedImplementation = address(0);
        }
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

    function voteForNewOwner(address _owner, bool vote) public canYouVote(votesForNewOwner) {
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
            emit VotingCompleted("Add New Owner", addressToString(proposedOwner), "true", votestrue, votesfalse, block.timestamp);
            proposedOwner = address(0);
        } else if (votesfalse * 100 / totalOwners > 40) {
            token.transfer(proposedOwner, balanceOwner[msg.sender]);
            resetVote(votesForNewOwner);
            emit VotingCompleted("Add New Owner", addressToString(proposedOwner), "false", votestrue, votesfalse, block.timestamp);
            proposedOwner = address(0);
        }
    }

    function voteForRemoveOwner(address _owner, bool vote) public canYouVote(votesForRemoveOwner) {
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
            token.transfer(proposedRemoveOwner, balanceOwner[msg.sender]);
            isOwnerVotedOut[proposedRemoveOwner] = false;
            blackList[proposedRemoveOwner] = true;
            emit VotingCompleted("Remove Owner", addressToString(proposedRemoveOwner), "true", votestrue, votesfalse, block.timestamp);
            proposedRemoveOwner = address(0);
        } else if (votesfalse * 100 / totalOwners >= 40) {
            owners[proposedRemoveOwner] = false;
            resetVote(votesForRemoveOwner);
            isOwnerVotedOut[_owner] = false;
            emit VotingCompleted("Remove Owner", addressToString(proposedRemoveOwner), "false", votestrue, votesfalse, block.timestamp);
            proposedRemoveOwner = address(0);
        }
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

    fallback() external payable {
        require(!stopped, "Contract is currently stopped.");
        address _impl = implementation;
        require(_impl != address(0));

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

    // Functions to close voting if it has been more than 3 days and no decision has been reached

    function closeVoteForNewImplementation() public canClose(proposedImplementation, votesForNewImplementation.timestamp) {
        emit VotingCompleted("Implementation", Strings.toString(proposedTokensNeeded), "close", votesForNewImplementation.isTrue.length, votesForNewImplementation.isFalse.length, block.timestamp);
        resetVote(votesForNewImplementation);
        proposedImplementation = address(0);
    }

    function closeVoteForForTokensNeeded() public {
        require(proposedTokensNeeded == 0, "There is no open vote");
        require(block.timestamp >= votesForTokensNeeded.timestamp + 3 days, "Voting is still open");
        emit VotingCompleted("Implementation", addressToString(proposedImplementation), "close", votesForTokensNeeded.isTrue.length, votesForTokensNeeded.isFalse.length, block.timestamp);
        resetVote(votesForTokensNeeded);
        proposedTokensNeeded = 0;
    }

    function closeVoteForNewOwner() public canClose(proposedOwner, votesForNewOwner.timestamp) {
        token.transfer(proposedOwner, balanceOwner[msg.sender]);
        emit VotingCompleted("Add New Owner", addressToString(proposedRemoveOwner), "close", votesForNewOwner.isTrue.length, votesForNewOwner.isFalse.length, block.timestamp);
        resetVote(votesForNewOwner);
        proposedOwner = address(0);
    }

    function closeVoteForRemoveOwner() public canClose(proposedRemoveOwner, votesForRemoveOwner.timestamp) {
        isOwnerVotedOut[proposedRemoveOwner] = false;
        emit VotingCompleted("Remove Owner", addressToString(proposedRemoveOwner), "close", votesForRemoveOwner.isTrue.length, votesForRemoveOwner.isFalse.length, block.timestamp);
        resetVote(votesForRemoveOwner);
        proposedRemoveOwner = address(0);
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

    function addressToString(address _addr) public pure returns(string memory) {
        bytes32 value = bytes32(uint256(uint160(_addr)));
        return Strings.toHexString(uint256(value));
    }

    modifier canClose(address addresess, uint256 timestamp) {
        require(addresess != address(0), "There is no open vote");
        require(block.timestamp >= timestamp + 3 days, "Voting is still open");
        _;
    }

    modifier canYouVote(VoteResult memory result) {
        require(owners[msg.sender], "Not an owner");
        require(!isOwnerVotedOut[msg.sender], "This owner is being voted out");
        require(!addressExists(result, msg.sender), "Already voted");
        _;
    }

    event VotingCompleted(string indexed votingName, string votingSubject, string result, uint votesFor, uint votesAgainst, uint timestamp);
}
