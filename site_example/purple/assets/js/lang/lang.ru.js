const vhtml = {
	statusdiv2: `<div style="margin: 5px; color: black;">&%status <div>`,
	resultdiv:  `<div style="margin: 10px">{&%}<div>`,
	result1div: `<div style="margin: 10px">&%result1<div>`,
	result2div: `<div style="red: 10px">&%result1 &%result2<div>`,
	errorsdiv:  `<div style="margin: 10px; color: red; font-weight: 600;">&%result1<div>`,
	nullstatus: `&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;</br>&nbsp;`
};

const lang = {
    // *************** Status *************** //
    unavailable:  ' ...',
    waiting:      '<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; Ожидание ...</h4>',
    basestatus:   vhtml.result1div.replace('&%result1', `<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; ГОТОВ К РАБОТЕ ...</h4>`),
    nologin:      vhtml.result1div.replace('&%result1', `<b><font color="red">
                                                         Вы не подключены к блокчейну:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('login');">Подключиться к блокчейну</button></br>&nbsp;`),
    noinstall:    vhtml.result1div.replace('&%result1', `<b><font color="red">
                                                         У вас не установлен MetaMask:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('link')">Установить MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `<b><font color="red">
                                                         Вы подключились с мобильного устройства! Для возможности работать с пирамидами нужен MetaMask,
                                                         если он у вас не установлен, то:</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button"  onclick="anhydrite('link')">Установить MetaMask</button></li><b><font color="red">
                                                         А если MetaMask уже установлен на вашем устройстве, то откройте сайт с помощью MetaMask браузера:</font></b>
                                                         <li><button class="button" onclick="anhydrite('link2')">Открыть MetaMask браузер</button></li></br></ul></form>`),
    update:       vhtml.result1div.replace('&%result1', `<b><font color="red">
                                                         Если вы уже подключены к блокчейну, обновите свой статус:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('load');">Оновить статус</button></br>&nbsp;`),
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

};



