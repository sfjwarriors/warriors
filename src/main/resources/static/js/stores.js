var storeScript = (function () {

    var stores = null;
    var mechanics = null;
    var carritos = [];
    var tiendaCarrito = "";
    var tiendaSele = null;
    var precio = 0;
    var userId = -1;

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
        } else {
            document.getElementById("servicebtn").style.display = "block";
            document.getElementById("servicebtn").href = "myorders.html";
            $("#servicebtn").text("Mis Ordenes");
        }
        client.getMechanics(setMechanics);
        client.getStores(showStores);
        userId = user.id;
    }

    function goBack() {
        $("#tiendaTitle").html("NUESTROS TALLERES Y TIENDAS");
        $("#cart").html("");
        document.getElementById("volverbtn").style.visibility = "hidden";
        // document.getElementById("carritobtn").style.visibility = "hidden";
        listStores();
    }

    function showCarrito(){
        $("#tiendaTitle").html("Carrito");
        document.getElementById("volverbtn").style.visibility = "visible";
        // $("#tiendas").html(carritos);
        console.log(carritos);
        st = '<div class="col-md-4"> <div class="blog-item-content bg-white p-5"><h3 class="mt-3 mb-3">Productos y Servicios</h3>';
        for(let i=0; i<carritos.length; i++){
            if(carritos[i].fkProductCart != null){
                for(let j=0; j<tiendaSele.products.length; j++){
                    if(tiendaSele.products[j].id==carritos[i].fkProductCart){
                        st+='<p class="mb-4">'+tiendaSele.products[j].name+'  Precio: $'+tiendaSele.products[j].price+'</p>';
                        precio += tiendaSele.products[j].price;
                    }
                }
            } else if (carritos[i].fkServicesCart != null){
                for(let j=0; j<tiendaSele.servicios.length; j++){
                    if(tiendaSele.servicios[j].id==carritos[i].fkServicesCart){
                        st+='<p class="mb-4">'+tiendaSele.servicios[j].name+'  Precio: $'+tiendaSele.servicios[j].price+'</p>';
                        precio += tiendaSele.servicios[j].price;
                    }
                }
            }
            // st+='<p class="mb-4">'+tiendaSele.products[j].name+'  Precio: $'+tiendaSele.products[j].price+'</p>';
        }
        st += '<p class="mb-4">Total: $'+precio+'</p> <a onclick="storeScript.createOrden()" class="btn btn-small btn-main btn-round-full">Pedir</a></div></div>';
        $("#tiendas").html("");
        $("#cart").html(st);
    }

    function createOrden(){
        let date = new Date();
        let fechaOrden = date.getFullYear()+'-'+date.getMonth()+'-'+date.getDate();
        let fechaEntrega = date.getFullYear()+'-'+date.getMonth()+'-'+(date.getDate()+1);
        let orden = {"dateOrder":fechaOrden,"deliveryDate":fechaEntrega,"totalValue":precio,"statusOrder":"CREADA","fkStore":tiendaSele.id, "fkUser":userId};
        client.createOrden(orden, sendCarts);
        console.log("crear orden", orden);
    }

    function sendCarts(n){
        for(let i=0; i<carritos.length; i++){
            carritos[i].fkOrderCart=n;
        }
        client.createCarts(carritos, ordenCreada);
    }
    
    function ordenCreada(){
        alert("Orden Creada")
        window.location.replace("index.html");
    }

    function viewProductStore(indexStore){
        viewProductStoreAux(stores[indexStore])
    }

    function addCarritoProduct(idProduct){
        carritos.push({"fkProductCart":idProduct, "fkOrderCart":null,"fkServicesCart":null});
        $("#carritobtn").html("Carrito ("+carritos.length+")"+'<img src="imagen/carrito.png" style="width: 50%; height: 50%;">');
    }

    function getCarrito(){
        return carritos;
    }

    function addCarritoService(idService){
        carritos.push({"fkProductCart":null,"fkOrderCart":null,"fkServicesCart":idService});
        $("#carritobtn").html("Carrito ("+carritos.length+")"+'<img src="imagen/carrito.png" style="width: 50%; height: 50%;">');
    }

    function viewProductStoreAux(storeAux){
        s = '';
        // document.getElementById("carritobtn").style.visibility = "visible";
        if(tiendaCarrito != storeAux.storeName){
            tiendaSele = storeAux;
            tiendaCarrito = storeAux.storeName;
            carritos = [];
            $("#carritobtn").html("Carrito ("+carritos.length+")"+'<img src="imagen/carrito.png" style="width: 50%; height: 50%;">');
        }
        $("#tiendaTitle").html(storeAux.storeName);
        for(var i=0; i<storeAux.products.length;i++){
            if(storeAux.products[i].status=='available'){
                s += '<div class="col-md-4"> <div class="card text-center mb-md-0 mb-3"> <div class="card-body py-5"> <h1>'+storeAux.products[i].name+'</h1><h3>Precio: $'+storeAux.products[i].price;
                s += '</h3> <a onclick="storeScript.addCarritoProduct('+storeAux.products[i].id+')" class="btn btn-small btn-main mt-3 btn-round-full">Agregar al Carrito</a>  </div> </div> </div>';
            }
        }
        $("#tiendas").html(s);
        document.getElementById("volverbtn").style.visibility = "visible";
        stomp.disconnect();
        stomp.connectAndSubscribe(updateProducts, storeAux.storeName.replace(/ /g, '')+'prods');
    }

    function updateProducts(data){
        client.getStore(data.body, viewProductStoreAux);
        console.log(data, "update products");
    }

    function viewServiceStore(indexStore){
        viewServiceStoreAux(stores[indexStore]);
    }

    function viewServiceStoreAux(storeAux){
        console.log(storeAux);
        s = "";
        if(tiendaCarrito != storeAux.storeName){
            tiendaSele = storeAux;
            tiendaCarrito = storeAux.storeName;
            carritos = [];
            $("#carritobtn").html("Carrito ("+carritos.length+")"+'<img src="imagen/carrito.png" style="width: 50%; height: 50%;">');
        }
        // document.getElementById("carritobtn").style.visibility = "visible";
        for(var i=0; i<storeAux.servicios.length;i++){
            if(storeAux.servicios[i].status=='available'){
                s += '<div class="col-md-4"> <div class="card text-center mb-md-0 mb-3"> <div class="card-body py-5"> <h1>'+storeAux.servicios[i].name+'</h1><h3>Precio: $'+storeAux.servicios[i].price;
                s += '</h3> <a onclick="storeScript.addCarritoService('+storeAux.servicios[i].id+')" class="btn btn-small btn-main mt-3 btn-round-full">Agregar al Carrito</a>  </div> </div> </div>';
            }
        }
        $("#tiendas").html(s);
        document.getElementById("volverbtn").style.visibility = "visible";
        stomp.disconnect();
        stomp.connectAndSubscribe(updateServices, storeAux.storeName.replace(/ /g, '')+'servs');
    }

    function updateServices(data){
        client.getStore(data.body, viewServiceStoreAux);
        console.log(data, "update service");
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
        }
        stomp.connectAndSubscribe(imprime, "all");
        $("#tiendas").html(s);
    }

    function imprime(mensaje){
        client.getMechanics(setMechanics);
        client.getStores(showStores);
    }

    function showStores(storesclient){
        stores = storesclient;
        listStores();
    }

    function setStore(store) {
        $("#storebtn").text(store.storeName);
        document.getElementById("storebtn").style.visibility="visible";
        document.getElementById("storebtn").href = "mystore.html";
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
        goBack: goBack,
        addCarritoProduct: addCarritoProduct,
        addCarritoService: addCarritoService,
        getCarrito: getCarrito,
        showCarrito: showCarrito,
        createOrden: createOrden,
        sendCarts: sendCarts,
        ordenCreada: ordenCreada
    }
})();