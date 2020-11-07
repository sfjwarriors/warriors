var formuScript = (function(){
	var nombre, apellido , email, direccion, celular, password1, password2, mecanico, usuario, expresion ;
	expresion = /\w+@\w+\.+[a-z]/;
	lista = [];
	//archivo = client;

	function isLogged() {
        if(window.sessionStorage.token!=null) {
			// client.isLogged(updateBtns);
			window.location.replace("index.html");
        } else {
			stomp.connectAndSubscribe(imprime, 'all');
            // window.location.replace("login.html");
        }
    }

	var crearFormulario = function(){
		nombre = $("#nombre").val();
		apellido = $("#apellido").val();
		email = $("#email").val();
		direccion = $("#direccion").val();
		celular = $("#celular").val();
		password1 = $("#password1").val();
		password2 = $("#password2").val();
		mecanico = document.getElementById("mecanico");
		usuario = document.getElementById("usuario"); 
		if (nombre==="" ||apellido==="" ||email==="" || direccion==="" ||celular==="" ||password1==="" ||password2==="" ){
			alert("Todos los campos son requeridos");
			return false;
		}else if(celular.length > 10){
			alert("El celular es muy largo")
			return false;
		}else if (isNaN(celular)){
			alert("El celular no es un numero")
			return false;
		}else if(password1 != password2){
			alert("Las contraseñas no coinciden")
			return false 
		}else if(!expresion.test(email)){
			alert("El correo no es valido")
			return false 
		}else if(mecanico.checked){
			tipoUsuario = 'MECA';
			// stomp.sends('all');
			// stomp.stompClient.send("/store/all", {}, "newStore");
            // stomp.stompClient.send("/app/all", {}, "newStore");
		}else if(usuario.checked){
			tipoUsuario = 'USER';
		}
		client.registerUsers(nombre,apellido,email,celular,direccion,password2,tipoUsuario,showRegisterUser);
	};

	function imprime(mensaje){
        console.log(mensaje);
    }
	
	var uncheck = function(){
		var checkbox1 = document.getElementById("mecanico");
		var checkbox2 = document.getElementById("usuario"); 
	   checkbox1.onclick = function(){ 
	   		if(checkbox1.checked != false){ 
	   			checkbox2.checked =null; }
			} 
	   checkbox2.onclick = function(){ 
	   		if(checkbox2.checked != false){ 
	  	 		checkbox1.checked=null;
			}
		} 
	}

	function showRegisterUser(data) {
        if(data=="Success") {
			stomp.sends('all');
			alert("Se registro el usuario");

			location.href="login.html";
		}
	}

	return {
		crearFormulario:crearFormulario,
		uncheck:uncheck,
		imprime: imprime,
		isLogged: isLogged
	}


})();
/** 
form.addEventListener('Registrarse', e => {
	e.preventDefault();
	
	checkInputs();
});

function checkInputs() {
	console.log(usuario);
	// trim to remove the whitespaces
	const usuarioValue = usuario.value.trim();
	const emailValue = email.value.trim();
	const passwordValue = password.value.trim();
	const password2Value = password2.value.trim();
	
	if(usuarioValue === '') {
		setErrorFor(usuario, 'Noi puede dejar el usuairo en blanco');
	} else {
		setSuccessFor(usuario);
	}
	
	if(emailValue === '') {
		setErrorFor(email, 'No puede dejar el email en blanco');
	} else if (!isEmail(emailValue)) {
		setErrorFor(email, 'No ingreso un email válido');
	} else {
		setSuccessFor(email);
	}
	
	if(passwordValue === '') {
		setErrorFor(password, 'Password no debe ingresar en blanco.');
	} else {
		setSuccessFor(password);
	}
	
	if(password2Value === '') {
		setErrorFor(password2, 'Password2 no debe ngresar en blanco');
	} else if(passwordValue !== password2Value) {
		setErrorFor(password2, 'Passwords no coinciden');
	} else{
		setSuccessFor(password2);
	}
}

function setErrorFor(input, message) {
	const formControl = input.parentElement;
	const small = formControl.querySelector('small');
	formControl.className = 'form-control error';
	small.innerText = message;
}

function setSuccessFor(input) {
	const formControl = input.parentElement;
	formControl.className = 'form-control success';
}

function isEmail(email) {
	return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}
*/