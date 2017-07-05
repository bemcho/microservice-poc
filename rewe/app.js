'use strict';

var Primus = require('primus')
    , express = require('express')
    , bodyParser = require('body-parser')
    , http = require('http')
    , app = express()
    , server = http.createServer(app);

app.set('port', process.env.PORT || 3000);
app.use('/public', express.static(__dirname + '/public'));
app.use(bodyParser.json());

app.get('/', function(req, res) {
  res.sendFile(__dirname + '/public/index.html');
});

server.listen(app.get('port'), function() {
  console.log('Express server listening on port ' + app.get('port'));
});
