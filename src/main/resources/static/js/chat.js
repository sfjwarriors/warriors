var chat = (function () {
    var idO = "";
    var idUser = -1;
    function openForm(idOrden, idUs) {
        idUser = idUs;
        if("orden"+idOrden != idO){
            idO = "orden"+idOrden;
            $("#mensajes").html("");
        }
        stomp.connectAndSubscribe(imprime, idO);
        console.log(idOrden);
        document.getElementById("myForm").style.display = "block";
    }

    function imprime(mensaje){
        let ms = mensaje.body;
        // let vali = mensaje.body.substring(0, 3);
        console.log(ms);
        let mens = JSON.parse(ms);
        console.log(mens);
        let mensajes = $("#mensajes").html();
        let date = new Date();
        if(idUser!=mens.idUser){
            $("#mensajes").html(mensajes+'<div class="containerchat darker"> <img src="imagen/client.png" alt="Avatar"> <p>'+mens.mess+'</p> <span class="time-right">'+ date.getHours()+ ':' + date.getMinutes() +'</span></div>');
        }
    }
    
    function closeForm() {
        document.getElementById("myForm").style.display = "none";
    }

    function sendMessage(){
        let mensaje = $("#mensaje").val();
        // console.log(mensaje);
        document.getElementById("mensaje").value = "";
        let mensajes = $("#mensajes").html();
        // console.log(mensajes);
        let date = new Date();
        $("#mensajes").html(mensajes+'<div class="containerchat darker"> <img src="imagen/you.png" alt="Avatar" class="right"> <p>'+mensaje+'</p> <span class="time-left">'+ date.getHours()+ ':' + date.getMinutes() +'</span></div>');
        setTimeout(function(){
            stomp.sends(idO, '{"idUser":'+idUser+', "mess":"'+mensaje+'"}');
        }, 500);
    }

    return {
        openForm: openForm,
        closeForm: closeForm,
        sendMessage: sendMessage,
        imprime: imprime
    }
})();