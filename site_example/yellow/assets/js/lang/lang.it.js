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
    basestatus:   vhtml.result1div.replace('&%result1', `<h4 style="margin: 10px" class="blink">&nbsp; &nbsp; Attesa ...</h4>`),
    nologin:      vhtml.result1div.replace('&%result1', `<b><font color="red">
                                                         Non sei connesso alla blockchain:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('login');">Connettersi alla blockchain</button></br>&nbsp;`),
    noinstall:    vhtml.result1div.replace('&%result1', `<b><font color="red">
                                                         Non è instrallato MetaMask:</font></b></br>&nbsp;</br>&nbsp;
                                                         <button class="button" onclick="anhydrite('link')">Installare MetaMask</button></br>&nbsp;`),
    metamaskmobile:     vhtml.resultdiv.replace('{&%}', `<b><font color="red">
                                                         Sei connesso da un dispositivo mobile! Per poter lavorare con le piramidi è necessario avere MetaMask,
                                                         se non lo hai installato, allora :</font></b>
                                                         <form onsubmit="return false"> <ul class="actions">
                                                         <li><button class="button" onclick="anhydrite('link')">Installare MetaMask</button></li><b><font color="red">
                                                         Se MetaMask è già installato sul tuo dispositivo,è necessario accendere al sito attraverso il browser di MetaMask:</font></b>
                                                         <li><button class="button" onclick="anhydrite('link2')">Accendere al browser MetaMask</button></li></br></ul></form>`),
    update:       vhtml.result1div.replace('&%result1', `<b><font color="red">
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

};
