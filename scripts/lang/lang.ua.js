const vhtml = {
	statusdiv2: `<div style="margin: 5px; color: black;">&%status <div>`,
	resultdiv:  `<div style="margin: 10px">{&%}<div>`,
	result1div: `<div style="margin: 10px">&%result1<div>`,
	result2div: `<div style="red: 10px">&%result1 &%result2<div>`,
	errorsdiv:  `<div style="margin: 10px; color: red; font-weight: 600;">&%result1<div>`,
        nullstatus: `&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;`
};
const pcol = {_1:'Пурпурна',_2:'Зелена',_3:'Жовта',_4:'Помаранчева',_5:'Червона',_6:'Тестова'};
const lang = {
    // *************** Status *************** //
    unavailable:  ' ...',
    waiting:      '<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; Очікування ...</h4>',
    basestatus:   vhtml.result1div.replace('&%result1', `Мережа: <b>{&%}</b></br>
                                                         Токен: <b>{&%}</b></br>
                                                         Контракт: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Всього токенів: <b>{&%}</b></br>
                                                         Вартість токену <b>{&%}</b> {&%}</br>
                                                         Ваша адреса: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>`),
    nologin:      vhtml.result1div.replace('&%result1', `Мережа: <b>{&%}</b></br>
                                                         Токен: <b>{&%}</b></br>
                                                         Контракт: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Всього токенів: <b>{&%}</b></br>
                                                         Вартість токену <b>{&%}</b> {&%}</br><b><font color="red">
                                                         Ви не підключені до блокчейну:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('login');">Підключитися до блокчейну</button></br>&nbsp;`),
    noinstall:    vhtml.result1div.replace('&%result1', `Мережа: <b>{&%}</b></br>
                                                         Контракт: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Вартість токену <b>{&%}</b> {&%}</br><b><font color="red">
                                                         У вас не встановлено MetaMask:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('link')">Встановити MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `Мережа: <b>{&%}</b></br>
                                                         Контракт: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Вартість токену <b>{&%}</b> {&%}</br><b><font color="red">
                                                         Ви підключились з мобільного пристрою! Для можливості працювати з пірамідами потрібен MetaMask,
                                                         якщо у вас він не встановлений, то:</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button" onclick="anhydrite('link')">Встановити MetaMask</button></br><b><font color="red"></li>
                                                         А якщо MetaMask уже встановлений на вашому пристрої, то відкрийте сайт за допомогою MetaMask браузера:</font></b>
                                                         <li><button class="button" onclick="anhydrite('link2')">Відкрити MetaMask браузер</button></li></br></ul>`),
    update:       vhtml.result1div.replace('&%result1', `Мережа: <b>{&%}</b></br>
                                                         Контракт: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Вартість токену <b>{&%}</b> {&%}</br><b><font color="red">
                                                         Якщо ви вже підключились до блокчейну, оновіть свій статус:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('load');">Оновити статус</button></br>&nbsp;`),
    // *************** Errors *************** //
    errorcode:     vhtml.errorsdiv.replace('&%result1', `Щось пішло не так... Помилка виконання коду:&nbsp; (&%result1)`),
    errornetid:    vhtml.errorsdiv.replace('&%result1', 'Помилка:&nbsp;  ви підключені до мережі "&%result1", відкрийте "MetaMask" та підключіться до потрібної мережі, після чого оновіть сторінку'),
    errnoconnect:  vhtml.errorsdiv.replace('&%result1', 'Помилка:&nbsp;  Відсутнє з\'єднання з блокчейном'),
    errnoallowed:  vhtml.errorsdiv.replace('&%result1', `Помилка:&nbsp;  Цей токен існує, але він недоступний для купівлі вами`),
    errtokenid_:   vhtml.errorsdiv.replace('&%result1', `Помилка:&nbsp;  Вы ввели невірний ID токену, він повинен бути числом від 1 до &%result1`),
    errtokenuint:  vhtml.errorsdiv.replace('&%result1', `Помилка:&nbsp;  Ви ввели нечисловий ID токену. Токен повинен бути числом`),
    errtokenaddr:  vhtml.errorsdiv.replace('&%result1', `Помилка:&nbsp;  Вы ввели недопустиму адресу, перепровірте її: (&%result1)`),
    errtokenperm:  vhtml.errorsdiv.replace('&%result1', `Помилка:&nbsp;  Немає доступных для купівлі токенів з циєю адресою: (&%result1)`),
    errcheckbox:   vhtml.errorsdiv.replace('&%result1', `Помилка:&nbsp;  Необхідно підтвердити свою згоду з: <u>Угодою користувача</u>`),
    errtransfer:   vhtml.errorsdiv.replace('&%result1', `Помилка:&nbsp;  Ви не можете передати токен <b>{&%}</b>, тому що він не належить вам`),
    // *************** Results *************** //
    reslengthtok: vhtml.result1div.replace('&%result1', `<b>Відсутні токени, що містять цю адресу</b>`),
    resultprofit: vhtml.result2div.replace('&%result1', `Всього зароблено: <b>&%result_</b> &%result2</br>Залишок на рахунку: <b>&%result1</b>`),
    resulttxhash: vhtml.result1div.replace('&%result1', `Транзакція відправлена, подивитись результат: <b><a href="&%result1/tx/&%result2" target="_blank" style="text-decoration: underline;">посилання на транзакцію</a></b>`),
    resulttxhasx: vhtml.result1div.replace('&%result1', `Транзакція підписана, подивитись результат: <b><a href="&%result1/tx/&%result2" target="_blank" style="text-decoration: underline;">посилання на транзакцію</a></b>`),
    resultholdt:  vhtml.result1div.replace('&%result1', `Режим "Холдер" <b>активовано</b>`),
    resultholdf:  vhtml.result1div.replace('&%result1', `Режим "Холдер" <b>вимкнено</b>`),
    resultchcidt: vhtml.result1div.replace('&%result1', `Токен <b>{&%}</b> доступний для купівлі з вашого акаунту`),
    resultchcidf: vhtml.result1div.replace('&%result1', `Токен <b>{&%}</b> не доступний для купівлі з вашого акаунту`),
    resultshorts: vhtml.result1div.replace('&%result1', `Найкоротший токен для купівлі з вашого акаунту: <b>&%result1</b>`),
    resultshort0: vhtml.result1div.replace('&%result1', `<b>Відсутній</b>`),
    resultlengt:  vhtml.result1div.replace('&%result1', `Довжина (кількість адрес) токену <b>&%result1</b> дорівнює <b>&%result2</b>`),
    resultlenloc: vhtml.result1div.replace('&%result1', `Довжина токену <b>&%result1</b> дорівнює: <b>&%result2</b>, вказана адреса знаходиться на <b>&%result3</b> позиції`),
    resultlenloc0:vhtml.result1div.replace('&%result1', `Довжина токену <b>&%result1</b> дорівнює: <b>&%result2</b>, адреса <b>&%result3</b> в його списку <b>відсутня</b>`),
    resultparent: vhtml.result1div.replace('&%result1', `Токен <b>&%result1</b> створений з токену <b>&%result2</b>`),
    resultparent0:vhtml.result1div.replace('&%result1', `Токен <b>&%result1</b> створений з <b>"нульового"</b> токену`),
    resultchild0: vhtml.result1div.replace('&%result1', `У токена <b>&%result1</b> дочірні токени <b>відсутніт</b>`),
    resultchild:  vhtml.result1div.replace('&%result1', `З токена <b>&%result1</b> було створено токени <b>&%result2;</b>`),
    resultowner:  vhtml.result1div.replace('&%result1', `Власником токену <b>&%result1</b> є акаунт: <b>&%result2</b>`),
    resultownto0: vhtml.result1div.replace('&%result1', `Акаунт: <b>&%result1</b> не володіє токенами`),
    resultowntok: vhtml.result1div.replace('&%result1', `Акаунт: <b>&%result1</b> володіє токенами: <b>&%result2</b>`),

    // *************** Pages *************** //

    all_pyramids_html: `
								<section>

								
								
									<h2 class="major">{&%arg1} Піраміда</h2>
								        <span id="status{&%}" class="image main" style='background: url("images/overlay.png")'>
                                                                        </span>
									<h3 class="major">Перевірка та зняття прибутку</h3>
									<form id="profit{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="profit{&%}" onclick="anhydrite('profit', {&%}, 'profit_{&%}');" value="Дивитися прибуток" /></li>
											<li><input type="button" id="wprofit{&%}" onclick="anhydrite('wprofit', {&%}, 'profit_{&%}');" value="Зняти прибуток" /></li>
										</ul>
									</form>
									<span id="profit_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Купівля випадкового токену</h3>
									<form id="buyrandom{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="buy-random{&%}" onclick="anhydrite('buyrandom', {&%}, 'buyrandom_{&%}');" value="Купити токен" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-random-check{&%}" name="buy-random-check{&%}">
												<label for=buy-random-check{&%}>Підтверджую</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Угода користувача</a></p>
											</div>
									</form>
									<span id="buyrandom_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Купівля токену по ID</h3>
									<form id="buyid{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbuy-id{&%}">Токен ID</label>
												<input type="text" name="get-buy-id{&%}" id="get-buy-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="buy-id{&%}" type="submit" onclick="anhydrite('buyid', {&%}, 'buyid_{&%}');" value="Купити токен" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-id-check{&%}" name="buy-id">
												<label for="buy-id-check{&%}">Підтверджую</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Угода користувача</a></p>
											</div>
									</form>
									<span id="buyid_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Купівля токену що містить адресу</h3>
									<form id="buyaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbuy-address{&%}">Адреса</label>
												<input id="get-buy-address{&%}" type="text" name="get-buy-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="buy-address{&%}" type="submit" onclick="anhydrite('buyaddress', {&%}, 'buyaddress_{&%}');" value="Купити токен" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-address-check{&%}" name="buy-id">
												<label for="buy-address-check{&%}">Підтверджую</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Угода користувача</a></p>
											</div>
									</form>
									<span id="buyaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Режим "Холдер"</h3>
									<form id="holder{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="hold{&%}" onclick="anhydrite('holder', {&%}, 'holder_{&%}');" value="Перевірка режиму Холдер" /></li>
											<li><input type="button" id="xhold{&%}" onclick="anhydrite('wholder', {&%}, 'holder_{&%}');" value="Зміна режиму Холдер" /></li>
										</ul>
									</form>
									<span id="holder_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Доступність токену</h3>
									<form id="checkid{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="check-id{&%}">Токен ID</label>
												<input type="text" name="check-id{&%}" id="check-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="token-check-id{&%}" type="submit" onclick="anhydrite('checkid', {&%}, 'checkid_{&%}');" value="Перевірити токен" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
									</form>
									<span id="checkid_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Токени з адресою</h3>
									<form id="alltokenaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="t-address{&%}">Адреса</label>
												<input type="text" name="t-tokens-address{&%}" id="t-tokens-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="all-tokens-address{&%}" type="submit" onclick="anhydrite('alltokenaddress', {&%}, 'alltokesnaddress_{&%}');" value="Перевірити адресу" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
									</form>
									<span id="alltokesnaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Найкоротший токен</h3>
									<form id="shortesttoken{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="shortest-token{&%}" onclick="anhydrite('shortesttoken', {&%}, 'shortesttoken_{&%}');" value="Показати найкоротший токен" /></li>
										</ul>
									</form>
									<span id="shortesttoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Найкоротший токен з адресою</h3>
									<form id="shortesaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="short-t-address{&%}">Адреса</label>
												<input type="text" name="shortes-address{&%}" id="shortes-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="t-shortes-address{&%}" type="submit" onclick="anhydrite('shortesaddress', {&%}, 'shortesaddress_{&%}');" value="Найкоротший токен" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
									</form>
									<span id="shortesaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Довжина токена</h3>
									<form id="lengthtoken{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="ss-length-token{&%}">Токен ID</label>
												<input type="text" name="s-length-token{&%}" id="s-length-token{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="aa-length-token{&%}" type="submit" onclick="anhydrite('lengthtoken', {&%}, 'lengthtoken_');" value="Довжина токена" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
									</form>
									<span id="lengthtoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Довжина токену та позиція адреси</h3>
									<form id="lengthtokenaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xtlength-token">Токен ID</label>
												<input type="text" name="length-token{&%}" id="length-token{&%}" value="" placeholder="0" />
											</div>
											<div class="field half">
												<label for="xlet-address{&%}">Адреса</label>
												<input type="text" name="length-token-address{&%}" id="length-token-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>
										</div>
										<ul class="actions">
											<li><input id="ll-token-address{&%}" type="submit" onclick="anhydrite('lengthtokenaddress', {&%}, 'lengthtokenaddress_');" value="Довжина токена" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
									</form>
									<span id="lengthtokenaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Батьківський токен</h3>
									<form id="parenttoken{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xparent-token-id{&%}">Токен ID</label>
												<input type="text" name="parent-token-id{&%}" id="parent-token-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="parent-token-id{&%}" type="submit" onclick="anhydrite('parenttoken', {&%}, 'parenttoken_');" value="Батьківський токен" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
									</form>
									<span id="parenttoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Дочірні токени</h3>
									<form id="childtokens{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xchild-owneroff-id{&%}">Токен ID</label>
												<input type="text" name="child-token-id{&%}" id="child-token-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="ci-token-id{&%}" type="submit" onclick="anhydrite('childtokens', {&%}, 'childtokens_');" value="Дочірні токени" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
									</form>
									<span id="childtokens_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Власник токену</h3>
									<form id="owneroff{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xowneroff-id{&%}">Токен ID</label>
												<input type="text" name="owneroff-id{&%}" id="owneroff-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="token-owneroff{&%}" type="submit" onclick="anhydrite('owneroff', {&%}, 'owneroff_');" value="Власник токену" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
									</form>
									<span id="owneroff_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Баланс токенів</h3>
									<form id="balanceoff{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbalanceoff-address{&%}">Адреса</label>
												<input type="text" name="balanceoff-address{&%}" id="balanceoff-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="t-balanceoff-address{&%}" type="submit" onclick="anhydrite('balanceoff', {&%}, 'balanceoff_');" value="Баланс токенів" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
									</form>
									<span id="balanceoff_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Дивитися прибутки адреси</h3>
									<form id="otherprofit{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xt-otherprofit">Адреса</label>
												<input type="text" name="b-otherprofit{&%}" id="b-otherprofit{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="bal-otherprofit{&%}" type="submit" onclick="anhydrite('otherprofit', {&%}, 'otherprofit_');" value="Дивитися прибутки" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
									</form>
									<span id="otherprofit_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Передати токен</h3>
									<form id="{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xtrans-token">Токен ID</label>
												<input type="text" name="transfer-token{&%}" id="transfer-token{&%}" value="" placeholder="0" />
											</div>
											<div class="field half">
												<label for="xlet-address{&%}">Адреса</label>
												<input type="text" name="transferfrom-address{&%}" id="transferfrom-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>
										</div>
										<ul class="actions">
											<li><input id="safetransferfrom{&%}" type="submit" onclick="anhydrite('safetransfer', {&%}, 'transfertoken_');" value="Передати токен" /></li>
											<li><input type="reset" value="Очистити" /></li>
										</ul>
									</form>
									<span id="transfertoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>`
};
