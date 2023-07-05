const vhtml = {
	statusdiv2: `<div style="margin: 5px; color: black;">&%status <div>`,
	resultdiv:  `<div style="margin: 10px">{&%}<div>`,
	result1div: `<div style="margin: 10px">&%result1<div>`,
	result2div: `<div style="red: 10px">&%result1 &%result2<div>`,
	errorsdiv:  `<div style="margin: 10px; color: red; font-weight: 600;">&%result1<div>`,
        nullstatus: `&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;`
};
const pcol = {_1:'Viola',_2:'Verde',_3:'Gialla',_4:'Arancione',_5:'Rossa',_6:'Prova'};
const lang = {
    // *************** Status *************** //
    unavailable:  ' ...',
    waiting:      '<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; Attesa ...</h4>',
    basestatus:   vhtml.result1div.replace('&%result1', `Rete: <b>{&%}</b></br>
                                                         Token: <b>{&%}</b></br>
                                                         Contratto: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Numero totale dei token: <b>{&%}</b></br>
                                                         Valore di un token <b>{&%}</b> {&%}</br>
                                                         Il tuo  indirizzo: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>`),
    nologin:      vhtml.result1div.replace('&%result1', `Rete: <b>{&%}</b></br>
                                                         Token: <b>{&%}</b></br>
                                                         Contratto: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Numero totale dei token: <b>{&%}</b></br>
                                                         Valore di un token <b>{&%}</b> {&%}</br><b><font color="red">
                                                         Non sei connesso alla blockchain:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('login');">Connettersi alla blockchain</button></br>&nbsp;`),
    noinstall:    vhtml.result1div.replace('&%result1', `Rete: <b>{&%}</b></br>
                                                         Contratto: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Valore di un token <b>{&%}</b> {&%}</br><b><font color="red">
                                                         Non è instrallato MetaMask:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('link')">Installare MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `Rete: <b>{&%}</b></br>
                                                         Contratto: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Valore di un token <b>{&%}</b> {&%}</br><b><font color="red">
                                                         Sei connesso da un dispositivo mobile! Per poter lavorare con le piramidi è necessario avere MetaMask,
                                                         se non lo hai installato, allora :</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button" onclick="anhydrite('link')">Installare MetaMask</button></li><b><font color="red">
                                                         Se MetaMask è già installato sul tuo dispositivo,è necessario accendere al sito attraverso il browser di MetaMask:</font></b>
                                                         <li><button class="button" onclick="anhydrite('link2')">Accendere al browser MetaMask</button></li></br></ul></form>`),
    update:       vhtml.result1div.replace('&%result1', `Rete: <b>{&%}</b></br>
                                                         Contratto: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Valore di un token <b>{&%}</b> {&%}</br><b><font color="red">
                                                         Se sei già connesso alla blockchain, aggiorna il tuo stato:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('load');">Aggiorna il tuo stato</button></br>&nbsp;`),
    // *************** Errors *************** //
    errorcode:     vhtml.errorsdiv.replace('&%result1', `Qualcosa è andato storto... Errore durante l'esecuzione del codice:&nbsp; (&%result1)`),
    errornetid:    vhtml.errorsdiv.replace('&%result1', 'Errore:&nbsp;  sei connesso alla rete "&%result1", accendere "MetaMask" connettersi alla rete desiderata, quindi aggiornare la pagina'),
    errnoconnect:  vhtml.errorsdiv.replace('&%result1', 'Errore:&nbsp;  Nessuna connessione alla blockchain'),
    errnoallowed:  vhtml.errorsdiv.replace('&%result1', `Errore:&nbsp;  Questo token esiste, ma non è disponibile per l'acquisto`),
    errtokenid_:   vhtml.errorsdiv.replace('&%result1', `Errore:&nbsp;  Hai inserito un ID token errato, il token deve essere un numero da 1 a &%result1`),
    errtokenuint:  vhtml.errorsdiv.replace('&%result1', `Errore:&nbsp;  Hai inserito un ID token non numerico. Il token deve essere un numero`),
    errtokenaddr:  vhtml.errorsdiv.replace('&%result1', `Errore:&nbsp;  Hai inserito un indirizzo non valido, ricontrollalo: (&%result1)`),
    errtokenperm:  vhtml.errorsdiv.replace('&%result1', `Errore:&nbsp;  Non ci sono token disponibili per l'acquisto con questo indirizzo: (&%result1)`),
    errcheckbox:   vhtml.errorsdiv.replace('&%result1', `Errore:&nbsp;  È necessario confermare il tuo consenso attraverso il: <u>Contratto utente</u>`),
    errtransfer:   vhtml.errorsdiv.replace('&%result1', `Errore:&nbsp;  Non puoi trasferire il token <b>{&%}</b>, perchè non appartiene a te`),
    // *************** Results *************** //
    reslengthtok: vhtml.result1div.replace('&%result1', `<b>Non ci sono token contenenti questo indirizzo</b>`),
    resultprofit: vhtml.result2div.replace('&%result1', `Il guadagno totale è: <b>&%result_</b> &%result2</br>Il rimanente sul conto è: <b>&%result1</b>`),
    resulttxhash: vhtml.result1div.replace('&%result1', `La transazione è stata inviata, vedi il risultato: <b><a href="&%result1/tx/&%result2" target="_blank" style="text-decoration: underline;">collegamento alla transazione</a></b>`),
    resulttxhasx: vhtml.result1div.replace('&%result1', `La transazione è stata sottoscritta, vedi il risultato: <b><a href="&%result1/tx/&%result2" target="_blank" style="text-decoration: underline;">collegamento alla transazione</a></b>`),
    resultholdt:  vhtml.result1div.replace('&%result1', `Modalità "Holder" <b>attivata</b>`),
    resultholdf:  vhtml.result1div.replace('&%result1', `Modalità "Holder" <b>disattivata</b>`),
    resultchcidt: vhtml.result1div.replace('&%result1', `Token <b>{&%}</b> disponibile per l'acquisto dal tuo account`),
    resultchcidf: vhtml.result1div.replace('&%result1', `Token <b>{&%}</b> non disponibile per l'acquisto dal tuo account`),
    resultshorts: vhtml.result1div.replace('&%result1', `Il token più corto da acquistare dal tuo account: <b>&%result1</b>`),
    resultshort0: vhtml.result1div.replace('&%result1', `<b>Non disponibile</b>`),
    resultlengt:  vhtml.result1div.replace('&%result1', `Lunghezza (numero di indirizzi) del token <b>&%result1</b> è uguale a <b>&%result2</b>`),
    resultlenloc: vhtml.result1div.replace('&%result1', `Lunghezza del token <b>&%result1</b> è uguale a: <b>&%result2</b>, l'indirizzo indicato si trova sulla posizione <b>&%result3</b>`),
    resultlenloc0:vhtml.result1div.replace('&%result1', `Lunghezza del token <b>&%result1</b> è ugale a: <b>&%result2</b>, l'indirizzo <b>&%result3</b> nella lista <b>è assente</b>`),
    resultparent: vhtml.result1div.replace('&%result1', `Il token <b>&%result1</b> è creato dal token <b>&%result2</b>`),
    resultparent0:vhtml.result1div.replace('&%result1', `Il token <b>&%result1</b> è creato da un token <b>"nullo"</b>`),
    resultchild0: vhtml.result1div.replace('&%result1', `Il token <b>&%result1</b> <b>non ha</b> token figli`),
    resultchild:  vhtml.result1div.replace('&%result1', `Dal token <b>&%result1</b> è stato creato il token <b>&%result2;</b>`),
    resultowner:  vhtml.result1div.replace('&%result1', `Il proprietario del token <b>&%result1</b> risulta essere l'account: <b>&%result2</b>`),
    resultownto0: vhtml.result1div.replace('&%result1', `L'account: <b>&%result1</b> non ha token`),
    resultowntok: vhtml.result1div.replace('&%result1', `L'account: <b>&%result1</b> ha token: <b>&%result2</b>`),

    // *************** Pages *************** //

    all_pyramids_html: `
								<section>

								
								
									<h2 class="major">Piramide {&%arg1}</h2>
								        <span id="status{&%}" class="image main" style='background: url("images/overlay.png")'>
                                                                        </span>
									<h3 class="major">Controlla e preleva i profitti</h3>
									<form id="profit{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="profit{&%}" onclick="anhydrite('profit', {&%}, 'profit_{&%}');" value="Vedere i profitti" /></li>
											<li><input type="button" id="wprofit{&%}" onclick="anhydrite('wprofit', {&%}, 'profit_{&%}');" value="Prelevare i profitti" /></li>
										</ul>
									</form>
									<span id="profit_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Acquista un token casuale</h3>
									<form id="buyrandom{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="buy-random{&%}" onclick="anhydrite('buyrandom', {&%}, 'buyrandom_{&%}');" value="Acquista il token" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-random-check{&%}" name="buy-random-check{&%}">
												<label for=buy-random-check{&%}>Confermo</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Contratto utente</a></p>
											</div>
									</form>
									<span id="buyrandom_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Acquista un token tramite ID</h3>
									<form id="buyid{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbuy-id{&%}">ID token </label>
												<input type="text" name="get-buy-id{&%}" id="get-buy-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="buy-id{&%}" type="submit" onclick="anhydrite('buyid', {&%}, 'buyid_{&%}');" value="Acquista il token" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-id-check{&%}" name="buy-id">
												<label for="buy-id-check{&%}">Confermo</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Contratto utente</a></p>
											</div>
									</form>
									<span id="buyid_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Acquista un token con indirizzo</h3>
									<form id="buyaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbuy-address{&%}">Indirizzo</label>
												<input id="get-buy-address{&%}" type="text" name="get-buy-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="buy-address{&%}" type="submit" onclick="anhydrite('buyaddress', {&%}, 'buyaddress_{&%}');" value="Acquista il token" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-address-check{&%}" name="buy-id">
												<label for="buy-address-check{&%}">Confermo</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Contratto utente</a></p>
											</div>
									</form>
									<span id="buyaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Modalità "Holder"</h3>
									<form id="holder{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="hold{&%}" onclick="anhydrite('holder', {&%}, 'holder_{&%}');" value="Controllo modalità Holder" /></li>
											<li><input type="button" id="xhold{&%}" onclick="anhydrite('wholder', {&%}, 'holder_{&%}');" value="Cambio modalità Holder" /></li>
										</ul>
									</form>
									<span id="holder_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Disponibilità dei token</h3>
									<form id="checkid{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="check-id{&%}">ID token</label>
												<input type="text" name="check-id{&%}" id="check-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="token-check-id{&%}" type="submit" onclick="anhydrite('checkid', {&%}, 'checkid_{&%}');" value="Controlla il token" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
									</form>
									<span id="checkid_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token con indirizzo</h3>
									<form id="alltokenaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="t-address{&%}">Indirizzo</label>
												<input type="text" name="t-tokens-address{&%}" id="t-tokens-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="all-tokens-address{&%}" type="submit" onclick="anhydrite('alltokenaddress', {&%}, 'alltokesnaddress_{&%}');" value="Controlla indirizzo" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
									</form>
									<span id="alltokesnaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token più corto</h3>
									<form id="shortesttoken{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="shortest-token{&%}" onclick="anhydrite('shortesttoken', {&%}, 'shortesttoken_{&%}');" value="Mostra il token più corto" /></li>
										</ul>
									</form>
									<span id="shortesttoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token più corto con indirizzo</h3>
									<form id="shortesaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="short-t-address{&%}">Indirizzo</label>
												<input type="text" name="shortes-address{&%}" id="shortes-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="t-shortes-address{&%}" type="submit" onclick="anhydrite('shortesaddress', {&%}, 'shortesaddress_{&%}');" value="Token più corto" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
									</form>
									<span id="shortesaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Lunghezza del token</h3>
									<form id="lengthtoken{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="ss-length-token{&%}">ID Token</label>
												<input type="text" name="s-length-token{&%}" id="s-length-token{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="aa-length-token{&%}" type="submit" onclick="anhydrite('lengthtoken', {&%}, 'lengthtoken_');" value="Lunghezza del token" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
									</form>
									<span id="lengthtoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h4 class="major">Lunghezza e posizione dell&#39;indirizzo</h4>
									<form id="lengthtokenaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xtlength-token">ID Токен</label>
												<input type="text" name="length-token{&%}" id="length-token{&%}" value="" placeholder="0" />
											</div>
											<div class="field half">
												<label for="xlet-address{&%}">Indirizzo</label>
												<input type="text" name="length-token-address{&%}" id="length-token-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>
										</div>
										<ul class="actions">
											<li><input id="ll-token-address{&%}" type="submit" onclick="anhydrite('lengthtokenaddress', {&%}, 'lengthtokenaddress_');" value="Lunghezza del token" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
									</form>
									<span id="lengthtokenaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token padre</h3>
									<form id="parenttoken{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xparent-token-id{&%}">ID token </label>
												<input type="text" name="parent-token-id{&%}" id="parent-token-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="parent-token-id{&%}" type="submit" onclick="anhydrite('parenttoken', {&%}, 'parenttoken_');" value="Token padre" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
									</form>
									<span id="parenttoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token figli</h3>
									<form id="childtokens{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xchild-owneroff-id{&%}">ID token</label>
												<input type="text" name="child-token-id{&%}" id="child-token-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="ci-token-id{&%}" type="submit" onclick="anhydrite('childtokens', {&%}, 'childtokens_');" value="Token figli" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
									</form>
									<span id="childtokens_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Proprietario del token</h3>
									<form id="owneroff{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xowneroff-id{&%}">ID Token</label>
												<input type="text" name="owneroff-id{&%}" id="owneroff-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="token-owneroff{&%}" type="submit" onclick="anhydrite('owneroff', {&%}, 'owneroff_');" value="Proprietario del token" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
									</form>
									<span id="owneroff_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Saldo dei token</h3>
									<form id="balanceoff{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbalanceoff-address{&%}">Indirizzo</label>
												<input type="text" name="balanceoff-address{&%}" id="balanceoff-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="t-balanceoff-address{&%}" type="submit" onclick="anhydrite('balanceoff', {&%}, 'balanceoff_');" value="Saldo dei token" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
									</form>
									<span id="balanceoff_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Vedere i profitti dell&#39;indirizzo</h3>
									<form id="otherprofit{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xt-otherprofit">Indirizzo</label>
												<input type="text" name="b-otherprofit{&%}" id="b-otherprofit{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="bal-otherprofit{&%}" type="submit" onclick="anhydrite('otherprofit', {&%}, 'otherprofit_');" value="Vedere i profitti" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
									</form>
									<span id="otherprofit_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Trasferire il token</h3>
									<form id="{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xtrans-token">ID token</label>
												<input type="text" name="transfer-token{&%}" id="transfer-token{&%}" value="" placeholder="0" />
											</div>
											<div class="field half">
												<label for="xlet-address{&%}">Indirizzo</label>
												<input type="text" name="transferfrom-address{&%}" id="transferfrom-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>
										</div>
										<ul class="actions">
											<li><input id="safetransferfrom{&%}" type="submit" onclick="anhydrite('safetransfer', {&%}, 'transfertoken_');" value="Trasferite il token" /></li>
											<li><input type="reset" value="Ripristina" /></li>
										</ul>
									</form>
									<span id="transfertoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>`
};
