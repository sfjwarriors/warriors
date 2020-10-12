var login = (function () {
    //var url = 'http://mecaclic.herokuapp.com';
    var url = 'http://localhost:8080';
    function isLogged() {
        axios.get(url+'/login/'+window.sessionStorage.token
        ).then(function f (res){
            if(window.sessionStorage.token==res.data.token){
                //$("#userbtn").text("Cerrar Sesión");
                //document.getElementById("userbtn").onclick = closeSession;
                //console.log("Esta logueado:", window.sessionStorage.token);
                updateBtns(res.data);
            }
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function updateBtns(user) {
        $("#userbtn").text("Cerrar Sesión");
        document.getElementById("userbtn").onclick = closeSession;
        $("#perfilbtn").text(user.name);
        console.log(user);
        document.getElementById("perfilbtn").style.visibility="visible";
        if(user.rol=='MECA') {
            getStore(user.id);
            $("#startbtn").text("Mi Taller");
            //document.getElementById("startbtn").onclick = LodirigeAlTaller;
        } else {
            $("#startbtn").text("Pide Ya!");
            //document.getElementById("startbtn").onclick = LoDirigeALasListasDeTalleres;
        }
    }

    function getStore(idMechanic) {
        axios.get(url+'/stores/'+idMechanic
        ).then(function f (res){
            setStore(res.data);
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function setStore(store) {
        $("#storebtn").text(store.storeName);
        document.getElementById("storebtn").style.visibility="visible";
        var storeTmp = store;
        console.log("stmp", storeTmp);
    }

    function login(){
        document.getElementById("preload").style.visibility="visible";
        axios.post(url+'/login', {
            email: $("#email").val(),
            password: $("#password").val()
        }).then(function f (res){
            console.log(res.data);
            window.sessionStorage.token = res.data;
            console.log(window.sessionStorage.token);
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

    function validate() {
        axios.get(url+'/login/'+window.sessionStorage.token
        ).then(function f (res){
            if(window.sessionStorage.token==res.data.token){
                window.location.replace("index.html");
            }
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function loginPage() {
        window.location.replace("login.html");
    }

    function closeSession() {
        window.sessionStorage.token=null;
        console.log("cierra", window.sessionStorage.token);
        loginPage();
    }

    function goProfile() {
        console.log("lo envío a su perfil");
        //window.location.replace("profile.html");
    }

    return {
        isLogged: isLogged,
        login: login,
        validate: validate,
        loginPage: loginPage,
        closeSession: closeSession,
        goProfile: goProfile
    }
})();