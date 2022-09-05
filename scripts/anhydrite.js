async function anhydrite(param, x, position) {

    const anh_address = [
	'0x578b350455932aC3d0e7ce5d7fa62d7785872221',
	'0x15e584A1527EF01Dd0cF6C1D1d140cD5dE9D65cC',
	'0xF855294bd9573698380dFC4e25054b2FA9c57E9B',
	'0x586b3EbCAd926867B3C6329Fa6b1D79A74B50249',
	'0x0CB3765bC673Ecfc55Fa36Ced05aC83572313e21',
	'0xD44DFd8230cF2A821F76C2B1F0679028ff7c084e',
	'0x0CB3765bC673Ecfc55Fa36Ced05aC83572313e21'];

    const NET_PYRAMID = {x: 0, name: '', net_id: 0, net_name: '', url: '', sumbol: '', contract: '', price: 0};

    let isNetLogined = undefined;

    if (typeof param !== 'undefined' && param !== '' && param !== null) {
		try {
			if (param === 'clear') {await loadHTML(param, '&nbsp;'); return;}
			if (param === 'profit') {await getBalance(x); return;}
			if (param === 'wprofit') {await withdrawProfit(x); return;}
			if (param === 'buyrandom') {await buyRandomToken(x); return;}
			if (param === 'buyid') {await buyTokenID(x); return;}
			if (param === 'buyaddress') {await buyTokenAddress(x); return;}
			if (param === 'holder') {await holder_Is(x); return;}
			if (param === 'wholder') {await holder_Switch(x); return;}
			if (param === 'checkid') {await buyAllowed(x); return;}
			if (param === 'alltokenaddress') {await getTokensAddress(x); return;}
			if (param === 'shortesttoken') {await minimalToken(x); return;}
			if (param === 'shortesaddress') {await minimalTokenContainsAddress(x, 'shortesaddress_', 'shortes-address'); return;}
			if (param === 'lengthtoken') {await branchLength(x, 'lengthtoken_', 's-length-token'); return;}
			if (param === 'lengthtokenaddress') {await branchLocation(x, 'lengthtokenaddress_', 'length-token', 'length-token-address'); return;}
			if (param === 'parenttoken') {await getPreviousID(x, 'parenttoken_', 'parent-token-id'); return;}
			if (param === 'childtokens') {await getNextIDs(x, 'childtokens_', 'child-token-id'); return;}
			if (param === 'owneroff') {await ownerOf(x, 'owneroff_', 'owneroff-id'); return;}
			if (param === 'balanceoff') {await ownerTokens(x, 'balanceoff_', 'balanceoff-address'); return;}
			if (param === 'otherprofit') {await balanceAddress(x, 'otherprofit_', 'b-otherprofit'); return;}
			if (param === 'safetransfer') {await safeTransferFrom(x, 'transfertoken_', 'transfer-token', 'transferfrom-address'); return;}
			if (param === 'load') {await load(); return;}
			if (param === 'login') {await loginMetaMask(); return;}
			if (param === 'link') {await linkMetaMask(); return;}
			if (param === 'link2') {await linkMetaMaskApp(); return;}
		}
		catch(err) {
		    console.log(err);
		    try {
			if (typeof position !== 'undefined') loadHTML(position, lang.errorcode.replace('&%result1', err.message));
		    } catch(e) {console.log('Error: ' + e.message);}
		}
    }

    async function load() {
	window.contracts = new Array();
	if (typeof window.ethereum !== 'undefined') {
	    window.web3 = new Web3(window.ethereum);
	    await loadContracts();
	    account = await getCurrentAccount();
	    newStatusSet('status', vhtml.nullstatus);
	    if (typeof account !== 'undefined') {
		isNetLogined = true;
	    }else {
		isNetLogined = false;
	    }
	}
	await status();
    }

    async function setStatus(x) {
	x = numberX(x);
	let message = '&nbsp;';
	if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	let adddr = anh_address[x];
	let total = '';
	let price = NET_PYRAMID.price / 1000000000000000000;
	let sumbl = NET_PYRAMID.sumbol;
	if (typeof isNetLogined !== 'undefined') {
	    let contract = NET_PYRAMID.contract;
	    name = NET_PYRAMID.name;
	    total = (window.Web3.givenProvider !== null && await getNetId() == NET_PYRAMID.net_id) ? await totalSupply(window.contracts[x]) : lang.unavailable;
	    if (isNetLogined) {
		message = lang.basestatus
		.replace('{&%}', NET_PYRAMID.net_name)
		.replace('{&%}', NET_PYRAMID.name)
		.replace('{&%}', NET_PYRAMID.url)
		.replace('{&%}', adddr)
		.replace('{&%}', adddr)
		.replace('{&%}', total)
		.replace('{&%}', price)
		.replace('{&%}', NET_PYRAMID.sumbol)
		.replace('{&%}', NET_PYRAMID.url)
		.replace('{&%}', account)
		.replace('{&%}', account);
	    } else {
		message = lang.nologin
		.replace('{&%}', NET_PYRAMID.net_name)
		.replace('{&%}', NET_PYRAMID.name)
		.replace('{&%}', NET_PYRAMID.url)
		.replace('{&%}', adddr)
		.replace('{&%}', adddr)
		.replace('{&%}', total)
		.replace('{&%}', price)
		.replace('{&%}', NET_PYRAMID.sumbol);
	    }
	} else if (browser.mobile) {
	    message = lang.metamaskmobile
	    .replace('{&%}', NET_PYRAMID.net_name)
	    .replace('{&%}', NET_PYRAMID.url)
	    .replace('{&%}', adddr)
	    .replace('{&%}', adddr)
	    .replace('{&%}', price)
	    .replace('{&%}', NET_PYRAMID.sumbol);
	} else {
	    message = lang.noinstall
	    .replace('{&%}', NET_PYRAMID.net_name)
	    .replace('{&%}', NET_PYRAMID.url)
	    .replace('{&%}', adddr)
	    .replace('{&%}', adddr)
	    .replace('{&%}', price)
	    .replace('{&%}', NET_PYRAMID.sumbol);
	}
	return message/*+browser.mobile+"<br>"+browser.os+"<br>"+browser.name*/;
    }
    async function upStatus() {
	let position = 'status';
	for (let i = 1; i < 7; i++) {
	    let pos = i == 6 ? position : position+i;
	    let x = i;
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    let adddr = anh_address[x];
	    let price = NET_PYRAMID.price / 1000000000000000000;
	    let status = "";
	    if (browser.mobile) {
		status = lang.metamaskmobile
		.replace('{&%}', NET_PYRAMID.net_name)
		.replace('{&%}', NET_PYRAMID.url)
		.replace('{&%}', adddr)
		.replace('{&%}', adddr)
		.replace('{&%}', price)
		.replace('{&%}', NET_PYRAMID.sumbol);
	} else {
		status = lang.update
		.replace('{&%}', NET_PYRAMID.net_name)
		.replace('{&%}', NET_PYRAMID.url)
		.replace('{&%}', adddr)
		.replace('{&%}', adddr)
		.replace('{&%}', price)
		.replace('{&%}', NET_PYRAMID.sumbol);
	    }
	    loadHTML(pos, status);
	}
    }

    async function status() {
	let position = 'status';
	for (let i = 1; i < 7; i++) {
	    let pos = i == 6 ? position : position+i;
	    let x = i;
	    let status = await setStatus(x);
	    loadHTML(pos, status);
	}
    }
    async function getCurrentAccount() {
	const accounts = await window.web3.eth.getAccounts();
	return accounts[0];
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

    function loadHTML(location, status) {
	const loc = document.getElementById(location);
	loc.innerHTML = status;
    }

    function newStatusSet(position, status) {
	for (let i = 0; i < 6; i++) {
	    let pos = i == 0 ? position : position+i;
	    loadHTML(pos, status);
	}
    }

    async function loadContracts() {
	async function loadContract(abi, address) {
	    return await new window.web3.eth.Contract(abi, address);
	}
	for (let i = 0; i < 7; i++) {
	    let abi = i == 0 ? abi_anh : abi_anb;
	    contracts.push(await loadContract(abi, anh_address[i]));
	}
    }

    async function tokenName(contract) {
	 let result = 'unknown';
	if (typeof contract === 'undefined') contract = NET_PYRAMID.contract;
	if (contract !== '') result = await contract.methods.name().call();
	return result;
    }

	async function totalSupply(contract) {
	    let result = 0;
	    if (typeof contract === 'undefined') contract = NET_PYRAMID.contract;
	    if (contract !== '') result = await contract.methods.totalSupply().call();
	    return result;
	}

	async function isBuyAllowed(contract, token) {
	   let result = false;
	    if (typeof contract === 'undefined') contract = NET_PYRAMID.contract;
	   if (contract !== '') result = await contract.methods.isBuyAllowed(token, account).call();
	   return result;
	}

	async function minimalTokenAddress(contract, address) {
	   let result = 0;
	   if (typeof contract === 'undefined') contract = NET_PYRAMID.contract;
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

    async function getBalance(x) {
	x = numberX(x);
	let message = '&nbsp;';
	let pos = 'profit_';
	const position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	if (typeof account !== 'undefined') {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		const contract = NET_PYRAMID.contract;
		const result = (await contract.methods.weiGetBalance(account).call(function(error, results){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}}));
		message = lang.resultprofit.replace('&%result_', result[0]/1000000000000000000).replace('&%result1', result[1]/1000000000000000000).replaceAll('&%result2', NET_PYRAMID.sumbol);
	    } else {
		message = lang.errornetid.replace('&%result1', getNetName(netId));
	    }
	} else {
	    message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

    async function withdrawProfit(x) {
	x = numberX(x);
	let message = '&nbsp;';
	let pos = 'profit_';
	const position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	if (typeof account !== 'undefined') {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		const contract = NET_PYRAMID.contract;
		await contract.methods.withdrawProfit().send({ from: account })
		    .on('transactionHash', function(hash){
			message = lang.resulttxhash.replace('&%result1', NET_PYRAMID.url);
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

    async function buyRandomToken(x) {
	x = numberX(x);
	let message = '&nbsp;';
	let pos = 'buyrandom_';
	const position = checkX(x) ? pos + x : pos;
	if (typeof account !== 'undefined') {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    if (checkBoxIs(x, 'buy-random-check')) {
		let netId = await getNetId();
		if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		    loadHTML(position, lang.waiting);
		    const contract = NET_PYRAMID.contract;
		    const token = await contract.methods.minimalToken(account).call(function(error, result){if (error !== null) {loadHTML(position, error.message);}});
		    await contract.methods.buyTokenID(token).send({ from: account, value: NET_PYRAMID.price })
		    .on('transactionHash', function(hash){
			message = lang.resulttxhash.replace('&%result1', NET_PYRAMID.url);
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
		message = lang.errcheckbox;
	    }
	} else {
	    message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

    async function buyTokenID(x) {
	x = numberX(x);
	let message = '&nbsp;';
	let pos = 'buyid_';
	let position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	if (typeof account !== 'undefined') {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    if (checkBoxIs(x, 'buy-id-check')) {
		let netId = await getNetId();
		if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		    let sub = 'get-buy-id';
		    let submit = checkX(x) ? sub + x : sub;
		    let token = Number($("#"+submit).val());
		    if (!isNaN(token)) {
			const contract = NET_PYRAMID.contract;
			let total = await totalSupply(contract);
			if (token > 0 && token <= total) {
			    let allowed = await isBuyAllowed(contract, token);
			    if (allowed) {
				await contract.methods.buyTokenID(token).send({ from: account, value: NET_PYRAMID.price })
				.on('transactionHash', function(hash){
				    message = lang.resulttxhash.replace('&%result1', NET_PYRAMID.url);
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
				message = lang.errnoallowed;
			    }
			} else {
			    message = lang.errtokenid_.replace('&%result1', total);
			}
		    } else {
			message = lang.errtokenuint;
		    }
		} else {
		    message = lang.errornetid.replace('&%result1', getNetName(netId));
		}
	    } else {
		message = lang.errcheckbox;
	    }
	} else {
	    message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

	async function buyTokenAddress(x) {
	    x = numberX(x);
	    let message = '&nbsp;';
	    let pos = 'buyaddress_';
	    let position = checkX(x) ? pos + x : pos;
	    loadHTML(position, lang.waiting);
	    if (typeof account !== 'undefined') {
		if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
		if (checkBoxIs(x, 'buy-address-check')) {
		    let netId = await getNetId();
		    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
			let sub = 'get-buy-address';
			let submit = checkX(x) ? sub + x : sub;
			let address = $("#"+submit).val();
			const contract = NET_PYRAMID.contract;
			if (isAddress(address)) {
			    let token = await minimalTokenAddress(contract, address);
			    if (token !== 0 && await isBuyAllowed(contract, token)) {
				loadHTML(position, lang.waiting);
				
				await contract.methods.buyTokenID(token).send({ from: account, value: NET_PYRAMID.price })
				.on('transactionHash', function(hash){
				    message = lang.resulttxhash.replace('&%result1', NET_PYRAMID.url);
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
		    message = lang.errcheckbox;
		}
	   } else {
		message = lang.errnoconnect;
	   }
		loadHTML(position, message);
	}

	async function holder_Is(x) {
	    x = numberX(x);
	    let pos = 'holder_';
	    let message = '&nbsp;';
	    const position = checkX(x) ? pos + x : pos;
	    loadHTML(position, lang.waiting);
	    if (typeof account !== 'undefined') {
		if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
		let netId = await getNetId();
		if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		    const contract = NET_PYRAMID.contract;
		    const result = await contract.methods.holder_Is(account).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
		    if (result) message = lang.resultholdt; else message = lang.resultholdf;
		} else {
		    message = lang.errornetid.replace('&%result1', getNetName(netId));
		}
	    } else {
		message = lang.errnoconnect;
	    }
		loadHTML(position, message);
	}

    async function holder_Switch(x) {
	x = numberX(x);
	let pos = 'holder_';
	let message = '&nbsp;';
	const position = checkX(x) ? pos + x : pos;
	if (typeof account !== 'undefined') {
	    loadHTML(position, lang.waiting);
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    const contract = NET_PYRAMID.contract;
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		loadHTML(position, lang.waiting);
		const contract = NET_PYRAMID.contract;
		await contract.methods.holder_Switch().send({ from: account })
		.on('transactionHash', function(hash){
		    message = lang.resulttxhash.replace('&%result1', NET_PYRAMID.url);
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

	async function buyAllowed(x) {
	    x = numberX(x);
	    let pos = 'checkid_';
	    let message = '&nbsp;';
	    const position = checkX(x) ? pos + x : pos;
	    loadHTML(position, lang.waiting);
	    let netId = await getNetId();
	    if (typeof account !== 'undefined') {
		if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
		if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		    let sub = 'check-id';
		    let submit = checkX(x) ? sub + x : sub;
		    let token = Number($("#"+submit).val());
		    if (!isNaN(token)) {
			const contract = NET_PYRAMID.contract;
			let total = await totalSupply(contract);
			if (token > 0 && token <= total) {
			    let result = await isBuyAllowed(contract, token);
			    if (result) message = lang.resultchcidt.replace('{&%}', token);
			    else message = lang.resultchcidf.replace('{&%}', token);
			} else {
			    message = lang.errtokenid_.replace('&%result1', total);
			}
		    } else {
			message = lang.errtokenuint;
		    }
		} else {
		    message = lang.errornetid.replace('&%result1', getNetName(netId));
		}
	    } else {
		message = lang.errnoconnect;
	    }
		loadHTML(position, message);
	}

    async function getTokensAddress(x) {
	x = numberX(x);
	let message = '&nbsp;';
	let pos = 'alltokesnaddress_';
	let position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	if (window.Web3.givenProvider !== null) {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		let sub = 't-tokens-address';
		let submit = checkX(x) ? sub + x : sub;
		let address = $("#"+submit).val();
		const contract = NET_PYRAMID.contract;
		if (isAddress(address)) {
		    let result = await contract.methods.getTokensContainsAddress(address).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
		    if (result.length == 0) message = lang.reslengthtok; else message = result.join('; ');
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

    async function minimalToken(x) {
	x = numberX(x);
	let pos = 'shortesttoken_';
	let message = '&nbsp;';
	const position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	if (typeof account !== 'undefined') {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		const contract = NET_PYRAMID.contract;
		let result = await contract.methods.minimalToken(account).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
		if (result == 0) result = lang.resultshort0; else result = 'ID: ' + result;
		message = lang.resultshorts.replace('&%result1', result);
	    } else {
		message = lang.errornetid.replace('&%result1', getNetName(netId));
	    }
	} else {
	    message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

    async function minimalTokenContainsAddress(x, pos, sub) {
	x = numberX(x);
	let message = '&nbsp;';
	let position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	if (typeof account !== 'undefined') {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		let submit = checkX(x) ? sub + x : sub;
		let address = $("#"+submit).val();
		const contract = NET_PYRAMID.contract;
		if (isAddress(address)) {
		    let result = await contract.methods.minimalTokenContainsAddress(address, account).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
		    if (result == 0) result = lang.resultshort0; else result = 'ID: ' + result;
		    message = lang.resultshorts.replace('&%result1', result);
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

    async function branchLength(x, pos, sub) {
	x = numberX(x);
	let message = '&nbsp;';
	const position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	let netId = await getNetId();
	if (window.Web3.givenProvider !== null) {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		let submit = checkX(x) ? sub + x : sub;
		let token = Number($("#"+submit).val());
		if (!isNaN(token)) {
		    const contract = NET_PYRAMID.contract;
		    let total = await totalSupply(contract);
		    if (token > 0 && token <= total) {
			let result = await contract.methods.branchLength(token).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
			message = lang.resultlengt.replace('&%result1', token).replace('&%result2', result);
		    } else {
			message = lang.errtokenid_.replace('&%result1', total);
		    }
		} else {
		    message = lang.errtokenuint;
		}
	    } else {
		message = lang.errornetid.replace('&%result1', getNetName(netId));
	    }
	} else {
	    message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

    async function branchLocation(x, pos, sub, sub2) {
	x = numberX(x);
	let message = '&nbsp;';
	const position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	let netId = await getNetId();
	if (window.Web3.givenProvider !== null) {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		let submit = checkX(x) ? sub + x : sub;
		let submit2 = checkX(x) ? sub2 + x : sub2;
		let token = Number($("#"+submit).val());
		if (!isNaN(token)) {
		    let submit2 = checkX(x) ? sub2 + x : sub2;
		    let address = $("#"+submit2).val();
		    if (isAddress(address)) {
			const contract = NET_PYRAMID.contract;
			let total = await totalSupply(contract);
			if (token > 0 && token <= total) {
			    let result = await contract.methods.branchLocation(token, address).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
			    if (result[1] == 0) message = lang.resultlenloc0.replace('&%result1', token).replace('&%result2', result[0]).replace('&%result3', address);
			    else message = lang.resultlenloc.replace('&%result1', token).replace('&%result2', result[0]).replace('&%result3', result[1]);
			} else {
			    message = lang.errtokenid_.replace('&%result1', total);
			}
		    } else {
			message = lang.errtokenaddr.replace('&%result1', address);
		    }
		} else {
		    message = lang.errtokenuint;
		}
	    } else {
		message = lang.errornetid.replace('&%result1', getNetName(netId));
	    }
	} else {
	    message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

    async function getPreviousID(x, pos, sub) {
	x = numberX(x);
	let message = '&nbsp;';
	const position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	let netId = await getNetId();
	if (window.Web3.givenProvider !== null) {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		let submit = checkX(x) ? sub + x : sub;
		let token = Number($("#"+submit).val());
		if (!isNaN(token)) {
		    const contract = NET_PYRAMID.contract;
		    let total = await totalSupply(contract);
		    if (token > 0 && token <= total) {
			let result = await contract.methods.getPreviousID(token).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
			if (result == 0) {
			    message = lang.resultparent0.replace('&%result1', token);
			} else {
			    message = lang.resultparent.replace('&%result1', token).replace('&%result2', result);
			}
		    } else {
			message = lang.errtokenid_.replace('&%result1', total);
		    }
		} else {
		    message = lang.errtokenuint;
		}
	    } else {
		message = lang.errornetid.replace('&%result1', getNetName(netId));
	    }
	} else {
		message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

    async function getNextIDs(x, pos, sub) {
	x = numberX(x);
	let message = '&nbsp;';
	const position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	let netId = await getNetId();
	if (window.Web3.givenProvider !== null) {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		let submit = checkX(x) ? sub + x : sub;
		let token = Number($("#"+submit).val());
		    if (!isNaN(token)) {
			const contract = NET_PYRAMID.contract;
			let total = await totalSupply(contract);
			if (token > 0 && token <= total) {
			    let result = await contract.methods.getNextIDs(token).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
			    if (result.length == 0) {
				message = lang.resultchild0.replace('&%result1', token);
			    } else {
				message = lang.resultchild.replace('&%result1', token).replace('&%result2', result.join('; '));
			    }
			} else {
			    message = lang.errtokenid_.replace('&%result1', total);
			}
		    } else {
			message = lang.errtokenuint;
		    }
		} else {
		    message = lang.errornetid.replace('&%result1', getNetName(netId));
		}
	    } else {
		message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

    async function ownerOf(x, pos, sub) {
	x = numberX(x);
	let message = '&nbsp;';
	const position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	let netId = await getNetId();
	if (window.Web3.givenProvider !== null) {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		let submit = checkX(x) ? sub + x : sub;
		let token = Number($("#"+submit).val());
		if (!isNaN(token)) {
		    const contract = NET_PYRAMID.contract;
		    let total = await totalSupply(contract);
		    if (token > 0 && token <= total) {
			let result = await contract.methods.ownerOf(token).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
			message = lang.resultowner.replace('&%result1', token).replace('&%result2', result);
		    } else {
			message = lang.errtokenid_.replace('&%result1', total);
		    }
		} else {
		    message = lang.errtokenuint;
		}
	    } else {
		message = lang.errornetid.replace('&%result1', getNetName(netId));
	    }
	} else {
	message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

    async function ownerTokens(x, pos, sub) {
	x = numberX(x);
	let message = '&nbsp;';

	let position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	if (window.Web3.givenProvider !== null) {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		let submit = checkX(x) ? sub + x : sub;
		let address = $("#"+submit).val();
		const contract = NET_PYRAMID.contract;
		if (isAddress(address)) {
		    let result = await contract.methods.ownerTokens(address).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
		    if (result.length == 0) {
			message = lang.resultownto0.replace('&%result1', address);
		    } else {
			message = lang.resultowntok.replace('&%result1', address).replace('&%result2', result.join('; '));
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

    async function balanceAddress(x, pos, sub) {
	x = numberX(x);
	let message = '&nbsp;';
	let position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	if (window.Web3.givenProvider !== null) {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    let netId = await getNetId();
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		let submit = checkX(x) ? sub + x : sub;
		let address = $("#"+submit).val();
		const contract = NET_PYRAMID.contract;
		if (isAddress(address)) {
		    let result = await contract.methods.weiGetBalance(address).call(function(error, results){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
		    message = lang.resultprofit.replace('&%result_', result[0]/1000000000000000000).replace('&%result1', result[1]/1000000000000000000).replaceAll('&%result2', NET_PYRAMID.sumbol);
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

    async function safeTransferFrom(x, pos, sub, sub2) {
	x = numberX(x);
	let message = '&nbsp;';
	const position = checkX(x) ? pos + x : pos;
	loadHTML(position, lang.waiting);
	let netId = await getNetId();
	if (window.Web3.givenProvider !== null) {
	    if (NET_PYRAMID.x !== x) await setNET_PYRAMID(x);
	    if (typeof netId !== 'undefined' && netId === NET_PYRAMID.net_id) {
		let submit = checkX(x) ? sub + x : sub;
		let submit2 = checkX(x) ? sub2 + x : sub2;
		let token = Number($("#"+submit).val());
		if (!isNaN(token)) {
		    let submit2 = checkX(x) ? sub2 + x : sub2;
		    let address = $("#"+submit2).val();
		    if (isAddress(address)) {
			const contract = NET_PYRAMID.contract;
			let total = await totalSupply(contract);
			if (token > 0 && token <= total) {
			    let owner = await contract.methods.ownerOf(token).call(function(error, result){if (error !== null) {message = vhtml.errorsdiv.replace('&%result1', error.message);}});
			    if (owner === account) {
				await contract.methods.safeTransferFrom(account, address, token).send({ from: account })
				.on('transactionHash', function(hash){
				    message = lang.resulttxhash.replace('&%result1', NET_PYRAMID.url);
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
			    } else {message = lang.errtransfer.replace('{&%}', token);}
			} else {
			    message = lang.errtokenid_.replace('&%result1', total);
			}
		    } else {
			message = lang.errtokenaddr.replace('&%result1', address);
		    }
		} else {
		    message = lang.errtokenuint;
		}
	    } else {
		message = lang.errornetid.replace('&%result1', getNetName(netId));
	    }
	} else {
	    message = lang.errnoconnect;
	}
	loadHTML(position, message);
    }

    async function getNetId() {
	let netId = undefined;
	if (window.Web3.givenProvider !== null) {
	    let promise = web3.eth.net.getId();
	    await promise.then((result) => netId = result);
	}
	return netId;
    }

    async function getPrice(contract) {
	let result = 0;
	if (window.Web3.givenProvider !== null) {
	    if (contract !== '') result = await contract.methods.getPrice().call();
	}	
	return result;
    }

    async function setRopsten(x) {
	NET_PYRAMID.x = x;
	NET_PYRAMID.net_id = 3;
	NET_PYRAMID.name = (window.Web3.givenProvider !== null && await getNetId() == 3) ? await tokenName(window.contracts[x]) : lang.unavailable;
	NET_PYRAMID.net_name = 'Testnet Ropsten';
	NET_PYRAMID.url = 'https://ropsten.etherscan.io';
	NET_PYRAMID.sumbol = 'ETH';
	NET_PYRAMID.contract = window.contracts[x];
	NET_PYRAMID.price = await getPrice(NET_PYRAMID.contract);
    }

    async function setBSCTest(x) {
	NET_PYRAMID.x = x;
	NET_PYRAMID.net_id = 97;
	NET_PYRAMID.name = (window.Web3.givenProvider !== null && await getNetId() == 97) ? await tokenName(window.contracts[x]) : lang.unavailable;
	NET_PYRAMID.net_name = 'Smart Chain - Testnet';
	NET_PYRAMID.url = 'https://testnet.bscscan.com/';
	NET_PYRAMID.sumbol = 'BNB';
	NET_PYRAMID.contract = window.contracts[x];
	NET_PYRAMID.price = await getPrice(NET_PYRAMID.contract);
    }

    async function setBSC(x) {
	NET_PYRAMID.x = x;
	NET_PYRAMID.net_id = 56;
	NET_PYRAMID.name = (window.Web3.givenProvider !== null && await getNetId() == 56) ? await tokenName(window.contracts[x]) : lang.unavailable;
	NET_PYRAMID.net_name = 'Smart Chain';
	NET_PYRAMID.url = 'https://bscscan.com/';
	NET_PYRAMID.sumbol = 'BNB';
	NET_PYRAMID.contract = window.contracts[x];
	NET_PYRAMID.price = await getPrice(NET_PYRAMID.contract);
    }

    async function setNET_PYRAMID(x) {
	x = numberX(x);
	if (x > 0 && x < 6) {
	    await setBSC(x);
	} else {
	    await setBSCTest(x);
	}
    }


	function getNetName(net_id) {
	    let net_name = 'unknown';
	    if (net_id == 97) net_name = 'Smart Chain - Testnet';
	    if (net_id == 56) net_name = 'Smart Chain';
	    if (net_id == 3) net_name = 'Testnet Ropsten';
	    if (net_id == 1) net_name = 'Ethereum mainnet';
	    return net_name;
	}

	function checkBoxIs(x, box) {
	    let checkx = checkX(x) ? box + x : box;
	    return $(`#${checkx}`).is(":checked");
	}

	function isAddress(address) {
	    let re = /^0x[a-fA-F0-9]{40}$/
	   return re.exec(address);
		}

	function checkX(x) {
	    return (x > 0 && x < 6);
	}

	function numberX2(x) {
	    console.log(window.location.hash);
	    let loc = window.location.hash;
	    x = 6;
	    if (loc === '#purple') x = 1;
	    if (loc === '#green') x = 2;
	    if (loc === '#yellow') x = 3;
	    if (loc === '#orange') x = 4;
	    if (loc === '#red') x = 5;
	    if (loc === '#ropsten') x = 6;
	    console.log('location = '+loc);
	    console.log('x = '+x);
	   return x;
	}

	function numberX(x) {
	    if (typeof x !== 'number' || !checkX(x)) x = 6;
	    return x;
	}

}

let account;
const abi_anb = [
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
	"inputs": [],
	"name": "holder_Switch",
	"outputs": [],
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
	"inputs": [],
	"name": "withdrawProfit",
	"outputs": [],
	"stateMutability": "nonpayable",
	"type": "function"
    },
    {
	"stateMutability": "payable",
	"type": "receive"
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
    }
];
/* ********************************************************************************************************** */
const abi_anh = [
    {
	"inputs": [
	    {
		"internalType": "string",
		"name": "data_",
		"type": "string"
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
	"inputs": [
	    {
		"internalType": "address",
		"name": "contr",
		"type": "address"
	    },
	    {
		"internalType": "string",
		"name": "data",
		"type": "string"
	    }
	],
	"name": "getP",
	"outputs": [],
	"stateMutability": "nonpayable",
	"type": "function"
    },
    {
	"inputs": [],
	"name": "getW",
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
	"name": "renounceOwnership",
	"outputs": [],
	"stateMutability": "nonpayable",
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
		"internalType": "string",
		"name": "data",
		"type": "string"
	    },
	    {
		"internalType": "address",
		"name": "to",
		"type": "address"
	    }
	],
	"name": "xAdd",
	"outputs": [],
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
	"name": "xContract",
	"outputs": [],
	"stateMutability": "nonpayable",
	"type": "function"
    },
    {
	"inputs": [
	    {
		"internalType": "string",
		"name": "data",
		"type": "string"
	    },
	    {
		"internalType": "string",
		"name": "newdata",
		"type": "string"
	    }
	],
	"name": "xNew",
	"outputs": [],
	"stateMutability": "nonpayable",
	"type": "function"
    },
    {
	"inputs": [
	    {
		"internalType": "string",
		"name": "data",
		"type": "string"
	    },
	    {
		"internalType": "address",
		"name": "to",
		"type": "address"
	    }
	],
	"name": "xRemove",
	"outputs": [],
	"stateMutability": "nonpayable",
	"type": "function"
    },
    {
	"stateMutability": "payable",
	"type": "receive"
    }
];
