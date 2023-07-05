const vhtml = {
	statusdiv2: `<div style="margin: 5px; color: black;">&%status <div>`,
	resultdiv:  `<div style="margin: 10px">{&%}<div>`,
	result1div: `<div style="margin: 10px">&%result1<div>`,
	result2div: `<div style="red: 10px">&%result1 &%result2<div>`,
	errorsdiv:  `<div style="margin: 10px; color: red; font-weight: 600;">&%result1<div>`,
	nullstatus: `&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;`
};
const pcol = {_1:'Фиолетовая',_2:'Зеленая',_3:'Желтая',_4:'Оранжевая',_5:'Красная',_6:'Тестовая'};
const lang = {
    // *************** Status *************** //
    unavailable:  ' ...',
    waiting:      '<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; Ожидание ...</h4>',
    basestatus:   vhtml.result1div.replace('&%result1', `Сеть: <b>{&%}</b></br>
                                                         Токен: <b>{&%}</b></br>
                                                         Контракт: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Всего токенов: <b>{&%}</b></br>
                                                         Цена токена <b>{&%}</b> {&%}</br>
                                                         Ваш адрес: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>`),
    nologin:      vhtml.result1div.replace('&%result1', `Сеть: <b>{&%}</b></br>
                                                         Токен: <b>{&%}</b></br>
                                                         Контракт: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Всего токенов: <b>{&%}</b></br>
                                                         Цена токена <b>{&%}</b> {&%}</br><b><font color="red">
                                                         Вы не подключены к блокчейну:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('login');">Подключиться к блокчейну</button></br>&nbsp;`),
    noinstall:    vhtml.result1div.replace('&%result1', `Сеть: <b>{&%}</b></br>
                                                         Контракт: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Цена токена <b>{&%}</b> {&%}</br><b><font color="red">
                                                         У вас не установлен MetaMask:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('link')">Установить MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `Сеть: <b>{&%}</b></br>
                                                         Контракт: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Цена токена <b>{&%}</b> {&%}</br><b><font color="red">
                                                         Вы подключились с мобильного устройства! Для возможности работать с пирамидами нужен MetaMask,
                                                         если он у вас не установлен, то:</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button"  onclick="anhydrite('link')">Установить MetaMask</button></li><b><font color="red">
                                                         А если MetaMask уже установлен на вашем устройстве, то откройте сайт с помощью MetaMask браузера:</font></b>
                                                         <li><button class="button" onclick="anhydrite('link2')">Открыть MetaMask браузер</button></li></br></ul></form>`),
    update:       vhtml.result1div.replace('&%result1', `Сеть: <b>{&%}</b></br>
                                                         Контракт: <b><a href="{&%}/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         Цена токена <b>{&%}</b> {&%}</br><b><font color="red">
                                                         Если вы уже подключены к блокчейну, обновите свой статус:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('load');">Оновити статус</button></br>&nbsp;`),
    // *************** Errors *************** //
    errorcode:     vhtml.errorsdiv.replace('&%result1', `Что-то пошло не так... Ошибка исполнения кода:&nbsp; (&%result1)`),
    errornetid:    vhtml.errorsdiv.replace('&%result1', 'Ошибка:&nbsp;  Вы не подключены к сети "&%result1", откройте "MetaMask" и подключитесь к нужной сети, после этого обновите страницу'),
    errnoconnect:  vhtml.errorsdiv.replace('&%result1', 'Ошибка:&nbsp;  Отсутствует соединение с блокчейном'),
    errnoallowed:  vhtml.errorsdiv.replace('&%result1', `Ошибка:&nbsp;  Этот токен существует, но он недоступен для покупки вами `),
    errtokenid_:   vhtml.errorsdiv.replace('&%result1', `Ошибка:&nbsp;  Вы ввели неверный ID токена, он должен быть числом  от 1 до &%result1`),
    errtokenuint:  vhtml.errorsdiv.replace('&%result1', `Ошибка:&nbsp;  Вы ввели не числовой ID токена. Токен должен быть числом`),
    errtokenaddr:  vhtml.errorsdiv.replace('&%result1', `Ошибка:&nbsp;  Вы ввели недопустимый адрес, перепроверьте его: (&%result1)`),
    errtokenperm:  vhtml.errorsdiv.replace('&%result1', `Ошибка:&nbsp;  Нет доступных токенов для покупки с этим адресом: (&%result1)`),
    errcheckbox:   vhtml.errorsdiv.replace('&%result1', `Ошибка:&nbsp;  Необходимо подтвердить своё согласие с: <u>Пользовательским соглашением</u>`),
    // *************** Results *************** //
    reslengthtok: vhtml.result1div.replace('&%result1', `<b>Отсутствуют токены, которые имеют этот адрес </b>`),
    resultprofit: vhtml.result2div.replace('&%result1', `Всего заработано: <b>&%result_</b> &%result2</br>Отстаток на счету: <b>&%result1</b>`),
    resulttxhash: vhtml.result1div.replace('&%result1', `Транзакция отправлена, посмотреть результат: <b><a href="&%result1/tx/&%result2" target="_blank" style="text-decoration: underline;">ссылка  на транзакцию</a></b>`),
    resulttxhasx: vhtml.result1div.replace('&%result1', `Транзакция подписана, посмотреть результат: <b><a href="&%result1/tx/&%result2" target="_blank" style="text-decoration: underline;">ссылка  на транзакцию</a></b>`),
    resultholdt:  vhtml.result1div.replace('&%result1', `Режим "Холдер" <b>активировано</b>`),
    resultholdf:  vhtml.result1div.replace('&%result1', `Режим "Холдер" <b>отключено</b>`),
    resultchcidt: vhtml.result1div.replace('&%result1', `Токен <b>{&%}</b> доступен для покупки с вашего аккаунта`),
    resultchcidf: vhtml.result1div.replace('&%result1', `Токен <b>{&%}</b> не доступен для покупки с вашего аккаунта`),
    resultshorts: vhtml.result1div.replace('&%result1', `Кратчайший токен для покупки с вашего аккаунта: <b>&%result1</b>`),
    resultshort0: vhtml.result1div.replace('&%result1', `<b>Отсутствует</b>`),
    resultlengt:  vhtml.result1div.replace('&%result1', `Длина (количество адресов) токена <b>&%result1</b> равно <b>&%result2</b>`),
    resultlenloc: vhtml.result1div.replace('&%result1', `Длина токена <b>&%result1</b> равна: <b>&%result2</b>, указанный адрес находится на <b>&%result3</b> позиции`),
    resultlenloc0:vhtml.result1div.replace('&%result1', `Длина токена <b>&%result1</b> равна: <b>&%result2</b>, адрес <b>&%result3</b> в его списке <b>отсутствует</b>`),
    resultparent: vhtml.result1div.replace('&%result1', `Токен <b>&%result1</b> созданный с токена <b>&%result2</b>`),
    resultparent0:vhtml.result1div.replace('&%result1', `Токен <b>&%result1</b> созданный с <b>"нулевого"</b> токена`),
    resultchild0: vhtml.result1div.replace('&%result1', `У токена <b>&%result1</b> дочерние токены <b>отсутсвуют</b>`),
    resultchild:  vhtml.result1div.replace('&%result1', `С токена <b>&%result1</b> было создано токены <b>&%result2;</b>`),
    resultowner:  vhtml.result1div.replace('&%result1', `Владельцем токена <b>&%result1</b> является аккаунт: <b>&%result2</b>`),
    resultownto0: vhtml.result1div.replace('&%result1', `Аккаунт: <b>&%result1</b> не владеет токенами`),
    resultowntok: vhtml.result1div.replace('&%result1', `Аккаунт: <b>&%result1</b> владеет токенами: <b>&%result2</b>`),

    // *************** Pages *************** //

    all_pyramids_html: `
								<section>

								
								
									<h2 class="major">{&%arg1} Пирамида</h2>
								        <span id="status{&%}" class="image main" style='background: url("images/overlay.png")'>
                                                                        </span>
									<h3 class="major">Проверка и снятие прибыли</h3>
									<form id="profit{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="profit{&%}" onclick="anhydrite('profit', {&%}, 'profit_{&%}');" value="Проверить прибыль" /></li>
											<li><input type="button" id="wprofit{&%}" onclick="anhydrite('wprofit', {&%}, 'profit_{&%}');" value="Снять прибыль" /></li>
										</ul>
									</form>
									<span id="profit_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Покупка случайного токена</h3>
									<form id="buyrandom{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="buy-random{&%}" onclick="anhydrite('buyrandom', {&%}, 'buyrandom_{&%}');" value="Купить токен" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-random-check{&%}" name="buy-random-check{&%}">
												<label for=buy-random-check{&%}>Подтверждаю</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Пользовательское соглашение</a></p>
											</div>
									</form>
									<span id="buyrandom_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Покупка токена по ID</h3>
									<form id="buyid{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbuy-id{&%}">Токен ID</label>
												<input type="text" name="get-buy-id{&%}" id="get-buy-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="buy-id{&%}" type="submit" onclick="anhydrite('buyid', {&%}, 'buyid_{&%}');" value="Купить токен" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-id-check{&%}" name="buy-id">
												<label for="buy-id-check{&%}">Подтверждаю</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Пользовательское соглашение</a></p>
											</div>
									</form>
									<span id="buyid_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Покупка токена который имеет адрес</h3>
									<form id="buyaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbuy-address{&%}">Адрес</label>
												<input id="get-buy-address{&%}" type="text" name="get-buy-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="buy-address{&%}" type="submit" onclick="anhydrite('buyaddress', {&%}, 'buyaddress_{&%}');" value="Купить токен" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
											<div class="field half">
												<input type="checkbox" id="buy-address-check{&%}" name="buy-id">
												<label for="buy-address-check{&%}">Подтверждаю</label>
												<p style="font-size: 70%;"><a href="#" target="_blank">Пользовательское соглашение</a></p>
											</div>
									</form>
									<span id="buyaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Режим "Холдер"</h3>
									<form id="holder{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="hold{&%}" onclick="anhydrite('holder', {&%}, 'holder_{&%}');" value="Проверка режима Холдер" /></li>
											<li><input type="button" id="xhold{&%}" onclick="anhydrite('wholder', {&%}, 'holder_{&%}');" value="Изменение режима Холдер" /></li>
										</ul>
									</form>
									<span id="holder_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Доступность токена</h3>
									<form id="checkid{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="check-id{&%}">Токен ID</label>
												<input type="text" name="check-id{&%}" id="check-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="token-check-id{&%}" type="submit" onclick="anhydrite('checkid', {&%}, 'checkid_{&%}');" value="Проверить токен" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
									</form>
									<span id="checkid_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Токены с адресом</h3>
									<form id="alltokenaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="t-address{&%}">Адрес</label>
												<input type="text" name="t-tokens-address{&%}" id="t-tokens-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="all-tokens-address{&%}" type="submit" onclick="anhydrite('alltokenaddress', {&%}, 'alltokesnaddress_{&%}');" value="Проверить адрес" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
									</form>
									<span id="alltokesnaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Кратчайший токен</h3>
									<form id="shortesttoken{&%}" onsubmit="return false">
										<ul class="actions">
											<li><input type="button" id="shortest-token{&%}" onclick="anhydrite('shortesttoken', {&%}, 'shortesttoken_{&%}');" value="Показать кратчайший токен" /></li>
										</ul>
									</form>
									<span id="shortesttoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Кратчайший токен с адресом</h3>
									<form id="shortesaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="short-t-address{&%}">Адрес</label>
												<input type="text" name="shortes-address{&%}" id="shortes-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="t-shortes-address{&%}" type="submit" onclick="anhydrite('shortesaddress', {&%}, 'shortesaddress_{&%}');" value="Кратчайший токен" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
									</form>
									<span id="shortesaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Длина токена</h3>
									<form id="lengthtoken{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="ss-length-token{&%}">Токен ID</label>
												<input type="text" name="s-length-token{&%}" id="s-length-token{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="aa-length-token{&%}" type="submit" onclick="anhydrite('lengthtoken', {&%}, 'lengthtoken_');" value="Длина токена" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
									</form>
									<span id="lengthtoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Длина токену и позиция адреса</h3>
									<form id="lengthtokenaddress{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xtlength-token">Токен ID</label>
												<input type="text" name="length-token{&%}" id="length-token{&%}" value="" placeholder="0" />
											</div>
											<div class="field half">
												<label for="xlet-address{&%}">Адрес</label>
												<input type="text" name="length-token-address{&%}" id="length-token-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>
										</div>
										<ul class="actions">
											<li><input id="ll-token-address{&%}" type="submit" onclick="anhydrite('lengthtokenaddress', {&%}, 'lengthtokenaddress_');" value="Длина токена" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
									</form>
									<span id="lengthtokenaddress_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Родительский токен</h3>
									<form id="parenttoken{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xparent-token-id{&%}">Токен ID</label>
												<input type="text" name="parent-token-id{&%}" id="parent-token-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="parent-token-id{&%}" type="submit" onclick="anhydrite('parenttoken', {&%}, 'parenttoken_');" value="Родительский токен" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
									</form>
									<span id="parenttoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Дочерние токены</h3>
									<form id="childtokens{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xchild-owneroff-id{&%}">Токен ID</label>
												<input type="text" name="child-token-id{&%}" id="child-token-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="ci-token-id{&%}" type="submit" onclick="anhydrite('childtokens', {&%}, 'childtokens_');" value="Дочерние токены" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
									</form>
									<span id="childtokens_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Владелец токена</h3>
									<form id="owneroff{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xowneroff-id{&%}">Токен ID</label>
												<input type="text" name="owneroff-id{&%}" id="owneroff-id{&%}" value="" placeholder="0" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="token-owneroff{&%}" type="submit" onclick="anhydrite('owneroff', {&%}, 'owneroff_');" value="Владелец токена" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
									</form>
									<span id="owneroff_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Баланс токенов</h3>
									<form id="balanceoff{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xbalanceoff-address{&%}">Адрес</label>
												<input type="text" name="balanceoff-address{&%}" id="balanceoff-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="t-balanceoff-address{&%}" type="submit" onclick="anhydrite('balanceoff', {&%}, 'balanceoff_');" value="Баланс токенов" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
									</form>
									<span id="balanceoff_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

								<section>
									<h3 class="major">Проверить прибыль адреса</h3>
									<form id="otherprofit{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xt-otherprofit">Адрес</label>
												<input type="text" name="b-otherprofit{&%}" id="b-otherprofit{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>

										</div>
										<ul class="actions">
											<li><input id="bal-otherprofit{&%}" type="submit" onclick="anhydrite('otherprofit', {&%}, 'otherprofit_');" value="Проверить прибыль" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
									</form>
									<span id="otherprofit_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>

<section>
									<h3 class="major">Передать токен</h3>
									<form id="{&%}" onsubmit="return false">
										<div class="fields">
											<div class="field half">
												<label for="xtrans-token">Токен ID</label>
												<input type="text" name="transfer-token{&%}" id="transfer-token{&%}" value="" placeholder="0" />
											</div>
											<div class="field half">
												<label for="xlet-address{&%}">Адрес</label>
												<input type="text" name="transferfrom-address{&%}" id="transferfrom-address{&%}" value="" placeholder="0x0000000000000000000000000000000000000000" />
											</div>
										</div>
										<ul class="actions">
											<li><input id="safetransferfrom{&%}" type="submit" onclick="anhydrite('safetransfer', {&%}, 'transfertoken_');" value="Передать токен" /></li>
											<li><input type="reset" value="Очистить" /></li>
										</ul>
									</form>
									<span id="transfertoken_{&%}" class="image main" style='background: url("images/overlay.png")'>&nbsp;</span>
								</section>`

};



