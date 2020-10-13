var mystore = (function () {
    var storeTmp;
    var s = "";
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
        storeTmp = store;
        getProducts();
    }

    function habilitaCambioNombre() {
        document.getElementById("storetittle").disabled = false;
        $("#cambiarnombre").text("Guardar Nombre");
        document.getElementById("cambiarnombre").onclick = saveNameStore;
    }

    function saveNameStore() {
        document.getElementById("storetittle").disabled = true;
        var newNameStore = document.getElementById("storetittle").value;
        $("#cambiarnombre").text("Cambiar Nombre");
        storeTmp.storeName = newNameStore;
        client.updateStore(storeTmp, showUpdateStore);
        document.getElementById("cambiarnombre").onclick = habilitaCambioNombre;
    }

    function showUpdateStore(data) {
        if(data=="Success") {
            alert("Se cambio el nombre de su tienda");
        }
    }

    function getProducts() {
        client.getStore(storeTmp.fkMechanic, listProducts);
    }

    function getServices() {
        client.getStore(storeTmp.fkMechanic, listServices);
    }

    function listProducts(store) {
        console.log("List Products", store.products);
        s = "";
        for(var i=0; i<store.products.length; i++) {
            s += "<div class='col-lg-6 col-md-6 mb-5'><div class='blog-item'><img src='imagen/bateria.jpg' alt='' class='img-fluid rounded'> <div class='blog-item-content bg-white p-5'>";
            s += "<h3 class='mt-3 mb-3'><a href='blog-single.html'>"+store.products[i].name+"</a></h3><p class='mb-4'>"+store.products[i].description+"</p><p class='mb-4'> Precio: $"+store.products[i].price+"</p>";
            s += "<a href='carrito.html' class='btn btn-small btn-main btn-round-full'>Agregar al Carrito</a></div></div></div>";
        }
        //console.log("stmp", storeTmp);
        $("#lista").html(s);
    }

    function listServices(store) {
        console.log("List Services", store.servicios);
        s = "";
        for(var i=0; i<store.servicios.length; i++) {
            s += "<div class='col-lg-6 col-md-6 mb-5'><div class='blog-item'><img src='imagen/bateria.jpg' alt='' class='img-fluid rounded'> <div class='blog-item-content bg-white p-5'>";
            s += "<h3 class='mt-3 mb-3'><a href='blog-single.html'>"+store.servicios[i].name+"</a></h3><p class='mb-4'>"+store.servicios[i].description+"</p><p class='mb-4'> Precio: $"+store.servicios[i].price+"</p>";
            s += "<a href='carrito.html' class='btn btn-small btn-main btn-round-full'>Agregar al Carrito</a></div></div></div>";
        }
        $("#lista").html(s);
    }

    return {
        isLogged: isLogged,
        habilitaCambioNombre: habilitaCambioNombre,
        getProducts: getProducts,
        getServices, getServices
    }
})();