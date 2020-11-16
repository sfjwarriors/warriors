var storeScript = (function () {

    var stores = null;
    var mechanics = null;

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
        $("#tiendasbtn").text("Tiendas");
        document.getElementById("tiendasbtn").href = "stores.html";
        document.getElementById("tiendasbtn").style.visibility="visible";
        if(user.rol=='MECA') {
            client.getStore(user.id, setStore);
            // document.getElementById("servicebtn").href = "myorders.html";
            // $("#servicebtn").text("Mis Ordenes");
        }
        client.getMechanics(setMechanics);
        client.getStores(showStores);
        // else {
        //     document.getElementById("servicebtn").href = "#";
        //     $("#servicebtn").text("Pedir");
        // }
    }

    function goBack() {
        $("#tiendaTitle").html("NUESTROS TALLERES Y TIENDAS");
        document.getElementById("volverbtn").style.visibility = "hidden";
        listStores();
    }

    function viewProductStore(indexStore){
        s = '';
        $("#tiendaTitle").html(stores[indexStore].storeName);
        for(var i=0; i<stores[indexStore].products.length;i++){
            console.log(stores[indexStore].products[i])
            s += '<div class="col-md-4"> <div class="card text-center mb-md-0 mb-3"> <div class="card-body py-5"> <h1>'+stores[indexStore].products[i].name+'</h1><h3>Precio: $'+stores[indexStore].products[i].price;
            s += '</h3> <a onclick="" class="btn btn-small btn-main mt-3 btn-round-full">Agregar al Carrito</a>  </div> </div> </div>';
        }
        $("#tiendas").html(s);
        document.getElementById("volverbtn").style.visibility = "visible";
        // $("#volverbtn").html('<a onclick="storeScript.goBack()" class="btn btn-small btn-main mt-3 btn-round-full">Volver</a>');
        stomp.disconnect();
        stomp.connectAndSubscribe(updateProducts, stores[indexStore].storeName.replace(/ /g, '')+'prods');
        console.log(s);
        // client.getStore(idMechanic, showProducts);
    }

    function updateProducts(data){
        console.log(data);
    }

    function viewServiceStore(indexStore){
        console.log(indexStore, stores[indexStore]);
        s = "";
        for(var i=0; i<stores[indexStore].servicios.length;i++){
            console.log(stores[indexStore].servicios[i])
            s += '<div class="col-md-4"> <div class="card text-center mb-md-0 mb-3"> <div class="card-body py-5"> <h1>'+stores[indexStore].servicios[i].name+'</h1><h3>Precio: $'+stores[indexStore].servicios[i].price;
            s += '</h3> <a onclick="" class="btn btn-small btn-main mt-3 btn-round-full">Agregar al Carrito</a>  </div> </div> </div>';
        }
        $("#tiendas").html(s);
        document.getElementById("volverbtn").style.visibility = "visible";
        stomp.disconnect();
        stomp.connectAndSubscribe(updateServices, stores[indexStore].storeName.replace(/ /g, '')+'servs');
    }

    function updateServices(data){
        console.log(data);
    }

    function getStores(){
        return stores;
    }

    function listStores(){
        console.log("saved", stores);
        var s = '';
        for(var i=0; i<stores.length; i++){
            s += '<div class="col-md-4"> <div class="card text-center mb-md-0 mb-3"> <div class="card-body py-5"> <h1>'+stores[i].storeName+'</h1><h3>Direccion: ';
            for(var j=0; j<mechanics.length; j++){
                if(stores[i].fkMechanic==mechanics[j].id){
                    s += mechanics[j].address;
                    s += '</h3> <a onclick="storeScript.viewProductStore('+i+')" class="btn btn-small btn-main mt-3 btn-round-full">VER PRODUCTOS</a> <a onclick="storeScript.viewServiceStore('+i+')" class="btn btn-small btn-main mt-3 btn-round-full">VER SERVICIOS</a> </div> </div> </div>';
                }
            }
            // s += '</h3> <a onclick="stores.getStores()" class="btn btn-small btn-main mt-3 btn-round-full">VER TALLER</a> </div> </div> </div>';
        }
        // console.log(mechanics);
        stomp.connectAndSubscribe(imprime, "all");
        $("#tiendas").html(s);
    }

    function imprime(mensaje){
        client.getMechanics(setMechanics);
        client.getStores(showStores);
        // console.log("Nueva Tienda", mensaje);
    }

    function showStores(storesclient){
        stores = storesclient;
        listStores();
    }

    function setStore(store) {
        $("#storebtn").text(store.storeName);
        document.getElementById("storebtn").style.visibility="visible";
        document.getElementById("storebtn").href = "mystore.html";
        // storeTmp = store;
    }

    function setMechanics(mec){
        mechanics = mec;
    }

    return {
        isLogged: isLogged,
        listStores: listStores,
        showStores: showStores,
        imprime: imprime,
        viewProductStore: viewProductStore,
        viewServiceStore: viewServiceStore,
        updateProducts: updateProducts,
        updateServices: updateServices,
        getStores: getStores,
        goBack: goBack
    }
})();