var stomp = (function () {
  var connected = false;
  var stompClient = null;
  var connectAndSubscribe = function (callback, storeName) {
    console.log("hello");
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

  function setConnected(con) {
    connected = con;
  }

  var disconnect = function () {
    if (stompClient !== null) {
      stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
  };

  return {
    connectAndSubscribe: connectAndSubscribe,
    disconnect: disconnect,
  };
})();
