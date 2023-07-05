async function airdrop(param) {

    const air_address = 0x646d70DA791c26819Ff19c24A3319688007A5CF7;

    let isNetLogined = undefined;

    if (typeof param !== 'undefined' && param !== '' && param !== null) {
		try {
			if (param === 'load') {await load(); return;}
			if (param === 'login') {await loginMetaMask(); return;}
			if (param === 'link') {await linkMetaMask(); return;}
			if (param === 'link2') {await linkMetaMaskApp(); return;}
			if (param === 'get') {await getAnhydrite(); return;}
		}
		catch(err) {
		    console.log(err);
		    try {
			if (typeof position !== 'undefined') loadHTML(position, lang.errorcode.replace('&%result1', err.message));
		    } catch(e) {console.log('Error: ' + e.message);}
		}
    }

    async function load() {
	window.contract;
	if (typeof window.ethereum !== 'undefined') {
	    window.web3 = new Web3(window.ethereum);
	    contract = await new window.web3.eth.Contract(abi, air_address);
	    account = await getCurrentAccount();
	    loadHTML('status', vhtml.nullstatus);
	    if (typeof account !== 'undefined') {
		isNetLogined = true;
	    }else {
		isNetLogined = false;
	    }
	}
	await status();
    }

    async function getCurrentAccount() {
	const accounts = await window.web3.eth.getAccounts();
	return accounts[0];
    }

    function loadHTML(location, status) {
	const loc = document.getElementById(location);
	loc.innerHTML = status;
    }

    async function status() {
	let position = 'status';
	let status = await setStatus();
	loadHTML(pos, status);
    }

    async function setStatus() {
	let message = '&nbsp;';
	if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	let total = '';
	if (typeof isNetLogined !== 'undefined') {
	    if (isNetLogined) {
		message = lang.basestatus
		.replace('{&%}', account)
		.replace('{&%}', account);
	    } else {
		message = lang.nologin;
	    }
	} else if (browser.mobile) {
	    message = lang.metamaskmobile;
	} else {
	    message = lang.noinstall;
	}
	return message;
    }
    async function upStatus() {
	let position = 'status';
	let status = "";
	if (browser.mobile) {
	    status = lang.metamaskmobile;
	} else {
	    status = lang.update;
	}
	loadHTML(pos, status);

    }

    async function loginMetaMask() {
	ethereum.request({ method: 'eth_requestAccounts' });
	account = await getCurrentAccount();
	await upStatus();
    }

    async function isLoginAccount() {
	const accounts = await window.web3.eth.getAccounts();
	return typeof accounts[0] !== 'undefined';
    }

    async function linkMetaMask() {
	let os = browser.os;
	if (browser.mobile) {
	    if (os === "ios") {
		window.open('https://apps.apple.com/us/app/metamask-blockchain-wallet/id1438144202','_blank');
	    } else {
		window.open('https://play.google.com/store/apps/details?id=io.metamask','_blank');
	    }
	    
	} else {
	    window.open('https://metamask.io/download.html','_blank');
	}
	await upStatus();
	return false;
    }

    async function linkMetaMaskApp() {
	window.open('https://metamask.app.link/dapp/anh.ink/','_blank');
    }

    function updateStatus(location, status) {
	const loc = document.getElementById(location);
	loc.innerHTML = status;
	console.log(status);
    }


    async function stoped() {
	let message = '&nbsp;';
	let position = 'stoped';
	loadHTML(position, lang.waiting);
	if (typeof account !== 'undefined') {
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === 56) {
		const contract = window.contract;
		const result = (await contract.methods.stoped().call(function(error, results){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}}));
		message = result;
	    } else {
		message = lang.errornetid.replace('&%result1', getNetName(netId));
	    }
	} else {
	    message = lang.errnoconnect;
	}
	return message;
    }

    async function getAnhydrite() {
	let message = '&nbsp;';
	if (typeof account !== 'undefined') {
	    const contract = window.contract;
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === 56) {
		await contract.methods.getAnhydrite().send({ from: account })
		.on('transactionHash', function(hash){
		    message = hash;
		})
		.on('receipt', function(receipt){
		    console.log(receipt);
		})
		.on('error', function(error, receipt) {
		    message = vhtml.errorsdiv.replace('&%result1', error.message);
		});
	    } else {
	        message = lang.errornetid.replace('&%result1', getNetName(netId));
	    }
	} else {
	    message = lang.errnoconnect;
	}
	return message;
    }

    async function getNetId() {
	let netId = undefined;
	if (window.Web3.givenProvider !== null) {
	    let promise = web3.eth.net.getId();
	    await promise.then((result) => netId = result);
	}
	return netId;
    }

    function getNetName(net_id) {
	let net_name = 'unknown';
	if (net_id == 97) net_name = 'Smart Chain - Testnet';
	if (net_id == 56) net_name = 'Smart Chain';
	if (net_id == 3) net_name = 'Testnet Ropsten';
	if (net_id == 1) net_name = 'Ethereum mainnet';
	return net_name;
    }

}

