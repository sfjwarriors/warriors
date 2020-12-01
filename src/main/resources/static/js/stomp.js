var stomp = (function () {
  var connected = false;
  var stompClient = null;
  var connectAndSubscribe = function (callback, storeName) {
    disconnect();
    var socket = new SockJS("/stompendpoint");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
      if (connected) {
        disconnect();
      }
      stompClient.subscribe("/store." + storeName, function (message) {
        setConnected(true);
        callback(message);
      });
    });
  };

  function sends(ruta, mensaje){
    stompClient.send("/store."+ruta, {}, mensaje);
    stompClient.send("/app."+ruta, {}, mensaje);
  }

  function setConnected(con) {
    connected = con;
  }

  var disconnect = function () {
    if (stompClient !== null) {
      stompClient.disconnect();
    }
    setConnected(false);
  };

  return {
    connectAndSubscribe: connectAndSubscribe,
    disconnect: disconnect,
    sends: sends
  };
})();
