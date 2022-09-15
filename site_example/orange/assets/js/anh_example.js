async function anhydrite(param) {

    const prm_address = '0x0CB3765bC673Ecfc55Fa36Ced05aC83572313e21';
    const own_address = '0x6b7A93973d2ba5BE33fB7A9fc8CE19BfA2c93280';

    let isNetLogined = undefined;

    if (typeof param !== 'undefined' && param !== '' && param !== null) {
		try {
			if (param === 'load') {await load(); return;}
			if (param === 'login') {await loginMetaMask(); return;}
			if (param === 'link') {await linkMetaMask(); return;}
			if (param === 'link2') {await linkMetaMaskApp(); return;}
			if (param === 'buyaddress') {await buyTokenAddress(); return;}
			if (param === 'profit') {await getBalance(); return;}
			if (param === 'wprofit') {await withdrawProfit(); return;}
		}
		catch(err) {
		    console.log(err);
		    try {
			if (typeof position !== 'undefined') loadHTML(position, lang.errorcode.replace('&%result1', err.message));
		    } catch(e) {console.log('Error: ' + e.message);}
		}
    }

    async function load() {
	window.contrac;
	if (typeof window.ethereum !== 'undefined') {
	    window.web3 = new Web3(window.ethereum);
	    window.contract = await new window.web3.eth.Contract(abi, prm_address);
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
	loadHTML(position, status);
    }

    async function setStatus() {
	let message = '&nbsp;';
	if (typeof isNetLogined !== 'undefined') {
	    if (isNetLogined) {
		message = lang.basestatus;
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
	status = lang.update;
	loadHTML(position, status);

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

    async function getPrice(contract) {
	let result = 0;
	if (window.Web3.givenProvider !== null) {
	    if (contract !== '') result = await contract.methods.getPrice().call();
	}	
	return result;
    }

	async function buyTokenAddress() {
	    let message = '&nbsp;';
	    let position = 'status';
	    loadHTML(position, lang.waiting);
	    if (typeof account !== 'undefined') {
		    let netId = await getNetId();
		    if (typeof netId !== 'undefined' && netId === 56) {
			let address = own_address;
			const contract = window.contract;
			if (isAddress(address)) {
			    let token = await ownerTokens(contract, address);
			    if (token > 0) {
			        let is = await isBuyAllowed(contract, token);
				    if (!is) token = await minimalTokenAddress(contract, address);
			    }
			    if (token < 1) token = await minimalTokenAddress(contract, address);
			    if (token > 0 && await isBuyAllowed(contract, token)) {
				loadHTML(position, lang.waiting);
				let price = await getPrice(contract);
				await contract.methods.buyTokenID(token).send({ from: account, value: price })
				.on('transactionHash', function(hash){
				    message = lang.resulttxhash.replace('&%result1', 'https://bscscan.com/');
				    message = message.replace('&%result2', hash);
				    loadHTML(position, message);
				})
				.on('receipt', function(receipt){
				    console.log(receipt);
				})
				.on('error', function(error, receipt) {
				    message = vhtml.errorsdiv.replace('&%result1', error.message);
				    loadHTML(position, message);
				});
			    } else {
				message = lang.errtokenperm.replace('&%result1', address);
			    }
			} else {
			    message = lang.errtokenaddr.replace('&%result1', address);
			}
		    } else {
			message = lang.errornetid.replace('&%result1', getNetName(netId));
		    }
	   } else {
		message = lang.errnoconnect;
	   }
		loadHTML(position, message);
	}

    async function getBalance() {
	let message = '&nbsp;';
	const position = 'status';
	loadHTML(position, lang.waiting);
	if (typeof account !== 'undefined') {
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === 56) {
		const contract = window.contract;
		const result = (await contract.methods.weiGetBalance(account).call(function(error, results){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}}));
		message = lang.resultprofit.replace('&%result_', result[0]/1000000000000000000).replace('&%result1', result[1]/1000000000000000000).replaceAll('&%result2', 'BNB');
	    } else {
		message = lang.errornetid.replace('&%result1', getNetName(netId));
	    }
	} else {
	    message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

    async function withdrawProfit(x) {
	let message = '&nbsp;';
	let position = 'status';
	loadHTML(position, lang.waiting);
	if (typeof account !== 'undefined') {
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === 56) {
		const contract = window.contract;
		await contract.methods.withdrawProfit().send({ from: account })
		    .on('transactionHash', function(hash){
			message = lang.resulttxhash.replace('&%result1', 'https://bscscan.com/');
			message = message.replace('&%result2', hash);
			loadHTML(position, message);
		    })
		    .on('receipt', function(receipt){
			console.log(receipt);
		    })
		    .on('error', function(error, receipt) {
			message = vhtml.errorsdiv.replace('&%result1', error.message);
			loadHTML(position, message);
		});
	    } else {
		message = lang.errornetid.replace('&%result1', getNetName(netId));
	    }
	} else {
	    message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

    async function ownerTokens(contract, address) {
	let token = 0;
	let result = await contract.methods.ownerTokens(address).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
	if (result.length > 0) token = result[0];
	return token;
    }

    async function isBuyAllowed(contract, token) {
	let result = false;
	if (typeof contract === 'undefined') contract = window.contract;
	if (contract !== '') result = await contract.methods.isBuyAllowed(token, account).call();
	return result;
    }

    async function minimalTokenAddress(contract, address) {
	let result = 0;
	if (typeof contract === 'undefined') contract = window.contract;
	if (contract !== '') {
	    try {
		result = await contract.methods.minimalTokenContainsAddress(address, account).call();
	    }
	    catch(err) {
		console.log('Error: '+err.message);
	    }
	}
	  return result;
    }

    async function getNetId() {
	let netId = undefined;
	if (window.Web3.givenProvider !== null) {
	    let promise = web3.eth.net.getId();
	    await promise.then((result) => netId = result);
	}
	return netId;
    }

    function isAddress(address) {
	let re = /^0x[a-fA-F0-9]{40}$/
	return re.exec(address);
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
				"internalType": "string",
				"name": "uri_",
				"type": "string"
			}
		],
		"stateMutability": "payable",
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
				"name": "approved",
				"type": "address"
			},
			{
				"indexed": true,
				"internalType": "uint256",
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "Approval",
		"type": "event"
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
				"name": "operator",
				"type": "address"
			},
			{
				"indexed": false,
				"internalType": "bool",
				"name": "approved",
				"type": "bool"
			}
		],
		"name": "ApprovalForAll",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": true,
				"internalType": "address",
				"name": "sender",
				"type": "address"
			},
			{
				"indexed": false,
				"internalType": "bool",
				"name": "isholder",
				"type": "bool"
			}
		],
		"name": "EnableHolder",
		"type": "event"
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
				"indexed": true,
				"internalType": "uint256",
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "Transfer",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": true,
				"internalType": "address",
				"name": "sender",
				"type": "address"
			},
			{
				"indexed": false,
				"internalType": "uint256",
				"name": "profit",
				"type": "uint256"
			}
		],
		"name": "WithdrawProfit",
		"type": "event"
	},
	{
		"stateMutability": "payable",
		"type": "fallback"
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
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "approve",
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
		"inputs": [
			{
				"internalType": "uint256",
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "branchLength",
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
				"internalType": "uint256",
				"name": "tokenId",
				"type": "uint256"
			},
			{
				"internalType": "address",
				"name": "to",
				"type": "address"
			}
		],
		"name": "branchLocation",
		"outputs": [
			{
				"internalType": "uint256[2]",
				"name": "",
				"type": "uint256[2]"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "buyTokenID",
		"outputs": [],
		"stateMutability": "payable",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "getAmount",
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
		"name": "getAnhydrite",
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
		"inputs": [
			{
				"internalType": "uint256",
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "getApproved",
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
		"name": "getMultiply",
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
				"internalType": "uint256",
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "getNextIDs",
		"outputs": [
			{
				"internalType": "uint256[]",
				"name": "",
				"type": "uint256[]"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "uint256",
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "getPreviousID",
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
		"name": "getPrice",
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
				"name": "to",
				"type": "address"
			}
		],
		"name": "getTokensContainsAddress",
		"outputs": [
			{
				"internalType": "uint256[]",
				"name": "",
				"type": "uint256[]"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "to",
				"type": "address"
			}
		],
		"name": "holder_Is",
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
		"name": "holder_Switch",
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
				"name": "operator",
				"type": "address"
			}
		],
		"name": "isApprovedForAll",
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
		"inputs": [
			{
				"internalType": "uint256",
				"name": "id",
				"type": "uint256"
			},
			{
				"internalType": "address",
				"name": "who",
				"type": "address"
			}
		],
		"name": "isBuyAllowed",
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
		"inputs": [
			{
				"internalType": "address",
				"name": "to",
				"type": "address"
			}
		],
		"name": "minimalToken",
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
				"name": "to",
				"type": "address"
			},
			{
				"internalType": "address",
				"name": "who",
				"type": "address"
			}
		],
		"name": "minimalTokenContainsAddress",
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
		"inputs": [
			{
				"internalType": "uint256",
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "ownerOf",
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
		"inputs": [
			{
				"internalType": "address",
				"name": "to",
				"type": "address"
			}
		],
		"name": "ownerTokens",
		"outputs": [
			{
				"internalType": "uint256[]",
				"name": "",
				"type": "uint256[]"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "renounceOwnership",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
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
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "safeTransferFrom",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
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
				"name": "tokenId",
				"type": "uint256"
			},
			{
				"internalType": "bytes",
				"name": "_data",
				"type": "bytes"
			}
		],
		"name": "safeTransferFrom",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "address",
				"name": "operator",
				"type": "address"
			},
			{
				"internalType": "bool",
				"name": "approved",
				"type": "bool"
			}
		],
		"name": "setApprovalForAll",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"inputs": [
			{
				"internalType": "bytes4",
				"name": "interfaceId",
				"type": "bytes4"
			}
		],
		"name": "supportsInterface",
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
		"inputs": [
			{
				"internalType": "uint256",
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "tokenURI",
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
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "transferFrom",
		"outputs": [],
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
				"name": "to",
				"type": "address"
			}
		],
		"name": "weiGetBalance",
		"outputs": [
			{
				"components": [
					{
						"internalType": "uint256",
						"name": "all",
						"type": "uint256"
					},
					{
						"internalType": "uint256",
						"name": "balance",
						"type": "uint256"
					}
				],
				"internalType": "struct AnhydriteBlock.profit",
				"name": "",
				"type": "tuple"
			}
		],
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [],
		"name": "withdrawProfit",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"stateMutability": "payable",
		"type": "receive"
	}
];
