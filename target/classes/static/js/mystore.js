var mystore = (function () {
    var storeTmp;
    var s = "";
    var edit = false;
    var editing = null;

    function isLogged() {
        client.isLogged(updateBtns);
    }

    function updateBtns(user) {
        $("#userbtn").text("Cerrar Sesión");
        document.getElementById("userbtn").onclick = client.closeSession;
        $("#perfilbtn").text(user.name);
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
        if(edit){
            fixForm();
        }
        client.getStore(storeTmp.fkMechanic, listProducts);
    }

    function getServices() {
        if(edit){
            fixForm();
        }
        client.getStore(storeTmp.fkMechanic, listServices);
    }

    function getOrders() {
        if(edit){
            fixForm();
        }
        client.getStore(storeTmp.fkMechanic, listOrders);
    }

    function listProducts(store) {
        storeTmp = store;
        s = "";
        for(var i=0; i<store.products.length; i++) {
            s += "<div class='col-lg-6 col-md-6 mb-5'><div class='blog-item'><img src='images/notavailable.jpg' alt='' class='img-fluid rounded'> <div class='blog-item-content bg-white p-5'>";
            s += "<h3 class='mt-3 mb-3'><a href='blog-single.html'>"+store.products[i].name+"</a></h3><p class='mb-4'>"+store.products[i].description+"</p><p class='mb-4'> Precio: $"+store.products[i].price+"</p>";
            s += "<a href='#forms' onclick='mystore.editProduct("+i+")' class='btn btn-small btn-main btn-round-full'>Editar</a> <a onclick='mystore.deleteProduct("+i+")' class='btn btn-small btn-main btn-round-full'>Eliminar</a></div></div></div>";
        }
        $("#lista").html(s);
    }

    function listServices(store) {
        storeTmp = store;
        s = "";
        for(var i=0; i<store.servicios.length; i++) {
            s += "<div class='col-lg-6 col-md-6 mb-5'><div class='blog-item'><img src='images/notavailable.jpg' alt='' class='img-fluid rounded'> <div class='blog-item-content bg-white p-5'>";
            s += "<h3 class='mt-3 mb-3'><a href='blog-single.html'>"+store.servicios[i].name+"</a></h3><p class='mb-4'>"+store.servicios[i].description+"</p><p class='mb-4'> Precio: $"+store.servicios[i].price+"</p>";
            s += "<a href='#forms' onclick='mystore.editService("+i+")'class='btn btn-small btn-main btn-round-full'>Editar</a> <a onclick='mystore.deleteService("+i+")' class='btn btn-small btn-main btn-round-full'>Eliminar</a></div></div></div>";
        }
        $("#lista").html(s);
    }

    function listOrders(store) {
        storeTmp = store;
        s = "";
        for(var o=0; o<store.orders.length; o++){
            s+= "<div class='blog-item-content bg-white p-5'><h3 class='mt-3 mb-3'><a href='blog-single.html'>Orden No. "+store.orders[o].id+"</a></h3>";
            for(var j=0; j<store.orders[o].carts.length; j++){
                for(var k=0; k<storeTmp.servicios.length; k++){
                    if(store.servicios[k].id==store.orders[o].carts[j].fkServicesCart){
                        s += "<p class='mb-4'>Servicio: "+store.servicios[k].name+"  Precio: $"+store.servicios[k].price+"</p>";
                    } else if(store.products[k].id==store.orders[o].carts[j].fkProductCart){
                        s += "<p class='mb-4'>Producto: "+store.products[k].name+"  Precio: $"+store.products[k].price+"</p>";
                    }
                }
            }
            s += "<p class='mb-4'>Valor Total: "+store.orders[o].totalValue+"</p>";
            s += "<p class='mb-4'>Estado de la orden: "+store.orders[o].statusOrder+"</p>";
            s += "<a href='carrito.html' class='btn btn-small btn-main btn-round-full'>Avanzar Estado</a></div>";
        }
        $("#lista").html(s);
    }

    function editProduct(position){
        edit = true;
        editing = position;
        fixForm();
        //document.getElementById("saveservice").style.visibility = "hidden";
        $("#textform").text("Editar Producto");
        $("#saveproduct").text("Guardar Producto");
        $("#saveservice").text("Cancelar");
        document.getElementById("saveproduct").onclick = updateProduct;
        document.getElementById("saveservice").onclick = cancelEdit;
        //console.log(storeTmp.products[position].name);
        fillForm(storeTmp.products[position]);
    }

    function editService(position){
        edit = true;
        editing = position;
        fixForm();
        //document.getElementById("saveproduct").style.visibility = "hidden";
        $("#textform").text("Editar Servicio");
        $("#saveservice").text("Cancelar");
        $("#saveproduct").text("Guardar Servicio");
        //console.log(storeTmp.servicios[position].name);
        document.getElementById("saveservice").onclick = cancelEdit;
        document.getElementById("saveproduct").onclick = updateService;
        fillForm(storeTmp.servicios[position]);
    }

    function fillForm(dataf){
        //console.log(dataf);
        document.getElementById("namepos").value = dataf.name;
        document.getElementById("pricepos").value = dataf.price;
        document.getElementById("descriptionpos").value = dataf.description;
    }

    function fixForm(){
        //document.getElementById("saveproduct").style.visibility = "visible";
        //document.getElementById("saveservice").style.visibility = "visible";
        $("#textform").text("Nuevo Servicio o Producto");
        $("#saveservice").text("Crear Servicio");
        $("#saveproduct").text("Crear Producto");
        document.getElementById("namepos").value = "";
        document.getElementById("pricepos").value = "";
        document.getElementById("descriptionpos").value = "";
        document.getElementById("saveservice").onclick = createService;
        document.getElementById("saveproduct").onclick = createProduct;
    }

    function updateService() {
        if(validateForm()){
            let service = {"id":storeTmp.servicios[editing].id,"name":document.getElementById("namepos").value, "image":"Not available", "description":document.getElementById("descriptionpos").value, "price":document.getElementById("pricepos").value, "status":"available", "fkStoreService":storeTmp.id};
            client.updateService(service, getServices);
            fixForm();
        }
    }

    function updateProduct() {
        //console.log("hey1", storeTmp.servicios[editing], validateForm());
        if(validateForm()){
            let producto = {"id":storeTmp.products[editing].id,"name":document.getElementById("namepos").value, "image":"Not available", "description":document.getElementById("descriptionpos").value, "price":document.getElementById("pricepos").value, "status":"available", "fkStoreProduct":storeTmp.id};
            client.updateProduct(producto, getProducts);
            fixForm();
        }
    }

    function cancelEdit(){
        edit = false;
        editing = null;
        fixForm();
    }

    function createService(){
        if(validateForm()){
            let service = {"name":document.getElementById("namepos").value, "image":"Not available", "description":document.getElementById("descriptionpos").value, "price":document.getElementById("pricepos").value, "status":"available", "fkStoreService":storeTmp.id};
            client.createService(service, getServices);
            fixForm();
        }
    }

    function createProduct(){
        if(validateForm()){
            let producto = {"name":document.getElementById("namepos").value, "image":"Not available", "description":document.getElementById("descriptionpos").value, "price":document.getElementById("pricepos").value, "status":"available", "fkStoreProduct":storeTmp.id};
            client.createProduct(producto, getProducts);
            fixForm();
        }
    }


    function validateForm() {
        var ans = false;
        if(document.getElementById("namepos").value==""){
            alert("El nombre no puede ser vacio");
        }else if(document.getElementById("pricepos").value<10000){
            alert("El precio no puede ser menor o igual a 10000");
        }else if(document.getElementById("pricepos").value==""){
            alert("El precio no puede ser vacio");
        }else if(document.getElementById("descriptionpos").value==""){
            alert("La descripción no puede ser vacia");
        } else {
            ans = true;
        }
        return ans;
    }

    function deleteProduct(position){
        client.deleteProduct(storeTmp.products[position], getProducts);
    }

    function deleteService(position){
        client.deleteService(storeTmp.servicios[position], getServices);
    }

    return {
        isLogged: isLogged,
        habilitaCambioNombre: habilitaCambioNombre,
        getProducts: getProducts,
        getServices, getServices,
        getOrders: getOrders,
        editProduct: editProduct,
        editService: editService,
        fixForm: fixForm,
        cancelEdit: cancelEdit,
        createService: createService,
        createProduct, createProduct,
        deleteProduct: deleteProduct,
        deleteService: deleteService
    }
})();