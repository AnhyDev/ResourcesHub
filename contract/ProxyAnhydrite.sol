// SPDX-License-Identifier: MIT
/// @custom:optimization 900000
pragma solidity ^0.8.0;

import "@openzeppelin/contracts@4.6.0/utils/Address.sol";
import "@openzeppelin/contracts@4.6.0/token/ERC20/IERC20.sol";

/// @custom:security-contact support@anh.ink
contract AnhydriteProxy {

    struct VoteResult {
        address[] isTrue;
        address[] isFalse;
        uint256 timestamp;
    }

    address public _implementation;
    bool private _stopped = false;

    mapping(address => bool) public _owners;
    mapping(address => uint256) public _balanceOwner;
    uint public _totalOwners;
    
    IERC20 public immutable _token;
    uint256 public _tokensNeededForOwnership;

    mapping(address => uint256) public _initiateOwners;
    mapping(address => bool) private _isOwnerVotedOut;
    mapping(address => bool) public _blackList;

    address private _proposedImplementation;
    VoteResult private _votesForNewImplementation;

    bool private _proposedStopped = false;
    VoteResult private _votesForStopped;

    uint256 private _proposedTokensNeeded;
    VoteResult private _votesForTokensNeeded;

    address private _proposedOwner;
    VoteResult private _votesForNewOwner;
    
    address private _proposedRemoveOwner;
    VoteResult private _votesForRemoveOwner;

    event VotingCompleted(string indexed votingName, address votingSubject, string result, uint votesFor, uint votesAgainst, uint timestamp);
    event VotingCompletedFor_stopped(bool indexed propose, string result, uint votesFor, uint votesAgainst, uint timestamp);
    event VotingCompletedForNeeded(uint indexed propose, string result, uint votesFor, uint votesAgainst, uint timestamp);

    constructor() {
        _implementation = address(0);
        _owners[msg.sender] = true;
        _totalOwners++;
        _token = IERC20(0x578b350455932aC3d0e7ce5d7fa62d7785872221);
        _tokensNeededForOwnership = 1 * 10 **18;
    }
    

    /// @custom:info Functions that do not change the blockchain.

    function getImplementation() public view returns (address) {
        return _implementation;
    }

    function isOwner(address account) public view returns (bool) {
        return _owners[account];
    }

    function getBalanceOwner(address owner) public view returns (uint256) {
        return _balanceOwner[owner];
    }

    function getTotalOwners() public view returns (uint) {
        return _totalOwners;
    }

    function getToken() public view returns (IERC20) {
        return _token;
    }

    function getTokensNeededForOwnership() public view returns (uint256) {
        return _tokensNeededForOwnership;
    }

    function getInitiateOwners(address account) public view returns (uint256) {
        return _initiateOwners[account];
    }

    function isBlacklisted(address account) public view returns (bool) {
        return _blackList[account];
    }

    // Function to get the status of voting for new Tokens Needed
    function getVoteForNewTokensNeeded() public view returns (uint256, uint256, uint256, uint256) {
        return (
            _proposedTokensNeeded, 
            _votesForNewOwner.isTrue.length, 
            _votesForNewOwner.isFalse.length, 
            _votesForNewOwner.timestamp
        );
    }

    // Function to get the status of voting for new implementation
    function getVoteForNewImplementationStatus() public view returns (address, uint256, uint256, uint256) {
        return _getVote(_votesForNewImplementation, _proposedImplementation);
    }

    // Function to get the status of voting for new owner
    function getVoteForNewOwnerStatus() public view returns (address, uint256, uint256, uint256) {
        return _getVote(_votesForNewOwner, _proposedOwner);
    }

    // Function to get the status of voting for remove owner
    function getVoteForRemoveOwnerStatus() public view returns (address, uint256, uint256, uint256) {
        return _getVote(_votesForRemoveOwner, _proposedRemoveOwner);
    }

    /// @custom:info Functions that change the blockchain.

    // Function Stopped
    function voteForStopped(bool _proposed, bool vote) public onlyOwner canYouVote(_votesForStopped) {
        require(_stopped != _proposed, "This vote will not change the Stop status");

        if (_proposed != _proposedStopped) {
            _proposedStopped = _proposed;
            _votesForStopped.timestamp = block.timestamp;
        }

        if (vote) {
            _votesForStopped.isTrue.push(msg.sender);
        } else {
            _votesForStopped.isFalse.push(msg.sender);
        }

        uint votestrue = _votesForStopped.isTrue.length;
        uint votesfalse = _votesForStopped.isFalse.length;

        if (votestrue * 100 / _totalOwners >= 60) {
            _stopped = _proposedStopped;
            _resetVote(_votesForStopped);
            emit VotingCompletedFor_stopped(_proposedStopped, "true", votestrue, votesfalse, block.timestamp);
            
       } else if (votesfalse * 100 / _totalOwners > 40) {
            _resetVote(_votesForStopped);
            emit VotingCompletedFor_stopped(_proposedStopped, "false", votestrue, votesfalse, block.timestamp);
            _proposedStopped = !_proposedStopped;
        }
        _increaseByOnePercent();
    }

    /// @custom:info Functions voting

    function voteForNeededForOwnership(uint256 _proposed, bool vote) public onlyOwner canYouVote(_votesForTokensNeeded) {
        _proposed = _proposed * 10 ** 18;
        require(_proposedTokensNeeded == 0 || _proposedTokensNeeded ==  _proposed, "Voting is open for another amount");

        if (_proposedTokensNeeded == 0) {
            _proposedTokensNeeded = _proposed;
            _votesForTokensNeeded.timestamp = block.timestamp;
        }

        if (vote) {
            _votesForTokensNeeded.isTrue.push(msg.sender);
        } else {
            _votesForTokensNeeded.isFalse.push(msg.sender);
        }

        uint votestrue = _votesForTokensNeeded.isTrue.length;
        uint votesfalse = _votesForTokensNeeded.isFalse.length;

        if (votestrue * 100 / _totalOwners >= 60) {
            _tokensNeededForOwnership = _proposedTokensNeeded;
            emit VotingCompletedForNeeded(_proposedTokensNeeded, "true", votestrue, votesfalse, block.timestamp);
            _resetVote(_votesForTokensNeeded);
            _proposedTokensNeeded = 0;
       } else if (votesfalse * 100 / _totalOwners > 40) {
            emit VotingCompletedForNeeded(_proposedTokensNeeded, "false", votestrue, votesfalse, block.timestamp);
            _resetVote(_votesForTokensNeeded);
            _proposedTokensNeeded = 0;
        }
        _increaseByOnePercent();
    }

    function voteForNewImplementation(address implementation_, bool vote) public onlyOwner canYouVote(_votesForNewImplementation) {
        require(_proposedImplementation == address(0) || _proposedImplementation ==  implementation_, "Voting for another address is now open");
 
        if (_proposedImplementation == address(0)) {
            _proposedImplementation = implementation_;
            _votesForNewImplementation.timestamp = block.timestamp;
        }

        if (vote) {
            _votesForNewImplementation.isTrue.push(msg.sender);
        } else {
            _votesForNewImplementation.isFalse.push(msg.sender);
        }

        uint votestrue = _votesForNewImplementation.isTrue.length;
        uint votesfalse = _votesForNewImplementation.isFalse.length;

        if (votestrue * 100 / _totalOwners >= 60) {
            _implementation = _proposedImplementation;
            _resetVote(_votesForNewImplementation);
            emit VotingCompleted("Implementation", _proposedImplementation, "true", votestrue, votesfalse, block.timestamp);
            _proposedImplementation = address(0);
        } else if (votesfalse * 100 / _totalOwners > 40) {
            _resetVote(_votesForNewImplementation);
            emit VotingCompleted("Implementation", _proposedImplementation, "false", votestrue, votesfalse, block.timestamp);
            _proposedImplementation = address(0);
        }
        _increaseByOnePercent();
    }

    function initiateOwnershipRequest() public {
        require(!_owners[msg.sender], "Already an owner");
        require(!_blackList[msg.sender], "This address is blacklisted");
        require(block.timestamp >= _initiateOwners[msg.sender] + 30 days, "Voting is still open");
        require(_token.allowance(msg.sender, address(this)) >= _tokensNeededForOwnership, "Not enough tokens allowed for transfer");
        require(_token.balanceOf(msg.sender) >= _tokensNeededForOwnership, "Not enough tokens for transfer");

        _initiateOwners[msg.sender] = block.timestamp;
        _token.transferFrom(msg.sender, address(this), _tokensNeededForOwnership);
        _balanceOwner[msg.sender] += _tokensNeededForOwnership;

        _proposedOwner = msg.sender;
        _votesForNewOwner = VoteResult(new address[](0), new address[](0), block.timestamp);
    }

    function voteForNewOwner(address _owner, bool vote) public onlyOwner canYouVote(_votesForNewOwner) {
        require(_proposedOwner != address(0) && _proposedOwner ==  _owner, "There are no votes at this address");

        if (vote) {
            _votesForNewOwner.isTrue.push(msg.sender);
        } else {
            _votesForNewOwner.isFalse.push(msg.sender);
        }

        uint votestrue = _votesForNewOwner.isTrue.length;
        uint votesfalse = _votesForNewOwner.isFalse.length;

        if (votestrue * 100 / _totalOwners >= 60) {
            _owners[_proposedOwner] = true;
            _totalOwners++;
            _resetVote(_votesForNewOwner);
            emit VotingCompleted("Add New Owner", _proposedOwner, "true", votestrue, votesfalse, block.timestamp);
            _proposedOwner = address(0);
        } else if (votesfalse * 100 / _totalOwners > 40) {
            _token.transfer(_proposedOwner, _balanceOwner[msg.sender]);
            _resetVote(_votesForNewOwner);
            emit VotingCompleted("Add New Owner", _proposedOwner, "false", votestrue, votesfalse, block.timestamp);
            _proposedOwner = address(0);
        }
        _increaseByOnePercent();
    }

    function voteForRemoveOwner(address _owner, bool vote) public onlyOwner canYouVote(_votesForRemoveOwner) {
        require(_proposedRemoveOwner == address(0) || _proposedRemoveOwner ==  _owner, "Voting for another address is now open");

        if (_proposedRemoveOwner == address(0)) {
            _proposedRemoveOwner = _owner;
            _votesForRemoveOwner.timestamp = block.timestamp;
            _isOwnerVotedOut[_owner] = true;
        }

        if (vote) {
            _votesForRemoveOwner.isTrue.push(msg.sender);
        } else {
            _votesForRemoveOwner.isFalse.push(msg.sender);
        }

        uint votestrue = _votesForRemoveOwner.isTrue.length;
        uint votesfalse = _votesForRemoveOwner.isFalse.length;

        if (votestrue * 100 / _totalOwners >= 60) {
            _owners[_proposedRemoveOwner] = false;
            _totalOwners--;
            _resetVote(_votesForRemoveOwner);
            _balanceOwner[msg.sender] = 0;
            _isOwnerVotedOut[_proposedRemoveOwner] = false;
            _blackList[_proposedRemoveOwner] = true;
            emit VotingCompleted("Remove Owner", _proposedRemoveOwner, "true", votestrue, votesfalse, block.timestamp);
            _proposedRemoveOwner = address(0);
        } else if (votesfalse * 100 / _totalOwners > 40) {
            _owners[_proposedRemoveOwner] = false;
            _resetVote(_votesForRemoveOwner);
            _isOwnerVotedOut[_owner] = false;
            emit VotingCompleted("Remove Owner", _proposedRemoveOwner, "false", votestrue, votesfalse, block.timestamp);
            _proposedRemoveOwner = address(0);
        }
        _increaseByOnePercent();
    }

    function depositTokens(uint256 amount) public onlyOwner {
        require(amount > 0, "Invalid amount");

        _token.transferFrom(msg.sender, address(this), amount);
        _balanceOwner[msg.sender] += amount;
    }

    function voluntarilyExit() public onlyOwner {
        require(!_isOwnerVotedOut[msg.sender], "You have been voted out");
        _isOwnerVotedOut[msg.sender] = true;

        if (_balanceOwner[msg.sender] > 0) {
            if(_token.balanceOf(address(this)) >= _balanceOwner[msg.sender]){
                _token.transfer(msg.sender, _balanceOwner[msg.sender]);
            } else {
                _executeTransaction("sendTokens(address,uint256)", abi.encode(msg.sender, _balanceOwner[msg.sender]));
            }
            _balanceOwner[msg.sender] = 0;
        _isOwnerVotedOut[msg.sender] = false;
        }

        _owners[msg.sender] = false;
        _totalOwners--;

        emit VotingCompleted("Voluntarily Exit", msg.sender, "true", 0, 0, block.timestamp);
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

    /// @custom:info Functions to close voting if it has been more than 3 days and no decision has been reached

    function closeVoteForStopped() public onlyOwner {
        require(_stopped != _proposedStopped, "There is no open vote");
        emit VotingCompletedFor_stopped(_proposedStopped, "close", _votesForStopped.isTrue.length, _votesForStopped.isFalse.length, block.timestamp);
        _resetVote(_votesForStopped);
        _proposedStopped = _stopped;
    }

    function closeVoteForTokensNeeded() public onlyOwner {
        require(_proposedTokensNeeded != 0, "There is no open vote");
        emit VotingCompletedForNeeded(_proposedTokensNeeded, "close", _votesForTokensNeeded.isTrue.length, _votesForTokensNeeded.isFalse.length, block.timestamp);
        _resetVote(_votesForTokensNeeded);
        _proposedTokensNeeded = 0;
    }

    function closeVoteForNewImplementation() public onlyOwner {
        _closeVote(_votesForNewImplementation, _proposedImplementation, "Implementation");
    }

    function closeVoteForNewOwner() public onlyOwner {
        _token.transfer(_proposedOwner, _balanceOwner[msg.sender]);
        _closeVote(_votesForNewOwner, _proposedOwner, "Add New Owner");
    }

    function closeVoteForRemoveOwner() public onlyOwner {
        _isOwnerVotedOut[_proposedRemoveOwner] = false;
        _closeVote(_votesForRemoveOwner, _proposedRemoveOwner, "Remove Owner");
    }

    function _getVote(VoteResult memory vote, address addresess) private pure returns (address, uint256, uint256, uint256) {
        return (
            addresess, 
            vote.isTrue.length, 
            vote.isFalse.length, 
            vote.timestamp
        );
    }

    /// @custom:info Internal and private variables.

    function _resetVote(VoteResult storage vote) internal {
        vote.isTrue = new address[](0);
        vote.isFalse = new address[](0);
        vote.timestamp = 0;
    }

    function _closeVote(VoteResult storage vote, address propose, string memory voteName) private canClose(propose, vote.timestamp) {
        emit VotingCompleted(voteName, propose, "close", vote.isTrue.length, vote.isFalse.length, block.timestamp);
        _resetVote(vote);
        propose = address(0);
    }

    function _executeTransaction(string memory _methodName, bytes memory _arguments) private {
        bytes memory callData = abi.encodeWithSignature(_methodName, _arguments);
        (bool success,) = _implementation.delegatecall(callData);
        require(success, "Execution failed");
    }

    function _increaseByOnePercent() private {
        uint256 onePercent = _tokensNeededForOwnership * 1 / 1000;
        _balanceOwner[msg.sender] += onePercent;
    }

    /// @custom:info Custom modifiers.

    modifier canClose(address addresess, uint256 timestamp) {
        require(addresess != address(0), "There is no open vote");
        require(block.timestamp >= timestamp + 3 days, "Voting is still open");
        _;
    }

    modifier canYouVote(VoteResult memory result) {
        require(!addressExists(result, msg.sender), "Already voted");
        require(_balanceOwner[msg.sender] >= _tokensNeededForOwnership, "Insufficient tokens in staking balance");
        _;
    }

    modifier onlyOwner() {
        require(_owners[msg.sender], "Not an owner");
        require(!_isOwnerVotedOut[msg.sender], "This owner is being voted out");
        _;
    }

    /// @custom:info External variables.

    fallback() external payable {
        require(!_stopped, "Contract is currently _stopped.");
        address _impl = _implementation;
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
        Address.sendValue(payable(address(_token)), msg.value);
    }

}
