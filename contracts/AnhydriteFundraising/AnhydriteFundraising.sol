// SPDX-License-Identifier: MIT
pragma solidity ^0.8.7;

import "@openzeppelin/contracts@4.6.0/token/ERC721/ERC721.sol";
import "@openzeppelin/contracts@4.6.0/access/Ownable.sol";

/// @custom:security-contact support@anh.ink
contract AnhydriteFundraising is ERC721, Ownable {

    struct blocktokens { 
      uint256 tokenId;
      string _uri;
    }

    address constant private anhydrite = 0x578b350455932aC3d0e7ce5d7fa62d7785872221;
    address private recipient;
    address payable private _token_address;

    uint256 private   gold;
    uint256 private silver;
    uint256 private bronze;
    uint256 private copper;

    blocktokens[] private _tokens;

    string private _uri1;
    string private _uri2;
    string private _uri3;
    string private _uri4;

    bool private _stopped;

    event StopAnhydriteFundraising(address indexed sender, bool stopped);

    constructor(address recipient_) ERC721("Anhydrite First Fundraising Campaign", "AFFC") {
        _token_address = payable(address(this));
        recipient = recipient_;

        _uri1 = "ipfs://bafkreiahrn7kxg244pnzm5cv5y7oyja54tyn2f3ao3b62tcxqv44hlj4ru";
        _uri2 = "ipfs://bafkreih6lzetqhsob4yihnxfzpjenx6dbehaobzjddzc2ccrjx26xtj5oe";
        _uri3 = "ipfs://bafkreiasui3jk6dhlljoqa7slb42yriipxastsho3xj4wcd3wkqhi6kthi";
        _uri4 = "ipfs://bafkreigi276ulrie5cajflv7y75tmp5tdsidziny7gxpmgf3zoqeznbpjm";
    
          gold = 5 * 10 ** 19;
        silver = 5 * 10 ** 18;
        bronze = 5 * 10 ** 17;
        copper = 5 * 10 ** 16;

        _tokens.push(blocktokens(1, "ipfs://bafkreif66z2aeoer6lyujghufat3nxtautrza3ucemwcwgfiqpajjlcroy"));
        _safeMint(msg.sender, 1);
    }

    function totalSupply() public view virtual returns (uint256) {
        return _tokens.length;
    }

    function withdraw() public onlyOwner {
        uint amount = address(this).balance;
        payable(recipient).transfer(amount);
    }

    function stoped() public view virtual returns (bool) {
        return _stopped;
    }

    function stop() public virtual onlyOwner {
        ANH_ERC20 token = ANH_ERC20(anhydrite);
        uint256 amount = token.balanceOf(address(this));
        token.transfer(recipient, amount);
        if (!_stopped) _stopped = true;
        emit StopAnhydriteFundraising(msg.sender, _stopped);
    }

    function _giveAnhydrite(address to, uint256 amount) private {
        ANH_ERC20 token = ANH_ERC20(anhydrite);
        require(token.balanceOf(address(this)) > amount, "Anhydrite: not enough Anhydrite for the current price");
        token.transfer(to, amount);
    }

    function tokenURI(uint256 tokenId) public view override(ERC721) returns (string memory) {
        require(_exists(tokenId), "ERC721Metadata: URI query for nonexistent token");
        string memory _uri = _tokens[tokenId - 1]._uri;
        return bytes(_uri).length > 0 ? _uri : "";
    }

    function transferOwnership(address newOwner) public virtual override onlyOwner {
        newOwner = address(0);
        require(newOwner == address(0), "This function is locked");
    }

    function renounceOwnership() public virtual override onlyOwner {
        bool a = false;
        require(a, "This function is locked");
    }

    function _buyAt(uint256 amount) private {
        require(!_stopped, "Anhydrite: First fundraising campaign completed");
        string memory _uri = "";

        if (amount == gold) {
            _uri = _uri1;
        }else if (amount == silver) {
            _uri = _uri2;
        }else if (amount == bronze) {
            _uri = _uri3;
        }else if (amount == copper) {
            _uri = _uri4;
        }

        if (bytes(_uri).length > 0) {
           uint256 tokenIdNew = _tokens.length + 1;
           _tokens.push(blocktokens(tokenIdNew, _uri));
           _safeMint(msg.sender, tokenIdNew);
        }
        _giveAnhydrite(msg.sender, amount * 10000);

    }

    receive() external payable {
        _buyAt(msg.value);
    }

    fallback() external payable {
        _buyAt(msg.value);
    }
}


interface ANH_ERC20 {
    function transfer(address recipient, uint256 amount) external;
    function balanceOf(address account) external view returns (uint256);
}

