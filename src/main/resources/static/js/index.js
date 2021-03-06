var index = (function () {
    function isLogged() {
        client.isLogged(updateBtns);
    }

    function updateBtns(user, bool) {
        $("#userbtn").text("Cerrar Sesión");
        document.getElementById("userbtn").onclick = client.closeSession;
        $("#tiendasbtn").text("TIENDAS");
        document.getElementById("tiendasbtn").href = "stores.html";
        document.getElementById("tiendasbtn").style.visibility="visible";
        document.getElementById("promoregister").style.visibility="hidden";
        if(user.rol=='MECA') {
            client.getStore(user.id, setStore);
            $("#startbtn").text("Mi Taller");
            document.getElementById("startbtn").href = "mystore.html";
        } else {
            document.getElementById("servicebtn").style.display = "block";
            document.getElementById("servicebtn").href = "myorders.html";
            $("#servicebtn").text("Mis Ordenes");
        
            $("#startbtn").text("Pide Ya!");
            document.getElementById("startbtn").href = "#";
        }
    }

    function setStore(store) {
            $("#storebtn").text(store.storeName);
            document.getElementById("storebtn").style.visibility="visible";
            document.getElementById("storebtn").href = "mystore.html";
            var storeTmp = store;
        }

    return {
        isLogged: isLogged
    }
})();