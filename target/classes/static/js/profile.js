var profile = (function () {
    function isLogged() {
        if(window.sessionStorage.token!=null) {
            client.isLogged(updateBtns);
        } else {
            window.location.replace("login.html");
        }
    }

    function updateBtns(user) {
        $("#userbtn").text("Cerrar Sesión");
        document.getElementById("userbtn").onclick = client.closeSession;
        $("#perfilbtn").text(user.name);
        console.log(user);
        document.getElementById("perfilbtn").href = "profile.html";
        document.getElementById("perfilbtn").style.visibility="visible";
        if(user.rol=='MECA') {
            client.getStore(user.id, setStore);
            document.getElementById("servicebtn").href = "myorders.html";
            $("#servicebtn").text("Mis Ordenes");
        } else {
            document.getElementById("servicebtn").href = "#";
            $("#servicebtn").text("Pedir");
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