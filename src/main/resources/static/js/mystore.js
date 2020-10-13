//document.body.onload = addElement;
var mystore = (function () {
    var storeTmp;
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
        addElement();
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

    function addElement () {
      // crea un nuevo div
      // y añade contenido
//      var newDiv = document.createElement("div");
//      var newContent = document.createTextNode("Hola!¿Qué tal?");
//      newDiv.appendChild(newContent); //añade texto al div creado.
//
//      // añade el elemento creado y su contenido al DOM
//      var currentDiv = document.getElementById("lista");
//      document.body.insertBefore(newDiv, currentDiv);
//        console.log("pro", storeTmp.products);
        var name = storeTmp.products[0].name;
        var s = "";
        for(var i=0; i<storeTmp.products.length; i++) {
            s += "<div class='col-lg-6 col-md-6 mb-5'><div class='blog-item'><img src='images/blog/1.jpg' alt='' class='img-fluid rounded'> <div class='blog-item-content bg-white p-5'>";
            s += "<h3 class='mt-3 mb-3'><a href='blog-single.html'>"+storeTmp.products[i].name+"</a></h3><p class='mb-4'>"+storeTmp.products[i].description+"</p>";
            s += "<a href='carrito.html' class='btn btn-small btn-main btn-round-full'>Agregar al Carrito</a></div></div></div>";
        }
        console.log("stmp", storeTmp);
        $("#lista").html(s);
    }

    return {
        isLogged: isLogged,
        habilitaCambioNombre: habilitaCambioNombre,
        addElement: addElement
    }
})();