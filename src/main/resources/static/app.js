var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
        var sock = new SockJS('/as');
        sock.onopen = function() {
         console.log('open');
         sock.send('test');
     };

     sock.onmessage = function(e) {
         console.log('message', e.data);
         sock.close();
     };

     sock.onclose = function() {
         console.log('close');
     };
//    stompClient = Stomp.over(socket);
//    stompClient.connect({}, function (frame) {
//        setConnected(true);
//        console.log('Connected: ' + frame);
//        stompClient.subscribe('/topic/greetings', function (greeting) {
//        var jsonData = JSON.parse(greeting.body);
//        jsonData = JSON.parse(jsonData.content);
//        $("#session_id").text(jsonData.length);
//        var rows;
//            jsonData.map(function(data){
//               rows+=`<tr><td>`+data+`</td></tr>`;
//
//            })
//            $("#greetings").html(rows);
//        });
//    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});
