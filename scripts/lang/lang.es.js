const vhtml = {
	statusdiv2: `<div style="margin: 5px; color: black;">&%status <div>`,
	resultdiv:  `<div style="margin: 10px">{&%}<div>`,
	result1div: `<div style="margin: 10px">&%result1<div>`,
	result2div: `<div style="red: 10px">&%result1 &%result2<div>`,
	errorsdiv:  `<div style="margin: 10px; color: red; font-weight: 600;">&%result1<div>`,
        nullstatus: `&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;`
};
const pcol = {_1:'Púrpura',_2:'Verde',_3:'Amarilla',_4:'Naranja',_5:'Roja',_6:'Prueba'};
const lang = {
    // *************** Status *************** //
    unavailable:  ' ...',
    waiting:      '<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; Espera ...</h4>',
    basestatus:   vhtml.result1div.replace('&%result1', `Red: <b>{&%}</b></br>
                                                         Token: <b>{&%}</b></br>
                                                         Contrato: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Número total de tokens: <b>{&%}</b></br>
                                                         Valor de un token <b>{&%}</b> {&%}</br>
                                                         Su dirección: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>`),
    nologin:      vhtml.result1div.replace('&%result1', `Red: <b>{&%}</b></br>
                                                         Token: <b>{&%}</b></br>
                                                         Contrato: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Nùmero total de tokens: <b>{&%}</b></br>
                                                         Valor de un token <b>{&%}</b> {&%}</br><b><font color="red">
                                                         No estás conectado a la cadena de bloques:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('login');">conectarse a la cadena de bloques</button></br>&nbsp;`),
    noinstall:    vhtml.result1div.replace('&%result1', `Red: <b>{&%}</b></br>
                                                         Contrato: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Valor de un token <b>{&%}</b> {&%}</br><b><font color="red">
                                                         MetaMask no está instalado:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('link')">Instalar MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `Red: <b>{&%}</b></br>
                                                         Contrato: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Valor de un token <b>{&%}</b> {&%}</br><b><font color="red">
                                                         ¡Estás conectado desde un dispositivo móvil! Para trabajar con pirámides necesitas tener MetaMask, 
														 si no la has instalado, entonces :</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button" onclick="anhydrite('link')">Instala MetaMask</button></li><b><font color="red">
                                                         Si MetaMask ya está instalado en su dispositivo, tiene que acceder al sitio a través del browser de MetaMask:</font></b>
                                                         <li><button class="button" onclick="anhydrite('link2')">Inicie sesión en el navegador MetaMask</button></li></br></ul></form>`),
    update:       vhtml.result1div.replace('&%result1', `Red: <b>{&%}</b></br>
                                                         Contrato: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Valor de un token <b>{&%}</b> {&%}</br><b><font color="red">
                                                         Si ya está conectado a la cadena de bloques, actualice su estado:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('load');">Actualice su estado</button></br>&nbsp;`),
    // *************** Errors *************** //
    errorcode:     vhtml.errorsdiv.replace('&%result1', `Algo salió mal... Error al ejecutar código:&nbsp; (&%result1)`),
    errornetid:    vhtml.errorsdiv.replace('&%result1', 'Error:&nbsp;  Está conectado a la red "&%result1", Inicie sesión en "MetaMask" conéctese a la red que desee y, a continuación, actualice la página'),
    errnoconnect:  vhtml.errorsdiv.replace('&%result1', 'Error:&nbsp;  Sin conexión a la cadena de bloques'),
    errnoallowed:  vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  Este token existe, pero no está disponible para su compra`),
    errtokenid_:   vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  Ingresó una ID de token incorrecta, el token debe ser un número de 1 a &%result1`),
    errtokenuint:  vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  Has introducido una ID de token no numérica. El token debe ser un número`),
    errtokenaddr:  vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  Has introducido una dirección no válida, vuelve a comprobarla: (&%result1)`),
    errtokenperm:  vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  No hay tokens disponibles para comprar con esta dirección: (&%result1)`),
    errcheckbox:   vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  Es necesario confirmar su consentimiento a través del: <u>Acuerdo de usuario</u>`),
    errtransfer:   vhtml.errorsdiv.replace('&%result1', `Error:&nbsp;  No puedes transferir el token <b>{&%}</b>, Porque no te pertenece`),
    // *************** Results *************** //
    reslengthtok: vhtml.result1div.replace('&%result1', `<b>No hay tokens que contengan esta dirección</b>`),
    resultprofit: vhtml.result2div.replace('&%result1', `La ganancia total es: <b>&%result_</b> &%result2</br>El resto de la cuenta es: <b>&%result1</b>`),
    resulttxhash: vhtml.result1div.replace('&%result1', `La transacción ha sido enviada, ver el resultado: <b><a href="&%result1/tx/&%result2" target="_blank" style="text-decoration: underline;">enlace a la transacción</a></b>`),
    resulttxhasx: vhtml.result1div.replace('&%result1', `La transacción ha sido firmada, ver el resultado: <b><a href="&%result1/tx/&%result2" target="_blank" style="text-decoration: underline;">enlace a la transacción</a></b>`),
    resultholdt:  vhtml.result1div.replace('&%result1', `Modalidad "Holder" <b>activada</b>`),
    resultholdf:  vhtml.result1div.replace('&%result1', `Modalidad "Holder" <b>desactivada</b>`),
    resultchcidt: vhtml.result1div.replace('&%result1', `El token <b>{&%}</b> es disponible para la compra desde su cuenta`),
    resultchcidf: vhtml.result1div.replace('&%result1', `El token <b>{&%}</b> no es disponible para la compra desde su cuenta`),
    resultshorts: vhtml.result1div.replace('&%result1', `El token más corto para comprar desde tu cuenta: <b>&%result1</b>`),
    resultshort0: vhtml.result1div.replace('&%result1', `<b>Indisponible</b>`),
    resultlengt:  vhtml.result1div.replace('&%result1', `La longitud del token (número de direcciones) <b>&%result1</b> es igual a <b>&%result2</b>`),
    resultlenloc: vhtml.result1div.replace('&%result1', `La longitud del token <b>&%result1</b> es igual a: <b>&%result2</b>, la dirección indicada está en la ubicación <b>&%result3</b>`),
    resultlenloc0:vhtml.result1div.replace('&%result1', `La longitud del token <b>&%result1</b> es igual a: <b>&%result2</b>, la dirección <b>&%result3</b> en la lista <b>está ausente</b>`),
    resultparent: vhtml.result1div.replace('&%result1', `El token <b>&%result1</b> se crea desde el token <b>&%result2</b>`),
    resultparent0:vhtml.result1div.replace('&%result1', `El token <b>&%result1</b> se crea desde el token <b>"nulo"</b>`),
    resultchild0: vhtml.result1div.replace('&%result1', `El token <b>&%result1</b> <b>no tiene</b> tokens hijos`),
    resultchild:  vhtml.result1div.replace('&%result1', `Desde el token <b>&%result1</b> se creó el token <b>&%result2;</b>`),
    resultowner:  vhtml.result1div.replace('&%result1', `El propietario del token <b>&%result1</b> resulta ser la cuenta: <b>&%result2</b>`),
    resultownto0: vhtml.result1div.replace('&%result1', `La cuenta: <b>&%result1</b> no tiene tokens`),
    resultowntok: vhtml.result1div.replace('&%result1', `La cuenta: <b>&%result1</b> tiene tokens: <b>&%result2</b>`),

    // *************** Pages *************** //

    all_pyramids_html: `
								<section>

								
								
									<h2 class="major">Pirámide {&%arg1}</h2>
								        <span id="status{&%}" class="image main" style='background: url("images/overlay.png")'>
                                                                        </span>
									<h3 class="major">Comproba y retira beneficios</h3>
									<form id="profit{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="profit{&%}" onclick="anhydrite('profit', {&%}, 'profit_{&%}');" value="Ver beneficios" /></li>
											<li><input type="button" id="wprofit{&%}" onclick="anhydrite('wprofit', {&%}, 'profit_{&%}');" value="Retirar beneficios" /></li>
										</ul>
									</form>
									<span id="profit_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Compra un token aleatorio</h3>
									<form id="buyrandom{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="buy-random{&%}" onclick="anhydrite('buyrandom', {&%}, 'buyrandom_{&%}');" value="Comprar el token" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-random-check{&%}" name="buy-random-check{&%}">
												<label for=buy-random-check{&%}>Confirma</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Acuerdo de usuario</a></p>
											</div>
									</form>
									<span id="buyrandom_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Compra un token a través de una ID</h3>
									<form id="buyid{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbuy-id{&%}">ID token </label>
												<input type="text" name="get-buy-id{&%}" id="get-buy-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="buy-id{&%}" type="submit" onclick="anhydrite('buyid', {&%}, 'buyid_{&%}');" value="Comprar el token" /></li>
											<li><input type="reset" value="Restaura" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-id-check{&%}" name="buy-id">
												<label for="buy-id-check{&%}">Confirma</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Contrato de usuario</a></p>
											</div>
									</form>
									<span id="buyid_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Compra un token con dirección</h3>
									<form id="buyaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbuy-address{&%}">Dirección</label>
												<input id="get-buy-address{&%}" type="text" name="get-buy-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="buy-address{&%}" type="submit" onclick="anhydrite('buyaddress', {&%}, 'buyaddress_{&%}');" value="Comprar el token" /></li>
											<li><input type="reset" value="Restaura" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-address-check{&%}" name="buy-id">
												<label for="buy-address-check{&%}">Confirma</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Contrato de usuario</a></p>
											</div>
									</form>
									<span id="buyaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Modalidad "Holder"</h3>
									<form id="holder{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="hold{&%}" onclick="anhydrite('holder', {&%}, 'holder_{&%}');" value="Controlla modalidad Holder" /></li>
											<li><input type="button" id="xhold{&%}" onclick="anhydrite('wholder', {&%}, 'holder_{&%}');" value="Cambia modalidad Holder" /></li>
										</ul>
									</form>
									<span id="holder_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Disponibilidad de tokens</h3>
									<form id="checkid{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="check-id{&%}">ID token</label>
												<input type="text" name="check-id{&%}" id="check-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="token-check-id{&%}" type="submit" onclick="anhydrite('checkid', {&%}, 'checkid_{&%}');" value="Comprobar el token" /></li>
											<li><input type="reset" value="Restaura" /></li>
										</ul>
									</form>
									<span id="checkid_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token con dirección</h3>
									<form id="alltokenaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="t-address{&%}">Dirección</label>
												<input type="text" name="t-tokens-address{&%}" id="t-tokens-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="all-tokens-address{&%}" type="submit" onclick="anhydrite('alltokenaddress', {&%}, 'alltokesnaddress_{&%}');" value="Comprobar dirección" /></li>
											<li><input type="reset" value="Restaura" /></li>
										</ul>
									</form>
									<span id="alltokesnaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token más corto</h3>
									<form id="shortesttoken{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="shortest-token{&%}" onclick="anhydrite('shortesttoken', {&%}, 'shortesttoken_{&%}');" value="Muestra el token más corto" /></li>
										</ul>
									</form>
									<span id="shortesttoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Token más corto con dirección</h3>
									<form id="shortesaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="short-t-address{&%}">Dirección</label>
												<input type="text" name="shortes-address{&%}" id="shortes-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="t-shortes-address{&%}" type="submit" onclick="anhydrite('shortesaddress', {&%}, 'shortesaddress_{&%}');" value="Token más corto" /></li>
											<li><input type="reset" value="Restaurar" /></li>
										</ul>
									</form>
									<span id="shortesaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Longitud del token</h3>
									<form id="lengthtoken{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="ss-length-token{&%}">ID Token</label>
												<input type="text" name="s-length-token{&%}" id="s-length-token{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="aa-length-token{&%}" type="submit" onclick="anhydrite('lengthtoken', {&%}, 'lengthtoken_');" value="Longitud del token" /></li>
											<li><input type="reset" value="Restaurar" /></li>
										</ul>
									</form>
									<span id="lengthtoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h4 class="major">Longitud y ubicación de la dirección</h4>
									<form id="lengthtokenaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xtlength-token">ID token </label>
												<input type="text" name="length-token{&%}" id="length-token{&%}" value="" placeholder="0" />
											</div>
											<div class="field half">
												<label for="xlet-address{&%}">Dirección</label>
												<input type="text" name="length-token-address{&%}" id="length-token-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>
										</div>
										<ul class="actions">
											<li><input id="ll-token-address{&%}" type="submit" onclick="anhydrite('lengthtokenaddress', {&%}, 'lengthtokenaddress_');" value="Longitud del token" /></li>
											<li><input type="reset" value="Restaura" /></li>
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
											<li><input type="reset" value="Restaurar" /></li>
										</ul>
									</form>
									<span id="parenttoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Tokens hijos</h3>
									<form id="childtokens{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xchild-owneroff-id{&%}">ID token</label>
												<input type="text" name="child-token-id{&%}" id="child-token-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="ci-token-id{&%}" type="submit" onclick="anhydrite('childtokens', {&%}, 'childtokens_');" value="Tokens hijos" /></li>
											<li><input type="reset" value="Restaura" /></li>
										</ul>
									</form>
									<span id="childtokens_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Propietario del token</h3>
									<form id="owneroff{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xowneroff-id{&%}">ID Token</label>
												<input type="text" name="owneroff-id{&%}" id="owneroff-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="token-owneroff{&%}" type="submit" onclick="anhydrite('owneroff', {&%}, 'owneroff_');" value="Propietario del token" /></li>
											<li><input type="reset" value="Restaurar" /></li>
										</ul>
									</form>
									<span id="owneroff_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Saldo de tokens</h3>
									<form id="balanceoff{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbalanceoff-address{&%}">Dirección</label>
												<input type="text" name="balanceoff-address{&%}" id="balanceoff-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="t-balanceoff-address{&%}" type="submit" onclick="anhydrite('balanceoff', {&%}, 'balanceoff_');" value="Saldo de tokens" /></li>
											<li><input type="reset" value="Restaura" /></li>
										</ul>
									</form>
									<span id="balanceoff_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Ver los beneficios de la dirección</h3>
									<form id="otherprofit{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xt-otherprofit">Dirección</label>
												<input type="text" name="b-otherprofit{&%}" id="b-otherprofit{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="bal-otherprofit{&%}" type="submit" onclick="anhydrite('otherprofit', {&%}, 'otherprofit_');" value="Ver beneficios" /></li>
											<li><input type="reset" value="Restaura" /></li>
										</ul>
									</form>
									<span id="otherprofit_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Transferir el token</h3>
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
											<li><input id="safetransferfrom{&%}" type="submit" onclick="anhydrite('safetransfer', {&%}, 'transfertoken_');" value="Transferir el token" /></li>
											<li><input type="reset" value="Restaurar" /></li>
										</ul>
									</form>
									<span id="transfertoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>`
};
