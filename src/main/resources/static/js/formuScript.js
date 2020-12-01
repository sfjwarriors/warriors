var formuScript = (function(){
	var nombre, apellido , email, direccion, celular, password1, password2, mecanico, usuario, expresion ;
	expresion = /\w+@\w+\.+[a-z]/;
	lista = [];

	function isLogged() {
		stomp.connectAndSubscribe(imprime, 'all');
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
		}else if(usuario.checked){
			tipoUsuario = 'USER';
		}
		client.registerUsers(nombre,apellido,email,celular,direccion,password2,tipoUsuario,showRegisterUser);
	};

	function imprime(mensaje){
        // console.log(mensaje);
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
			stomp.sends('all', 'nueva tienda');
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
