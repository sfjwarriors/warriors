var myorders = (function () {

    var s = "";
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
            window.location.replace("mystore.html");
            // client.getStore(user.id, setStore);
        } else {
            document.getElementById("servicebtn").style.display = "block";
            document.getElementById("servicebtn").href = "myorders.html";
            $("#servicebtn").text("Mis Ordenes");
        }
        // client.getMechanics(setMechanics);
        // client.getStores(showStores);
        userId = user.id;
        client.getOrdersClient(userId, showOrders);
    }

    function showOrders(orders){
        s = "";
        for(let o=0; o<orders.length; o++){
            s+= "<div class='blog-item-content bg-white p-5'><h3 class='mt-3 mb-3'><a href='blog-single.html'>Orden No. "+orders[o].ordenId+"</a></h3>";
            for(let k=0; k<orders[o].servicios.length; k++){
                s += "<p class='mb-4'>Servicio: "+orders[o].servicios[k].name+"  Precio: $"+orders[o].servicios[k].price+"</p>";
            }
            for(let j=0; j<orders[o].products.length; j++){
                s += "<p class='mb-4'>Producto: "+orders[o].products[j].name+"  Precio: $"+orders[o].products[j].price+"</p>";
            }
            
            s += "<p class='mb-4'>Valor Total: "+orders[o].totalValue+"</p>";
            s += "<p class='mb-4'>Estado de la orden: "+orders[o].statusOrder+"</p>";
            s += "<a href='#' class='btn btn-small btn-main btn-round-full'>Avanzar Estado</a>  ";
            s += '  <button class="btn btn-small btn-main btn-round-full" onclick="chat.openForm('+orders[o].ordenId+', '+userId+')" style="background-color: #F9F816; color: black;">Chat</button></div>';
            // s += '  <button class="btn btn-small btn-main btn-round-full" onclick="chat.openForm('+orders[o].ordenId+')" style="background-color: #F9F816; color: black;">Chat</button></div>';
        }
        $("#lista").html(s);
    }

    return {
        isLogged: isLogged
    }
})();