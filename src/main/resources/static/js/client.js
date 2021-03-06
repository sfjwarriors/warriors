var client = (function () {

    //Azure
    // var url = 'https://mecaclicks.azurewebsites.net/';
    //Heroku
   var url = 'http://mecaclic.herokuapp.com';
    //Local
    //   var url = 'http://localhost:8080';
    function isLogged(callback) {
        if(window.sessionStorage.token!=null) {
            axios.get(url+'/login/'+window.sessionStorage.token
            ).then(function f (res){
                if(window.sessionStorage.token==res.data.token){
                    callback(res.data);
                }
            }).catch(function (error) {
                alert(error.response.data);
            })
        }
    }

    function getStore(idMechanic, callback) {
        axios.get(url+'/stores/'+idMechanic
        ).then(function f (res){
            callback(res.data);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }
    function getStoreById(idStore, callback) {
        var s = [];
        axios.get(url+'/stores/id/'+idStore
        ).then(function f (res){
            s.push(res.data);
        }).catch(function (error) {
            alert(error.response.data);
        })
        return s;
    }

    function login(callback){
        document.getElementById("preload").style.visibility="visible";
        axios.post(url+'/login', {
            email: $("#email").val(),
            password: $("#password").val()
        }).then(function f (res){
            window.sessionStorage.token = res.data;
            window.location.replace("index.html");
        }).catch(function (error) {
            document.getElementById("preload").style.visibility = "hidden";
            alert(error.response.data);
            if (error.response.data == "Incorrect password") {
                document.getElementById("password").value = "";
            }
            if (error.response.data == "Email doesn't found") {
                document.getElementById("email").value = "";
                document.getElementById("password").value = "";
            }
        })
    }

    function validate(callback) {
        if(window.sessionStorage.token!=null) {
            axios.get(url+'/login/'+window.sessionStorage.token
            ).then(function f (res){
                if(window.sessionStorage.token==res.data.token){
                    window.location.replace("index.html");
                }
            }).catch(function (error) {
                alert(error.response.data);
            })
        }
    }

    function loginPage() {
        window.location.replace("login.html");
    }

    function closeSession() {
        window.sessionStorage.token= undefined;
        loginPage();
    }

    function getStores(callback) {
        axios.get(url+'/stores'
        ).then(function f (res){
            callback(res.data);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function getMechanics(callback) {
        axios.get(url+'/users/mechanics'
        ).then(function f (res){
            callback(res.data);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function updateStore(store, callback){
        axios.put(url+'/stores', {
            id: store.id,
            storeName: store.storeName,
            fkMechanic: store.fkMechanic,
            products: store.products,
            servicios: store.servicios,
            ordens: store.ordens
        }).then(function f (res){
            callback(res.data);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function registerUsers(nombre,apellido,email,celular,direccion,password,tipoUsuario,callback){
        var envio = {name: nombre, lastName:apellido,email:email,cellphone:celular,rol:tipoUsuario,address:direccion,password:password}

        axios.post(url+'/users',
            envio
          ).then(function f (res){
            callback(res.data);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function createOrden(orden, callback){
        axios.post(url+'/orders',
            orden
          ).then(function f (res){
            callback(res.data);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function createCarts(carritos, callback){
        for(let i=0; i<carritos.length; i++){
            axios.post(url+'/carts',
                carritos[i]
            ).then(function f (res){
                // callback(res.data);
            }).catch(function (error) {
                alert(error.response.data);
            })
        }
        callback();
    }

    function getProductById(idProduct, callback) {
        axios.get(url+'/products/'+idProduct
        ).then(function f (res){
            callback(res.data);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function getServiceById(idService, callback) {
        axios.get(url+'/services/'+idService
        ).then(function f (res){
             callback(res.data);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function updateProduct(data, callback){
        axios.put(url+'/products',
            data
          ).then(function f (res){
            callback(res.data);
            alert("Se actualizo el Producto: "+data.name);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function updateService(data, callback){
        axios.put(url+'/services',
            data
          ).then(function f (res){
            callback(res.data);
            alert("Se actualizo el Servicio: "+data.name);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function createProduct(data, callback){
        axios.post(url+'/products',
            data
          ).then(function f (res){
            callback(res.data);
            alert("Se creo el Producto");
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function createService(data, callback){
        axios.post(url+'/services',
            data
          ).then(function f (res){
            callback(res.data);
            alert("Se creo el Servicio");
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function deleteProduct(data, callback){
        axios.delete(url+'/products/'+data.id
        ).then(function f (res){
            callback(res.data);
            alert("Se elimino el Producto");
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function deleteService(data, callback){
        axios.delete(url+'/services/'+data.id
        ).then(function f (res){
            callback(res.data);
            alert("Se elimino el Servicio");
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function getOrdersClient(idUser, callback){
        axios.get(url+'/users/'+idUser+'/orders'
        ).then(function f (res){
             callback(res.data);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    return {
        isLogged: isLogged,
        login: login,
        validate: validate,
        loginPage: loginPage,
        closeSession: closeSession,
        getStore: getStore,
        updateStore: updateStore,
        getProductById: getProductById,
        getServiceById: getServiceById,
        registerUsers:registerUsers,
        updateProduct: updateProduct,
        updateService: updateService,
        createProduct: createProduct,
        createService: createService,
        deleteProduct: deleteProduct,
        deleteService: deleteService,
        getStores: getStores,
        getMechanics: getMechanics,
        createOrden: createOrden,
        createCarts: createCarts,
        getOrdersClient: getOrdersClient,
        getStoreById: getStoreById
    }
})();
