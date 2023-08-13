// SPDX-License-Identifier: MIT
pragma solidity ^0.8.9;

interface IAnhydriteGlobal {
    function getMonitoringAddresses() external view returns (uint256[] memory, address[] memory);
    function getMonitoring() external view returns (uint256, address);
    function getPrice(string memory name) external view returns (uint256);
    function getTokenContract(uint256 tokenId) external view returns (address);
    function setTokenURI(uint256 tokenId, string memory newURI) external;
    function safeMint(string memory uri) external;
}

import "@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
import "@openzeppelin/contracts/token/ERC721/extensions/ERC721URIStorage.sol";
import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Burnable.sol";
import "@openzeppelin/contracts/access/Ownable.sol";
import "@openzeppelin/contracts/utils/Counters.sol";

/// @custom:optimization:900000

/// @custom:security-contact support@anh.ink
contract AnhydriteGlobal is ERC721, ERC721Enumerable, ERC721URIStorage, ERC721Burnable, Ownable, IAnhydriteGlobal {
    using Counters for Counters.Counter;

    Counters.Counter private _tokenIdCounter;
    mapping(uint256 => address) private _tokenContracts;
    mapping(string => uint256) private _prices;
    address[] private _monitoring;
    mapping (address => bool) private hasMinted;

    constructor() ERC721("Anhydrite Global", "ANHG") {
        uint256 tokenId = _tokenIdCounter.current();
        _tokenIdCounter.increment();
        _safeMint(msg.sender, tokenId);
        _setTokenURI(tokenId, "");
        _tokenContracts[tokenId] = msg.sender;
    }

    /// @custom:info Special functions of the global contract.

    // Add a new address to the monitoring list.
    function addMonitoringAddress(address newAddress) public onlyOwner {
        _monitoring.push(newAddress);
    }

    // Get the last non-zero monitoring address and its index.
    function getMonitoring() public view override returns (uint256, address) {
        require(_monitoring.length > 0, "Monitoring list is empty");
        for (uint256 i = _monitoring.length; i > 0; i--) {
            if (_monitoring[i - 1] != address(0)) {
                return (i - 1, _monitoring[i - 1]);
            }
        }
        revert("No non-zero addresses found in the monitoring list");
    }

    // Remove an address from the monitoring list by replacing it with the zero address.
    function removeMonitoringAddress(address addressToRemove) public onlyOwner {
        bool found = false;

        // Find the address to be removed and replace it with the zero address.
        for (uint256 i = 0; i < _monitoring.length; i++) {
            if (_monitoring[i] == addressToRemove) {
                _monitoring[i] = address(0);
                found = true;
                break;
            }
        }

        require(found, "Address not found in monitoring list");
    }

    // Get the list of non-zero monitoring addresses and their indices.
    function getMonitoringAddresses() public view returns (uint256[] memory, address[] memory) {
        uint256 count = 0;
        for (uint256 i = 0; i < _monitoring.length; i++) {
            if (_monitoring[i] != address(0)) {
                count++;
            }
        }

        uint256[] memory indices = new uint256[](count);
        address[] memory addresses = new address[](count);

        uint256 index = 0;
        for (uint256 i = 0; i < _monitoring.length; i++) {
            if (_monitoring[i] != address(0)) {
                indices[index] = i;
                addresses[index] = _monitoring[i];
                index++;
            }
        }

        return (indices, addresses);
    }


    function setPrice(string memory name, uint256 count) public onlyOwner {
        _prices[name] = count;
    }

    function getPrice(string memory name) public view returns (uint256) {
        return _prices[name];
    }
    
    function getTokenContract(uint256 tokenId) public view returns (address) {
        return _tokenContracts[tokenId];
    }

    // Owner can change tokenURI of his own token
    function setTokenURI(uint256 tokenId, string memory newURI) public onlyTokenOwner(tokenId) {
        _setTokenURI(tokenId, newURI);
    }

    function safeMint(string memory uri) public onlyServerContract(msg.sender) {
        require(!hasMinted[msg.sender], "This contract has already used safeMint");
        hasMinted[msg.sender] = true;
        uint256 tokenId = _tokenIdCounter.current();
        _tokenIdCounter.increment();
        _safeMint(msg.sender, tokenId);
        _setTokenURI(tokenId, uri);
        _tokenContracts[tokenId] = msg.sender;
    }

    /// @custom:info The following functions are overrides required by Solidity.

    function _beforeTokenTransfer(address from, address to, uint256 tokenId, uint256 batchSize)
        internal
        override(ERC721, ERC721Enumerable)
    {
        super._beforeTokenTransfer(from, to, tokenId, batchSize);
    }

    function _burn(uint256 tokenId) internal override(ERC721, ERC721URIStorage) {
        super._burn(tokenId);
    }

    function tokenURI(uint256 tokenId)
        public
        view
        override(ERC721, ERC721URIStorage)
        returns (string memory)
    {
        return super.tokenURI(tokenId);
    }

    function supportsInterface(bytes4 interfaceId)
        public
        view
        override(ERC721, ERC721Enumerable, ERC721URIStorage)
        returns (bool)
    {
        return super.supportsInterface(interfaceId);
    }


    /// @custom:info Custom modifiers.

    modifier onlyTokenOwner(uint256 tokenId) {
        require(ownerOf(tokenId) == msg.sender, "Not token owner");
        _;
    }

    modifier onlyServerContract(address contractAddress) {
        IERC165 instance = IERC165(contractAddress);
        
        bool supportsERC721 = instance.supportsInterface(type(IERC721).interfaceId);
        bool supportsIServerContract = instance.supportsInterface(type(IServerContract).interfaceId);

        require(supportsERC721 && supportsIServerContract, "The contract does not support required interfaces");
        _;
    }
}

interface IServerContract {
    function setServerIpAddress(bytes4 newIpAddress) external;
    function getServerIpAddress() external view returns (bytes4);

    function setServerPort(uint16 newPort) external;
    function getServerPort() external view returns (uint16);

    function setServerName(string calldata newName) external;
    function getServerName() external view returns (string memory);

    function setServerAddress(string calldata newAddress) external;
    function getServerAddress() external view returns (string memory);
}