var index = (function () {
    function isLogged() {
        client.isLogged(updateBtns);
    }

    function updateBtns(user, bool) {
        $("#userbtn").text("Cerrar Sesión");
        document.getElementById("userbtn").onclick = client.closeSession;
        $("#perfilbtn").text(user.name);
        console.log(user);
        document.getElementById("perfilbtn").href = "profile.html";
        document.getElementById("perfilbtn").style.visibility="visible";
        document.getElementById("promoregister").style.visibility="hidden";
        if(user.rol=='MECA') {
            client.getStore(user.id, setStore);
            document.getElementById("servicebtn").href = "myorders.html";
            $("#servicebtn").text("Mis Ordenes");
            $("#startbtn").text("Mi Taller");
            document.getElementById("startbtn").href = "mystore.html";
        } else {
            document.getElementById("servicebtn").href = "#";
            $("#servicebtn").text("Pedir");
            $("#startbtn").text("Pide Ya!");
            document.getElementById("startbtn").href = "#";
        }
    }

    function setStore(store) {
            $("#storebtn").text(store.storeName);
            document.getElementById("storebtn").style.visibility="visible";
            document.getElementById("storebtn").href = "mystore.html";
            var storeTmp = store;
            console.log("stmp", storeTmp);
        }

    return {
        isLogged: isLogged
    }
})();