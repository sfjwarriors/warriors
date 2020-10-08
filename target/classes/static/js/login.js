login = (function () {
    function login() {
        console.log("hello");
        axios.post('http://localhost:8080/login', {
            email: $("#email"), password: $("#password")
            }).then(function f (res){
                console.log(res);
        })
    }
    return{
        login: login
    }
})();