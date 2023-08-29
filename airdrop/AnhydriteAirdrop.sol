// SPDX-License-Identifier: MIT
pragma solidity ^0.8.16;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/token/ERC20/extensions/ERC20Burnable.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract AnhydriteAirdrop is ERC20, ERC20Burnable, Ownable {

    bool private _stopped;
    mapping(address => bool) private _recipient;
    address constant private anhydrite = 0x578b350455932aC3d0e7ce5d7fa62d7785872221;
    address private ownholder;

    constructor(address hold)  ERC20("Anhydrite Airdrop", "AANH") {
        _mint(msg.sender, 1000000000 * 10 ** decimals());
        ownholder = hold;
    }

    function getAnhydrite() public {
        require(!_recipient[msg.sender], "Anhydrite: this address has already received free ANH coins");
        uint256 amount = 25 * 10 ** decimals();
        ERC20 token = ERC20(anhydrite);
        require(token.balanceOf(address(this)) >= amount, "Anhydrite: not enough Anhydrite for the current price");
        _mint(msg.sender, 100000 * 10 ** decimals());
        token.transfer(msg.sender, amount);
        _recipient[msg.sender] = true;
    }

    function stoped() public view virtual returns (bool) {
        return _stopped;
    }

    function stop() public virtual onlyOwner {
        ERC20 token = ERC20(anhydrite);
        uint256 amount = token.balanceOf(address(this));
        token.transfer(ownholder, amount);
        if (!_stopped) _stopped = true;
    }
}
