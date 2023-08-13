// SPDX-License-Identifier: All rights reserved
// Code pertaining to the Anhydrite project – anything not related to the OpenZeppelin library – is protected.
// Use, copying, or modification without proper attribution to the Anhydrite project and a link to https://anh.ink is expressly prohibited.

/// @custom:optimization 200
pragma solidity ^0.8.9;

interface IServicesAndNFTSales {

    enum ServiceType {
        Permanent,
        OneTime,
        TimeBased,
        NFT,
        ERC20,
        Sentinel //This is the sentinel value
    }

    event ServicePurchased(address indexed purchaser, ServiceType serviceType, uint256 serviceId);

    function addServicePermanent(string memory name, uint256 payAmount) external;
    function addServicePermanentWithTokens(string memory name, address payAddress, uint256 payAmount) external;
    function addServiceOneTime(string memory name, uint256 payAmount) external;
    function addServiceOneTimeWithTokens(string memory name, address payAddress, uint256 payAmount) external;
    function addServiceTimeBased(string memory name, uint256 payAmount, uint256 duration) external;
    function addServiceTimeBasedWithTokens(string memory name, address payAddress, uint256 payAmount, uint256 duration) external;
    function addServiceNFT(string memory name, uint256 payAmount, address tokenAddress, uint256 number) external;
    function addServiceNFTWithTokens(string memory name, address payAddress, uint256 payAmount, address tokenAddress, uint256 number) external;
    function addServiceERC20(string memory name, uint256 payAmount, address tokenAddress, uint256 number) external;
    function addServiceERC20WithTokens(string memory name, address payAddress, uint256 payAmount, address tokenAddress, uint256 number) external;
    function removeServiceFromList(uint256 serviceId, string memory serviceName) external;
    function buyService(uint256 serviceId, string memory serviceName) external payable;
    function buyServiceWithTokens(uint256 serviceId, string memory serviceName) external;
    function buyNFT(string memory serviceName) external payable;
    function buyNFTWithTokens(string memory serviceName) external;
    function buyListedNFT(string memory serviceName) external payable;
    function buyListedNFTWithTokens(string memory serviceName) external;
    function buyListedERC20(string memory serviceName) external payable;
    function buyListedERC20WithTokens(string memory serviceName) external;
}

import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
import "@openzeppelin/contracts/token/ERC20/IERC20.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