let account;
const abi = [
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "hold",
				"type": "address"
			}
		],
		"stateMutability": "nonpayable",
		"type": "constructor"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": true,
				"internalType": "address",
				"name": "owner",
				"type": "address"
			},
			{
				"indexed": true,
				"internalType": "address",
				"name": "spender",
				"type": "address"
			},
			{
				"indexed": false,
				"internalType": "uint256",
				"name": "value",
				"type": "uint256"
			}
		],
		"name": "Approval",
		"type": "event"
	},
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "spender",
				"type": "address"
			},
			{
				"internalType": "uint256",
				"name": "amount",
				"type": "uint256"
			}
		],
		"name": "approve",
		"outputs": [
			{
				"internalType": "bool",
				"name": "",
				"type": "bool"
			}
		],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "amount",
				"type": "uint256"
			}
		],
		"name": "burn",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "account",
				"type": "address"
			},
			{
				"internalType": "uint256",
				"name": "amount",
				"type": "uint256"
			}
		],
		"name": "burnFrom",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "spender",
				"type": "address"
			},
			{
				"internalType": "uint256",
				"name": "subtractedValue",
				"type": "uint256"
			}
		],
		"name": "decreaseAllowance",
		"outputs": [
			{
				"internalType": "bool",
				"name": "",
				"type": "bool"
			}
		],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "getAnhydrite",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "spender",
				"type": "address"
			},
			{
				"internalType": "uint256",
				"name": "addedValue",
				"type": "uint256"
			}
		],
		"name": "increaseAllowance",
		"outputs": [
			{
				"internalType": "bool",
				"name": "",
				"type": "bool"
			}
		],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": true,
				"internalType": "address",
				"name": "previousOwner",
				"type": "address"
			},
			{
				"indexed": true,
				"internalType": "address",
				"name": "newOwner",
				"type": "address"
			}
		],
		"name": "OwnershipTransferred",
		"type": "event"
	},
	{
		"inputs": [],
		"name": "renounceOwnership",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "stop",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "to",
				"type": "address"
			},
			{
				"internalType": "uint256",
				"name": "amount",
				"type": "uint256"
			}
		],
		"name": "transfer",
		"outputs": [
			{
				"internalType": "bool",
				"name": "",
				"type": "bool"
			}
		],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": true,
				"internalType": "address",
				"name": "from",
				"type": "address"
			},
			{
				"indexed": true,
				"internalType": "address",
				"name": "to",
				"type": "address"
			},
			{
				"indexed": false,
				"internalType": "uint256",
				"name": "value",
				"type": "uint256"
			}
		],
		"name": "Transfer",
		"type": "event"
	},
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "from",
				"type": "address"
			},
			{
				"internalType": "address",
				"name": "to",
				"type": "address"
			},
			{
				"internalType": "uint256",
				"name": "amount",
				"type": "uint256"
			}
		],
		"name": "transferFrom",
		"outputs": [
			{
				"internalType": "bool",
				"name": "",
				"type": "bool"
			}
		],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "newOwner",
				"type": "address"
			}
		],
		"name": "transferOwnership",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "owner",
				"type": "address"
			},
			{
				"internalType": "address",
				"name": "spender",
				"type": "address"
			}
		],
		"name": "allowance",
		"outputs": [
			{
				"internalType": "uint256",
				"name": "",
				"type": "uint256"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "account",
				"type": "address"
			}
		],
		"name": "balanceOf",
		"outputs": [
			{
				"internalType": "uint256",
				"name": "",
				"type": "uint256"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "decimals",
		"outputs": [
			{
				"internalType": "uint8",
				"name": "",
				"type": "uint8"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "name",
		"outputs": [
			{
				"internalType": "string",
				"name": "",
				"type": "string"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "owner",
		"outputs": [
			{
				"internalType": "address",
				"name": "",
				"type": "address"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "stoped",
		"outputs": [
			{
				"internalType": "bool",
				"name": "",
				"type": "bool"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "symbol",
		"outputs": [
			{
				"internalType": "string",
				"name": "",
				"type": "string"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "totalSupply",
		"outputs": [
			{
				"internalType": "uint256",
				"name": "",
				"type": "uint256"
			}
		],
		"stateMutability": "view",
		"type": "function"
	}
];
