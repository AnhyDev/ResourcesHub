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
    basestatus:   vhtml.resultdiv.replace('{&%}',       `Red: <b>Smart Chain</b></br>
                                                         Token: <b>Anhydrite</b></br>
                                                         Contrato: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br>
                                                         Su dirección: <b><a href="https://bscscan.com/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         </br>&nbsp;
                                                         <button class="button" onclick="airdrop('get');">Get Anhydrite</button></br>&nbsp;`),
    nologin:      vhtml.resultdiv.replace('{&%}',       `Red: <b>Smart Chain</b></br>
                                                         Token: <b>Anhydrite</b></br>
                                                         Contrato: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br>
                                                         </br><b><font color="red">
                                                         No estás conectado a la cadena de bloques:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="airdrop('login');">conectarse a la cadena de bloques</button></br>&nbsp;`),
    noinstall:    vhtml.resultdiv.replace('{&%}',       `Red: <b>Smart Chain</b></br>
                                                         Token: <b>Anhydrite</b></br>
                                                         Contrato: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br><b><font color="red">
                                                         MetaMask no está instalado:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="airdrop('link')">Instalar MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `Red: <b>Smart Chain</b></br>
                                                         Token: <b>Anhydrite</b></br>
                                                         Contrato: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </b> {&%}</br><b><font color="red">
                                                         ¡Estás conectado desde un dispositivo móvil! Para trabajar con pirámides necesitas tener MetaMask,
                                                         si no la has instalado, entonces:</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button" onclick="airdrop('link')">Встановити MetaMask</button></br><b><font color="red"></li>
                                                         Si MetaMask ya está instalado en su dispositivo, tiene que acceder al sitio a través del browser de MetaMask:</font></b>
                                                         <li><button class="button" onclick="airdrop('link2')">Inicie sesión en el navegador MetaMask</button></li></br></ul>`),
    update:       vhtml.resultdiv.replace('{&%}',       `Red: <b>Smart Chain</b></br>
                                                         Token: <b>Anhydrite</b></br>
                                                         Contrato: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br><b><font color="red">
                                                         Si ya está conectado a la cadena de bloques, actualice su estadoс:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="airdrop('load');">Actualice su estado</button></br>&nbsp;`),
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

};
