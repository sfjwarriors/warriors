var stomp = (function () {
  var connected = false;
  var stompClient = null;
  var connectAndSubscribe = function (callback, storeName) {
    disconnect();
    console.info("Connecting to WS...");
    var socket = new SockJS("/stompendpoint");
    stompClient = Stomp.over(socket);

    //subscribe to /topic/TOPICXX when connections succeed
    stompClient.connect({}, function (frame) {
      console.log("Connected: " + frame);
      if (connected) {
        disconnect();
      }
      stompClient.subscribe("/store." + storeName, function (message) {
        //alert("evento recibido");
        setConnected(true);
        callback(message);
      });
    });
  };

  function sends(ruta, mensaje){
    stompClient.send("/store."+ruta, {}, mensaje);
    stompClient.send("/app."+ruta, {}, mensaje);
    // stompClient.send("/app/buyticket."+cinema + '.' + fecha + fechaHora + '.' + movieSelected, {}, JSON.stringify(st));
    // stompClient.send("/topic/buyticket."+cinema + '.' + fecha + fechaHora + '.' + movieSelected, {}, JSON.stringify(st));
  }

  function setConnected(con) {
    connected = con;
  }

  var disconnect = function () {
    if (stompClient !== null) {
      stompClient.disconnect();
    }
    setConnected(false);
    // console.log("Disconnected");
  };

  return {
    connectAndSubscribe: connectAndSubscribe,
    disconnect: disconnect,
    sends: sends
  };
})();
