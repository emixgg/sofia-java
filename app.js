'use strict';

const   bodyParser = require('body-parser'),
        crypto = require('crypto'),
        express = require('express'),
        config = require('./private/config/facebookConfiguration'),
        https = require('https');
var verifier = require('./private/util/verifier');
var webhookRouter = require('./private/router/FacebookRouter');

var app = express();

app.set('port', process.env.PORT || 5000);
app.set('view engine', 'ejs');
app.use(bodyParser.json({verify: verifier.verifyRequestSignature}));
app.use(express.static('public'));


app.use('/', webhookRouter);

app.listen(app.get('port'), function () {
    console.log('Node app is running on port', app.get('port'));
});

module.exports = app;

