var login = (function () {
    var logged = false;

    function isLogged() {
        console.log("Esta logueado:", logged, window.sessionStorage.token);
        if(logged) {
            $("#userbtn").text("Perfil");
        }
        return logged;
    }

    function login(){
        axios.post('http://localhost:8080/login', {
            email: $("#email").val(),
            password: $("#password").val()
        }).then(function f (res){
            if (res.data=="Success") {
                logged = true;
                window.sessionStorage.token = "765376253736";
                console.log(window.sessionStorage.token);
                window.location.replace("index.html");
            }
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
        if (logged) {
            window.location.replace("index.html");
        }
    }

    return {
        isLogged: isLogged,
        login: login,
        validate: validate
    }
})();