abstract contract ServicesAndNFTSales is IServicesAndNFTSales, ERC721Enumerable, Ownable {

    struct Price {
        address tokenAddress;
        uint256 amount;
    }

    struct Service {
        bytes32 hash;
        string name;
        Price price;
        uint256 timestamp;
        uint256 duration;
        address tokenAddress;
        uint256 number;
    }

    mapping(uint256 => Service[]) public services;
    mapping(address => mapping(uint256 => Service[])) public userPurchasedServices;

    /// @custom:info Special functions of the server contract.

    function addServicePermanent(string memory name, uint256 payAmount) public onlyOwner {
        uint256 serviceId = uint(ServiceType.Permanent);
        _addService(serviceId, name, address(0), payAmount, 0, address(0), 0);
    }

    function addServicePermanentWithTokens(string memory name, address payAddress, uint256 payAmount) public onlyOwner {
        uint256 serviceId = uint(ServiceType.Permanent);
        _addService(serviceId, name, payAddress, payAmount, 0, address(0), 0);
    }

    function addServiceOneTime(string memory name, uint256 payAmount) public onlyOwner {
        uint256 serviceId = uint(ServiceType.OneTime);
        _addService(serviceId, name, address(0), payAmount, 0, address(0), 0);
    }

    function addServiceOneTimeWithTokens(string memory name, address payAddress, uint256 payAmount) public onlyOwner {
        uint256 serviceId = uint(ServiceType.OneTime);
        _addService(serviceId, name, payAddress, payAmount, 0, address(0), 0);
    }

    function addServiceTimeBased(string memory name, uint256 payAmount, uint256 duration) public onlyOwner {
        uint256 serviceId = uint(ServiceType.TimeBased);
        _addService(serviceId, name, address(0), payAmount, duration, address(0), 0);
    }

    function addServiceTimeBasedWithTokens(string memory name, address payAddress, uint256 payAmount, uint256 duration) public onlyOwner {
        uint256 serviceId = uint(ServiceType.TimeBased);
        _addService(serviceId, name, payAddress, payAmount, duration, address(0), 0);
    }

    function addServiceNFT(string memory name, uint256 payAmount, address tokenAddress, uint256 number) public onlyOwner {
        uint256 serviceId = uint(ServiceType.NFT);
        _addService(serviceId, name, address(0), payAmount, 0, tokenAddress, number);
    }

    function addServiceNFTWithTokens(string memory name, address payAddress, uint256 payAmount, address tokenAddress, uint256 number) public onlyOwner {
        uint256 serviceId = uint(ServiceType.NFT);
        _addService(serviceId, name, payAddress, payAmount, 0, tokenAddress, number);
    }

    function addServiceERC20(string memory name, uint256 payAmount, address tokenAddress, uint256 number) public onlyOwner {
        uint256 serviceId = uint(ServiceType.ERC20);
        _addService(serviceId, name, address(0), payAmount, 0, tokenAddress, number);
    }

    function addServiceERC20WithTokens(string memory name, address payAddress, uint256 payAmount, address tokenAddress, uint256 number) public onlyOwner {
        uint256 serviceId = uint(ServiceType.ERC20);
        _addService(serviceId, name, payAddress, payAmount, 0, tokenAddress, number);
    }

    function removeServiceFromList(uint256 serviceId, string memory serviceName) public onlyOwner {
        _removeServiceFromList(serviceId, serviceName);
    }

    function buyService(uint256 serviceId, string memory serviceName) public payable validServiceId(serviceId) {
        Service memory service = _getServiceByNameAndType(serviceId, serviceName);
        require(service.price.tokenAddress == address(0), "This service is sold for tokens");
        _buyService(service, serviceId);
    }

    function buyServiceWithTokens(uint256 serviceId, string memory serviceName) public validServiceId(serviceId) {
        Service memory service = _getServiceByNameAndType(serviceId, serviceName);
        _buyService(service, serviceId);
    }

    function buyNFT(string memory serviceName) public payable {
        _buyNFT(serviceName);
    }

    function buyNFTWithTokens(string memory serviceName) public {
        _buyNFT(serviceName);
    }
    
    function buyListedNFT(string memory serviceName) public payable {
        _buyListedNFT(serviceName);
    }
    
    function buyListedNFTWithTokens(string memory serviceName) public {
        _buyListedNFT(serviceName);
    }
    
    function buyListedERC20(string memory serviceName) public payable {
        _buyListedERC20(serviceName);
    }

    function buyListedERC20WithTokens(string memory serviceName) public {
        _buyListedERC20(serviceName);
    }

    function getServiceByNameAndType(uint256 serviceId, string memory serviceName) public view returns (Service memory) {
        return _getServiceByNameAndType(serviceId, serviceName);
    }

    function _buyNFT(string memory serviceName) internal {
        require(totalSupply() > 0, "Token with ID 0 has already been minted");
        uint256 serviceId = uint(ServiceType.NFT);
        Service memory service = _getServiceByNameAndType(serviceId, serviceName);
        _buyService(service, serviceId);
        _newMint(msg.sender, tokenURI(0));
    }

    function _buyListedERC20(string memory serviceName) internal {
        uint256 serviceId = uint(ServiceType.ERC20);
        Service memory service = _getServiceByNameAndType(serviceId, serviceName);
        uint256 amount = service.number;
        IERC20 erc20Token = IERC20(service.tokenAddress);
        require(erc20Token.balanceOf(address(this)) >= amount, "Not enough tokens in contract balance");

        _buyService(service, serviceId);
        erc20Token.transfer(msg.sender, amount);
    }
    
    function _buyListedNFT(string memory serviceName) private {
        uint256 serviceId = uint(ServiceType.NFT);
        Service memory service = _getServiceByNameAndType(serviceId, serviceName);
        uint256 idNft = service.number;
        ERC721 token = _listedNFT(idNft, service);

        _buyService(service, serviceId);
        token.safeTransferFrom(address(this), msg.sender, idNft);
        
        _removeServiceFromList(serviceId, serviceName);
    }

    function _getServiceByNameAndType(uint256 serviceId, string memory serviceName)internal view returns (Service memory) {
        bytes32 serviceNameHash = keccak256(abi.encodePacked(serviceName));
        Service[] memory serviceArray = services[serviceId];
        for(uint i = 0; i < serviceArray.length; i++) {
            if(serviceArray[i].hash == serviceNameHash) {
                return serviceArray[i];
            }
        }
        revert("Service not found");
    }

    function _purchaseService(address sender, Service memory service, uint256 serviceId) private {
        userPurchasedServices[sender][serviceId].push(service);

        emit ServicePurchased(sender, ServiceType(serviceId), serviceId);
    }
    
    function _buyService(Service memory service, uint256 serviceId) internal validServiceId(serviceId) {
        uint256 paymentAmount = service.price.amount;
        address paymentToken = service.price.tokenAddress;
        if(paymentToken == address(0)) {
            // If paying with ether
            require(msg.value == paymentAmount, "The amount of ether sent does not match the required amount.");
        } else {
            // If paying with tokens
            IERC20 token = IERC20(paymentToken);
            require(token.balanceOf(msg.sender) >= paymentAmount, "Your token balance is not enough.");
            require(token.allowance(msg.sender, address(this)) >= paymentAmount, "The contract does not permit the transfer of tokens on behalf of the user.");
            token.transferFrom(msg.sender, address(this), paymentAmount);
        }

        _purchaseService(msg.sender, service, serviceId);
    }

    function _removeServiceFromList(uint256 serviceId, string memory serviceName) private {     
        // Find the service index and remove it
        for (uint256 i = 0; i < services[serviceId].length; i++) {
            if (keccak256(abi.encodePacked(services[serviceId][i].name)) == keccak256(abi.encodePacked(serviceName))) {
                require(i < services[serviceId].length, "Index out of bounds");

                // Move the last element to the spot at index
                services[serviceId][i] = services[serviceId][services[serviceId].length - 1];
        
                // Remove the last element
                services[serviceId].pop();
                break;
            }
        }
    }
    
    function _listedNFT(uint256 idNft, Service memory service) private view returns (ERC721 returnedToken) {
        address tokenAddress = service.tokenAddress;
        require(service.price.tokenAddress != address(0), "This service is sold for tokens");
        require(tokenAddress != address(0), "This service has no NFTs for sale");
        require(service.number == idNft, "NFT with such id is not for sale");
        ERC721 tokenInstance = ERC721(tokenAddress);
        require(tokenInstance.ownerOf(idNft) == address(this), "Token is not owned by this contract");
        return tokenInstance;
    }

    function _addService(uint256 serviceId, string memory name, address payAddress, uint256 payAmount, uint256 duration,
        address tokenAddress, uint256 number) private {
        services[serviceId].push(Service({
            hash: keccak256(abi.encodePacked(name)),
            name: name,
            price: Price({
                tokenAddress: payAddress,
                amount: payAmount
            }),
            timestamp: block.timestamp,
            duration: duration,
            tokenAddress: tokenAddress,
            number: number
        }));
    }

    function _newMint(address to, string memory uri) internal virtual;
    
    modifier validServiceId(uint256 serviceId) {
        require(serviceId < uint(ServiceType.Sentinel), "Invalid service type");
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

import "@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "@openzeppelin/contracts/token/ERC721/IERC721Receiver.sol";
import "@openzeppelin/contracts/token/ERC721/extensions/ERC721URIStorage.sol";
import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Burnable.sol";
import "@openzeppelin/contracts/utils/Counters.sol";

/// @custom:security-contact support@anh.ink
contract AnhydriteMinecraftServer is ERC721, ERC721URIStorage, ERC721Burnable, IERC721Receiver, IServerContract, ServicesAndNFTSales {
    using Counters for Counters.Counter;

    Counters.Counter private _tokenIdCounter;
    bytes4 private _serverIpAddress;
    uint16 private _serverPort;
    string private _serverName;
    string private _serverAddress;

    IAnhydriteGlobal private immutable _proxyContract;
    IERC20 public immutable _tokenAnhydrite;
    IERC20 public _tokenServer;

    constructor(address proxyContractAddress) ERC721("Anhydrite Minecraft Server", "ANHMC") {
        _proxyContract = IAnhydriteGlobal(proxyContractAddress);
        _tokenAnhydrite = IERC20(0x578b350455932aC3d0e7ce5d7fa62d7785872221);
        //_newMint(msg.sender, "");
        _proxyContract.safeMint("");
    }

    receive() external payable { }
    fallback() external payable { }

    function _newMint(address to, string memory uri) internal override {
        uint256 tokenId = _tokenIdCounter.current();
        _tokenIdCounter.increment();
        _safeMint(to, tokenId);
        _setTokenURI(tokenId, uri);
    }

   /// @notice Функція для передачі Ether
    function withdrawMoney(address payable recipient, uint256 amount) public onlyOwner {
        require(address(this).balance >= amount, "Contract has insufficient balance");
        recipient.transfer(amount);
    }

    /// @notice Функція для передачі ERC20 токенів
    function transferERC20Tokens(address _tokenAddress, address _to, uint256 _amount) public onlyOwner {
        IERC20 token = IERC20(_tokenAddress);
        require(token.balanceOf(address(this)) >= _amount, "Not enough tokens on contract balance");
        token.transfer(_to, _amount);
    }

    /// @notice Функція для передачі ERC721 токенів
    function transferERC721Token(address _tokenAddress, address _to, uint256 _tokenId) public onlyOwner {
        ERC721 token = ERC721(_tokenAddress);
        require(token.ownerOf(_tokenId) == address(this), "The contract is not the owner of this token");
        token.safeTransferFrom(address(this), _to, _tokenId);
    }

    /// @notice Функція, яка отримує список сервісів заданого типу.
    /// @param serviceId ID типу сервісу.
    /// @return Список сервісів заданого типу.
    function getAllServicesByType(uint256 serviceId) public view validServiceId(serviceId) returns (Service[] memory) {
        return services[serviceId];
    }

    /// @notice Функція, яка отримує список сервісів заданого типу, куплених конкретним користувачем.
    /// @param userAddress Адреса користувача.
    /// @param serviceId ID типу сервісу.
    /// @return Список сервісів заданого типу, куплених користувачем.
    function getUserPurchasedServicesByType(address userAddress, uint256 serviceId) public view validServiceId(serviceId) returns (Service[] memory) {
        return userPurchasedServices[userAddress][serviceId];
    }

    /// @custom:info The following functions are overrides IServerContract.

    function setServerIpAddress(bytes4 newIpAddress) external override onlyOwner {
        require(isValidIPv4(newIpAddress), "Invalid IP address");
        _serverIpAddress = newIpAddress;
    }
    
    function setServerIpAddress(string calldata ipString) external onlyOwner {
        require(bytes(ipString).length > 6 && bytes(ipString).length < 16, "Invalid IP string length");
        
        bytes memory ipBytes = bytes(ipString);
        uint8 dotCount = 0;
        bytes memory ipBytesTemp = new bytes(4);
        uint8 currentOctet = 0;
        uint256 j = 0; // position in the ipBytesTemp

        for (uint256 i = 0; i < ipBytes.length; i++) {
            // Перевірка на дійсні символи
            require((ipBytes[i] >= bytes1("0") && ipBytes[i] <= bytes1("9")) || ipBytes[i] == bytes1("."), "Invalid IP character");

            if (ipBytes[i] == bytes1(".")) {
                require(currentOctet <= 255, "Invalid IP octet value");
                require(j < 4, "More octets than expected");  // Make sure we're not exceeding expected array length
                ipBytesTemp[j] = bytes1(currentOctet);
                j++;
                currentOctet = 0;
                dotCount++;
            } else {
                currentOctet = currentOctet * 10 + uint8(ipBytes[i]) - 48;
            }
        }

        require(dotCount == 3, "IP should have exactly 3 dots");
        require(currentOctet <= 255, "Invalid IP octet value");
        ipBytesTemp[3] = bytes1(currentOctet);
        
        bytes4 newIpAddress = bytes4(ipBytesTemp);
        require(isValidIPv4(newIpAddress), "Invalid IP address");

        _serverIpAddress = newIpAddress;
    }

    function isValidIPv4(bytes4 ip) public pure returns (bool) {
        if (ip == bytes4(0)) {
            return false; // 0.0.0.0 is not valid
        }

        uint8 a = uint8(ip[0]);
        uint8 b = uint8(ip[1]);

        // Check for private IP ranges
        if (a == 10) {
            return false; // 10.0.0.0/8
        }
        if (a == 172 && (b >= 16 && b <= 31)) {
            return false; // 172.16.0.0/12
        }
        if (a == 192 && b == 168) {
            return false; // 192.168.0.0/16
        }

        return true; 
    }

    function getServerIpAddress() external view override returns (bytes4) {
        return _serverIpAddress;
    }

    function setServerPort(uint16 newPort) external override onlyOwner {
        _serverPort = newPort;
    }

    function getServerPort() external view override returns (uint16) {
        return _serverPort;
    }

    function setServerName(string calldata newName) external override onlyOwner {
        _serverName = newName;
    }

    function getServerName() external view override returns (string memory) {
        return _serverName;
    }

    function setServerAddress(string calldata newAddress) external override onlyOwner {
        _serverAddress = newAddress;
    }

    function getServerAddress() external view override returns (string memory) {
        return _serverAddress;
    }

    // The following functions are overrides required by Solidity.

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

    function supportsInterface(bytes4 interfaceId) public view override(ERC721, ERC721Enumerable, ERC721URIStorage) returns (bool) {
        return  interfaceId == type(IERC721Receiver).interfaceId ||
                interfaceId == type(IServerContract).interfaceId ||
                super.supportsInterface(interfaceId);
    }

    function onERC721Received(address /* operator */, address /* from */, uint256 /* tokenId */, bytes calldata /* data */) external pure override returns (bytes4) {
        return this.onERC721Received.selector; // повертає магічне значення
    }

}

interface IAnhydriteGlobal {
    function getMonitoringAddresses() external view returns (uint256[] memory, address[] memory);
    function getMonitoring() external view returns (uint256, address);
    function getPrice(string memory name) external view returns (uint256);
    function getTokenContract(uint256 tokenId) external view returns (address);
    function setTokenURI(uint256 tokenId, string memory newURI) external;
    function safeMint(string memory uri) external;
}