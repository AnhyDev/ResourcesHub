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
    basestatus:   vhtml.result1div.replace('&%result1', `<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; Lista para el trabajo ...</h4>`),
    nologin:      vhtml.result1div.replace('&%result1', `<b><font color="red">
                                                         No estás conectado a la cadena de bloques:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('login');">conectarse a la cadena de bloques</button></br>&nbsp;`),
    noinstall:    vhtml.result1div.replace('&%result1', `<b><font color="red">
                                                         MetaMask no está instalado:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('link')">Instalar MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `<b><font color="red">
                                                         ¡Estás conectado desde un dispositivo móvil! Para trabajar con pirámides necesitas tener MetaMask, 
														 si no la has instalado, entonces :</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button" onclick="anhydrite('link')">Instala MetaMask</button></li><b><font color="red">
                                                         Si MetaMask ya está instalado en su dispositivo, tiene que acceder al sitio a través del browser de MetaMask:</font></b>
                                                         <li><button class="button" onclick="anhydrite('link2')">Inicie sesión en el navegador MetaMask</button></li></br></ul></form>`),
    update:       vhtml.result1div.replace('&%result1', `<b><font color="red">
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

};
