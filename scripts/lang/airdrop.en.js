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
    waiting:      '<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; Wait ...</h4>',
    basestatus:   vhtml.resultdiv.replace('{&%}',       `Network: <b>Smart Chain</b></br>
                                                         Token: <b>Anhydrite</b></br>
                                                         Contract: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br>
                                                         Your address: <b><a href="https://bscscan.com/address/{&%}" target="_blank" style="text-decoration: underline;">{&%}</a></b></br>
                                                         </br>&nbsp;
                                                         <button class="button" onclick="airdrop('get');">Get Anhydrite</button></br>&nbsp;`),
    nologin:      vhtml.resultdiv.replace('{&%}',       `Network: <b>Smart Chain</b></br>
                                                         Token: <b>Anhydrite</b></br>
                                                         Contract: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br>
                                                         </br><b><font color="red">
                                                         You are not connected to the blockchain:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="airdrop('login');">Connect to the blockchain</button></br>&nbsp;`),
    noinstall:    vhtml.resultdiv.replace('{&%}',       `Network: <b>Smart Chain</b></br>
                                                         Token: <b>Anhydrite</b></br>
                                                         Contract: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br><b><font color="red">
                                                         You do not have MetaMask installed:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="airdrop('link')">Install MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `Network: <b>Smart Chain</b></br>
                                                         Token: <b>Anhydrite</b></br>
                                                         Contract: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </b> {&%}</br><b><font color="red">
                                                         You are connected from a mobile device! To work with pyramids MetaMask is required,
                                                         if it is not installed, then:</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button" onclick="airdrop('link')">Install MetaMask</button></br><b><font color="red"></li>
                                                         If MetaMask is already installed on your device, then open the site throught the MetaMask browser:</font></b>
                                                         <li><button class="button" onclick="airdrop('link2')">Open MetaMask browser</button></li></br></ul>`),
    update:       vhtml.resultdiv.replace('{&%}',       `Network: <b>Smart Chain</b></br>
                                                         Token: <b>Anhydrite</b></br>
                                                         Contract: <b><a href="https://bscscan.com/token/0x578b350455932ac3d0e7ce5d7fa62d7785872221" target="_blank" style="text-decoration: underline;">0x578b350455932ac3d0e7ce5d7fa62d7785872221</a></b></br>
                                                         </br><b><font color="red">
                                                         If you are already connected to the blockchain, update your status:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="airdrop('load');">Update status</button></br>&nbsp;`),
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
};
