// SPDX-License-Identifier: All rights reserved
// Anything related to the Anhydrite project, except for the OpenZeppelin library code, is protected.
// Copying, modifying, or using without proper attribution to the Anhydrite project and a link to https://anh.ink is strictly prohibited.

/// @custom:optimization 200
pragma solidity ^0.8.9;

interface IAnhydriteMonitoring {
    function addServerAddress(address serverAddress) external;
    function removeServerAddress(address serverAddress) external;
    function voteForServer(address serverAddress, uint256 amount) external;
    function blockServer(address serverAddress) external;
    function stopContract() external;
    function getServerVotes(address serverAddress) external view returns (uint256);
    function isServerBlocked(address serverAddress) external view returns (bool, uint256);
}

import "@openzeppelin/contracts/token/ERC20/extensions/ERC20Burnable.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract AnhydriteMonitoring is Ownable, IAnhydriteMonitoring {
    ERC20Burnable public votingToken;

    struct ServerInfo {
        uint256 votes;
        bool isBlocked;
    }

    mapping(address => ServerInfo) public servers;
    IAnhydriteProxy private _proxy;

    bool public isContractStopped = false;

    event Voted(address indexed voter, address indexed serverAddress, uint256 amount);
    event AuthorizedAddressChanged(address indexed targetAddress, bool authorized);
    event ServerBlocked(address indexed serverAddress);
    event ContractStopped();

    constructor(address proxyAddress) {
        require(proxyAddress != address(0), "Invalid proxy address");
        _proxy = IAnhydriteProxy(proxyAddress);
        votingToken = ERC20Burnable(address(_proxy.getToken()));
    }

    function addServerAddress(address serverAddress) external onlyAuthorized notStopped {
        require(serverAddress != address(0), "Invalid server address");
        require(servers[serverAddress].votes == 0, "Server address already added");

        servers[serverAddress] = ServerInfo({
            votes: 1,
            isBlocked: false
        });
    }

    function removeServerAddress(address serverAddress) external onlyAuthorized notStopped {
        require(servers[serverAddress].votes != 0, "Server address not found");

        delete servers[serverAddress];
    }

    function voteForServer(address serverAddress, uint256 amount) external notStopped {
        require(serverAddress != address(0), "Invalid server address");
        require(servers[serverAddress].votes != 0, "Server address not found");
        require(!servers[serverAddress].isBlocked, "Server is blocked");
        require(amount > 0, "Amount should be greater than zero");

        uint256 senderBalance = votingToken.balanceOf(msg.sender);
        require(senderBalance >= amount, "Insufficient token balance");

        uint256 allowance = votingToken.allowance(msg.sender, address(this));
        require(allowance >= amount, "Token allowance too small");

        votingToken.burnFrom(msg.sender, amount);

        servers[serverAddress].votes += amount;

        emit Voted(msg.sender, serverAddress, amount);
    }

    function blockServer(address serverAddress) external onlyOwner notStopped {
        require(!servers[serverAddress].isBlocked, "Server is already blocked");
        servers[serverAddress].isBlocked = true;
        emit ServerBlocked(serverAddress);
    }

    function stopContract() external onlyOwner {
        require(!isContractStopped, "Contract is already stopped");
        isContractStopped = true;
        emit ContractStopped();
    }

    function getServerVotes(address serverAddress) external view returns (uint256) {
        if(servers[serverAddress].isBlocked) {
            return 0;
        }
        return servers[serverAddress].votes;
    }

    function isServerBlocked(address serverAddress) external view returns (bool, uint256) {
        return (servers[serverAddress].isBlocked, servers[serverAddress].votes);
    }

    modifier onlyAuthorized() {
        require(_proxy.getImplementation() == msg.sender, "Not authorized");
        _;
    }

    modifier notStopped() {
        require(!isContractStopped, "Contract is stopped");
        _;
    }
}

interface IAnhydriteProxy {
    function getToken() external returns (IERC20);
    function getImplementation() external returns (address);
    function isStopped() external returns (bool);
    function getTotalOwners() external returns (uint256);
    function isOwner(address account) external returns (bool);
    function getBalanceOwner(address owner) external returns (uint256);
    function getTokensNeededForOwnership() external returns (uint256);
    function isBlacklisted(address account) external returns (bool);
}
