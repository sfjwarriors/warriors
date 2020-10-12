var login = (function () {
    var url = 'http://mecaclic.herokuapp.com';
    //var url = 'http://localhost:8080';
    function isLogged() {
        axios.get(url+'/login/'+window.sessionStorage.token
        ).then(function f (res){
            if(window.sessionStorage.token==res.data.token){
                $("#userbtn").text("Cerrar Sesión");
                document.getElementById("userbtn").onclick = closeSession;
                console.log("Esta logueado:", window.sessionStorage.token);
            }
        }).catch(function (error) {
            alert(error.response.data);
        })
    }

    function login(){
        axios.post(url+'/login', {
            email: $("#email").val(),
            password: $("#password").val()
        }).then(function f (res){
            console.log(res.data);
            window.sessionStorage.token = res.data;
            console.log(window.sessionStorage.token);
            window.location.replace("index.html");
        }).catch(function (error) {
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

    return {
        isLogged: isLogged,
        login: login,
        validate: validate,
        loginPage: loginPage,
        closeSession: closeSession
    }
})();