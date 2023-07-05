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
    waiting:      '<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; READY FOR WORK ...</h4>',
    basestatus:   vhtml.result1div.replace('&%result1', `<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; READY FOR WORK ...</h4>`),
    nologin:      vhtml.result1div.replace('&%result1', `<b><font color="red">
                                                         You are not connected to the blockchain:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('login');">Connect to the blockchain</button></br>&nbsp;`),
    noinstall:    vhtml.result1div.replace('&%result1', `<b><font color="red">
                                                         You do not have MetaMask installed:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('link')">Install MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `<b><font color="red">
                                                         You are connected from a mobile device! To work with pyramids MetaMask is required,
                                                         if it is not installed, then:</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button" onclick="anhydrite('link')">Install MetaMask</button></br><b></li><font color="red">
                                                         If MetaMask is already installed on your device, then open the site throught the MetaMask browser:</font></b>
                                                         <li><button class="button" onclick="anhydrite('link2')">Open MetaMask browser</button></li></br></ul></form>`),
    update:       vhtml.result1div.replace('&%result1', `<b><font color="red">
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

};
