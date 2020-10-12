var mystore = (function () {
    function isLogged() {
        client.isLogged(updateBtns);
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
        document.getElementById("storetittle").value = store.storeName;
        document.getElementById("storebtn").style.visibility="visible";
        document.getElementById("storebtn").href = "mystore.html";
        var storeTmp = store;
        console.log("stmp", storeTmp);
    }

    function habilitaCambioNombre() {
        document.getElementById("storetittle").disabled = false;
        $("#cambiarnombre").text("Guardar Nombre");
        document.getElementById("cambiarnombre").onclick = saveNameStore;
    }

    function saveNameStore() {
        document.getElementById("storetittle").disabled = true;
        let newNameStore = document.getElementById("storetittle").value;
        $("#cambiarnombre").text("Cambiar Nombre");
        alert("El nuevo nombre de su tienda es: " + newNameStore);
    }

    return {
        isLogged: isLogged,
        habilitaCambioNombre: habilitaCambioNombre
    }
})();