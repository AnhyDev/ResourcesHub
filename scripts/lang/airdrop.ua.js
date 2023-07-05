const vhtml = {
	statusdiv2: `<div style="margin: 5px; color: black;">&%status <div>`,
	resultdiv:  `<div style="margin: 10px">{&%}<div>`,
	result2div: `<div style="red: 10px">&%result1 &%result2<div>`,
	errorsdiv:  `<div style="margin: 10px; color: red; font-weight: 600;">&%result1<div>`,
        nullstatus: `&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;`
};
const lang = {
    // *************** Status *************** //
    unavailable:  ' ...',
    waiting:      '<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; Очікування ...</h4>',
    basestatus:   vhtml.resultdiv.replace('{&%}',       `Мережа: <b>Smart Chain</b></br>
                                                         Токен: <b>Anhydrite</b></br>
                                                         Контракт: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br>
                                                         Ваша адреса: <b><a href="https://bscscan.com/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         </br>&nbsp;
                                                         <button class="button" onclick="airdrop('get');">Get Anhydrite</button></br>&nbsp;`),
    nologin:      vhtml.resultdiv.replace('{&%}',       `Мережа: <b>Smart Chain</b></br>
                                                         Токен: <b>Anhydrite</b></br>
                                                         Контракт: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br>
                                                         </br><b><font color="red">
                                                         Ви не підключені до блокчейну:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="airdrop('login');">Підключитися до блокчейну</button></br>&nbsp;`),
    noinstall:    vhtml.resultdiv.replace('{&%}',       `Мережа: <b>Smart Chain</b></br>
                                                         Токен: <b>Anhydrite</b></br>
                                                         Контракт: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br><b><font color="red">
                                                         У вас не встановлено MetaMask:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="airdrop('link')">Встановити MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `Мережа: <b>Smart Chain</b></br>
                                                         Токен: <b>Anhydrite</b></br>
                                                         Контракт: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </b> {&%}</br><b><font color="red">
                                                         Ви підключились з мобільного пристрою! Для можливості працювати з пірамідами потрібен MetaMask,
                                                         якщо у вас він не встановлений, то:</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button" onclick="airdrop('link')">Встановити MetaMask</button></br><b><font color="red"></li>
                                                         А якщо MetaMask уже встановлений на вашому пристрої, то відкрийте сайт за допомогою MetaMask браузера:</font></b>
                                                         <li><button class="button" onclick="airdrop('link2')">Відкрити MetaMask браузер</button></li></br></ul>`),
    update:       vhtml.resultdiv.replace('{&%}',       `Мережа: <b>Smart Chain</b></br>
                                                         Токен: <b>Anhydrite</b></br>
                                                         Контракт: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br><b><font color="red">
                                                         Якщо ви вже підключились до блокчейну, оновіть свій статус:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="airdrop('load');">Оновити статус</button></br>&nbsp;`),
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
};
