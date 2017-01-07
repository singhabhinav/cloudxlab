var app = require('express')();
var http = require('http')
var path = require('path');

var kafka = require('kafka-node')
var Consumer = kafka.Consumer
var client1 = new kafka.Client()
var client2 = new kafka.Client()
var twitterTopicConsumer = new Consumer(client1, [{ topic: 'twitterTopic', partition: 0 }], {autoCommit: true});
var sentimentTopicConsumer = new Consumer(client2, [{ topic: 'sentimentTopic', partition: 0 }], {autoCommit: true});

var finalhandler = require('finalhandler');
var serveStatic = require('serve-static');
var serve = serveStatic("./");
var server = http.createServer(function(req, res) {
  var done = finalhandler(req, res);
  serve(req, res, done);
});
var io = require('socket.io').listen(server);

app.get('/web', function(req, res){
	console.log("coming here");
  res.sendFile(path.relative('index.html'));
});

twitterTopicConsumer.on('message', function (message) {
	console.log(message.value);
    io.emit('twitterData', message.value);
});

sentimentTopicConsumer.on('message', function (message) {
	console.log(message.value);
    io.emit('sentimentData', message.value);
});

server.listen(8888, function(){
  console.log("Use this url to access the application - http://localhost:8888/web" )
});