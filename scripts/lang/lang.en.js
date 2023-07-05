const vhtml = {
	statusdiv2: `<div style="margin: 5px; color: black;">&%status <div>`,
	resultdiv:  `<div style="margin: 10px">{&%}<div>`,
	result1div: `<div style="margin: 10px">&%result1<div>`,
	result2div: `<div style="red: 10px">&%result1 &%result2<div>`,
	errorsdiv:  `<div style="margin: 10px; color: red; font-weight: 600;">&%result1<div>`,
        nullstatus: `&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;`
};
const pcol = {_1:'Purple',_2:'Green',_3:'Yellow',_4:'Orange',_5:'Red',_6:'Testing'};
const lang = {
    // *************** Status *************** //
    unavailable:  ' ...',
    waiting:      '<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; Wait ...</h4>',
    basestatus:   vhtml.result1div.replace('&%result1', `Network: <b>{&%}</b></br>
                                                         Token: <b>{&%}</b></br>
                                                         Contract: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Total number of tokens: <b>{&%}</b></br>
                                                         Token value <b>{&%}</b> {&%}</br>
                                                         Your address: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>`),
    nologin:      vhtml.result1div.replace('&%result1', `Network: <b>{&%}</b></br>
                                                         Token: <b>{&%}</b></br>
                                                         Contract: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Total number of tokens: <b>{&%}</b></br>
                                                         Token value <b>{&%}</b> {&%}</br><b><font color="red">
                                                         You are not connected to the blockchain:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('login');">Connect to the blockchain</button></br>&nbsp;`),
    noinstall:    vhtml.result1div.replace('&%result1', `Network: <b>{&%}</b></br>
                                                         Contract: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Token value <b>{&%}</b> {&%}</br><b><font color="red">
                                                         You do not have MetaMask installed:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('link')">Install MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `Network: <b>{&%}</b></br>
                                                         Contract: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Token value <b>{&%}</b> {&%}</br><b><font color="red">
                                                         You are connected from a mobile device! To work with pyramids MetaMask is required,
                                                         if it is not installed, then:</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button" onclick="anhydrite('link')">Install MetaMask</button></br><b></li><font color="red">
                                                         If MetaMask is already installed on your device, then open the site throught the MetaMask browser:</font></b>
                                                         <li><button class="button" onclick="anhydrite('link2')">Open MetaMask browser</button></li></br></ul></form>`),
    update:       vhtml.result1div.replace('&%result1', `Network: <b>{&%}</b></br>
                                                         Contract: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Token value <b>{&%}</b> {&%}</br><b><font color="red">
                                                         If you are already connected to the blockchain, update your status:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('load');">Update status</button></br>&nbsp;`),
    // *************** Errors *************** //
    errorcode:     vhtml.errorsdiv.replace('&%result1', `Something went wrong ... Error executing the code:&nbsp; (&%result1)`),
    errornetid:    vhtml.errorsdiv.replace('&%result1', 'Error:&nbsp;  you are connected to a network "&%result1", open "MetaMask" and connect to the desired network, then refresh the page'),
    errnoconnect:  vhtml.errorsdiv.replace('&%result1', 'Error:&nbsp;  No connection to blockchain'),
    errnoallowed:  vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  This token exists, but it is not available for purchase`),
    errtokenid_:   vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  You entered an incorrect token ID, it must be a number from 1 to &%result1`),
    errtokenuint:  vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  You entered a non-number token ID. The token must be a number`),
    errtokenaddr:  vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  You have entered an invalid address, please check it again: (&%result1)`),
    errtokenperm:  vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  There are no tokens available for purchase with this address: (&%result1)`),
    errcheckbox:   vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  You must confirm your agreement with the: <u>User agreement</u>`),
    errtransfer:   vhtml.errorsdiv.replace('&%result1', `Errror:&nbsp;  You cannot transfer the token <b>{&%}</b>, because it doesn't belong to you`),
    // *************** Results *************** //
    reslengthtok: vhtml.result1div.replace('&%result1', `<b>There are no tokens containing this address</b>`),
    resultprofit: vhtml.result2div.replace('&%result1', `Total earned: <b>&%result_</b> &%result2</br>Remaining on the account: <b>&%result1</b>`),
    resulttxhash: vhtml.result1div.replace('&%result1', `Transaction sent, see the result: <b><a href="&%result1/tx/&%result2" target="_blank" style="text-decoration: underline;">Transaction link</a></b>`),
    resulttxhasx: vhtml.result1div.replace('&%result1', `The transaction is signed, see the result: <b><a href="&%result1/tx/&%result2" target="_blank" style="text-decoration: underline;">Transaction link</a></b>`),
    resultholdt:  vhtml.result1div.replace('&%result1', `"Holder" mode <b>activated</b>`),
    resultholdf:  vhtml.result1div.replace('&%result1', `"Holder" mode <b>deactivated</b>`),
    resultchcidt: vhtml.result1div.replace('&%result1', `Token <b>{&%}</b> is available for purchase from your account`),
    resultchcidf: vhtml.result1div.replace('&%result1', `Token <b>{&%}</b> is not available for purchase from your account`),
    resultshorts: vhtml.result1div.replace('&%result1', `The shortest token for purchase from your account: <b>&%result1</b>`),
    resultshort0: vhtml.result1div.replace('&%result1', `<b>Missing</b>`),
    resultlengt:  vhtml.result1div.replace('&%result1', `Length (number of addresses) of the token <b>&%result1</b> equals <b>&%result2</b>`),
    resultlenloc: vhtml.result1div.replace('&%result1', `Length of the token <b>&%result1</b> equals: <b>&%result2</b>, the specified address is at <b>&%result3</b> position`),
    resultlenloc0:vhtml.result1div.replace('&%result1', `Length of the token <b>&%result1</b> equals: <b>&%result2</b>, the adress <b>&%result3</b> in its list <b>is missing</b>`),
    resultparent: vhtml.result1div.replace('&%result1', `The token <b>&%result1</b> is created from the token <b>&%result2</b>`),
    resultparent0:vhtml.result1div.replace('&%result1', `The token <b>&%result1</b> is created from a <b>"null"</b> token`),
    resultchild0: vhtml.result1div.replace('&%result1', `The token <b>&%result1</b> has <b>zero</b> child tokens`),
    resultchild:  vhtml.result1div.replace('&%result1', `From the token <b>&%result1</b> were created the tokens <b>&%result2;</b>`),
    resultowner:  vhtml.result1div.replace('&%result1', `The owner of the token <b>&%result1</b> is the accounnt: <b>&%result2</b>`),
    resultownto0: vhtml.result1div.replace('&%result1', `The account: <b>&%result1</b> doesnt't own tokens`),
    resultowntok: vhtml.result1div.replace('&%result1', `The account: <b>&%result1</b> owns the tokens: <b>&%result2</b>`),

    // *************** Pages *************** //

    all_pyramids_html: `
								<section>

								
								
									<h2 class="major">{&%arg1} Pyramid</h2>
								        <span id="status{&%}" class="image main" style='background: url("images/overlay.png")'>
                                                                        </span>
									<h3 class="major">Check and withdraw profits</h3>
									<form id="profit{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="profit{&%}" onclick="anhydrite('profit', {&%}, 'profit_{&%}');" value="Check profits" /></li>
											<li><input type="button" id="wprofit{&%}" onclick="anhydrite('wprofit', {&%}, 'profit_{&%}');" value="Withdraw profits" /></li>
										</ul>
									</form>
									<span id="profit_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Purchase a random token</h3>
									<form id="buyrandom{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="buy-random{&%}" onclick="anhydrite('buyrandom', {&%}, 'buyrandom_{&%}');" value="Buy token" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-random-check{&%}" name="buy-random-check{&%}">
												<label for=buy-random-check{&%}>Confirm</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">User agreement</a></p>
											</div>
									</form>
									<span id="buyrandom_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Purchase a token by ID</h3>
									<form id="buyid{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbuy-id{&%}">Token ID</label>
												<input type="text" name="get-buy-id{&%}" id="get-buy-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="buy-id{&%}" type="submit" onclick="anhydrite('buyid', {&%}, 'buyid_{&%}');" value="Buy token" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-id-check{&%}" name="buy-id">
												<label for="buy-id-check{&%}">Confirm</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">User agreement</a></p>
											</div>
									</form>
									<span id="buyid_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Purchase a token with an address</h3>
									<form id="buyaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbuy-address{&%}">Address</label>
												<input id="get-buy-address{&%}" type="text" name="get-buy-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="buy-address{&%}" type="submit" onclick="anhydrite('buyaddress', {&%}, 'buyaddress_{&%}');" value="Buy token" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-address-check{&%}" name="buy-id">
												<label for="buy-address-check{&%}">Confirm</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">User agreement</a></p>
											</div>
									</form>
									<span id="buyaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">"Holder" mode</h3>
									<form id="holder{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="hold{&%}" onclick="anhydrite('holder', {&%}, 'holder_{&%}');" value="Check Holder mode" /></li>
											<li><input type="button" id="xhold{&%}" onclick="anhydrite('wholder', {&%}, 'holder_{&%}');" value="Change Holder mode" /></li>
										</ul>
									</form>
									<span id="holder_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token availability</h3>
									<form id="checkid{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="check-id{&%}">Token ID</label>
												<input type="text" name="check-id{&%}" id="check-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="token-check-id{&%}" type="submit" onclick="anhydrite('checkid', {&%}, 'checkid_{&%}');" value="Check token" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</form>
									<span id="checkid_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token with address</h3>
									<form id="alltokenaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="t-address{&%}">Address</label>
												<input type="text" name="t-tokens-address{&%}" id="t-tokens-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="all-tokens-address{&%}" type="submit" onclick="anhydrite('alltokenaddress', {&%}, 'alltokesnaddress_{&%}');" value="Check the address" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</form>
									<span id="alltokesnaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Shortest token</h3>
									<form id="shortesttoken{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="shortest-token{&%}" onclick="anhydrite('shortesttoken', {&%}, 'shortesttoken_{&%}');" value="Show the shortest token" /></li>
										</ul>
									</form>
									<span id="shortesttoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Shortest token with address</h3>
									<form id="shortesaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="short-t-address{&%}">Address</label>
												<input type="text" name="shortes-address{&%}" id="shortes-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="t-shortes-address{&%}" type="submit" onclick="anhydrite('shortesaddress', {&%}, 'shortesaddress_{&%}');" value="Shortest token" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</form>
									<span id="shortesaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token length</h3>
									<form id="lengthtoken{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="ss-length-token{&%}">Token ID</label>
												<input type="text" name="s-length-token{&%}" id="s-length-token{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="aa-length-token{&%}" type="submit" onclick="anhydrite('lengthtoken', {&%}, 'lengthtoken_');" value="Token length" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</form>
									<span id="lengthtoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Length and address position</h3>
									<form id="lengthtokenaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xtlength-token">Token ID</label>
												<input type="text" name="length-token{&%}" id="length-token{&%}" value="" placeholder="0" />
											</div>
											<div class="field half">
												<label for="xlet-address{&%}">Address</label>
												<input type="text" name="length-token-address{&%}" id="length-token-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>
										</div>
										<ul class="actions">
											<li><input id="ll-token-address{&%}" type="submit" onclick="anhydrite('lengthtokenaddress', {&%}, 'lengthtokenaddress_');" value="Token length" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</form>
									<span id="lengthtokenaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Parent token</h3>
									<form id="parenttoken{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xparent-token-id{&%}">Token ID</label>
												<input type="text" name="parent-token-id{&%}" id="parent-token-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="parent-token-id{&%}" type="submit" onclick="anhydrite('parenttoken', {&%}, 'parenttoken_');" value="Parent token" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</form>
									<span id="parenttoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Child tokens</h3>
									<form id="childtokens{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xchild-owneroff-id{&%}">Token ID</label>
												<input type="text" name="child-token-id{&%}" id="child-token-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="ci-token-id{&%}" type="submit" onclick="anhydrite('childtokens', {&%}, 'childtokens_');" value="Child tokens" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</form>
									<span id="childtokens_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token's owner</h3>
									<form id="owneroff{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xowneroff-id{&%}">Token ID</label>
												<input type="text" name="owneroff-id{&%}" id="owneroff-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="token-owneroff{&%}" type="submit" onclick="anhydrite('owneroff', {&%}, 'owneroff_');" value="Token's owner" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</form>
									<span id="owneroff_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token balance</h3>
									<form id="balanceoff{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbalanceoff-address{&%}">address</label>
												<input type="text" name="balanceoff-address{&%}" id="balanceoff-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="t-balanceoff-address{&%}" type="submit" onclick="anhydrite('balanceoff', {&%}, 'balanceoff_');" value="Token balance" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</form>
									<span id="balanceoff_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">See earnings from address</h3>
									<form id="otherprofit{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xt-otherprofit">Address</label>
												<input type="text" name="b-otherprofit{&%}" id="b-otherprofit{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="bal-otherprofit{&%}" type="submit" onclick="anhydrite('otherprofit', {&%}, 'otherprofit_');" value="See earnings" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</form>
									<span id="otherprofit_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Transfer token</h3>
									<form id="{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xtrans-token">Token ID</label>
												<input type="text" name="transfer-token{&%}" id="transfer-token{&%}" value="" placeholder="0" />
											</div>
											<div class="field half">
												<label for="xlet-address{&%}">Address</label>
												<input type="text" name="transferfrom-address{&%}" id="transferfrom-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>
										</div>
										<ul class="actions">
											<li><input id="safetransferfrom{&%}" type="submit" onclick="anhydrite('safetransfer', {&%}, 'transfertoken_');" value="Transfer token" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</form>
									<span id="transfertoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>`
};
