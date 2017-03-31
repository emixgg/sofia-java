'use strict';

const winston = require('winston')

var express = require('express');
var app = express();

const   bodyParser = require('body-parser'),
        crypto = require('crypto'),
        config = require('./private/config/facebookConfiguration'),
        https = require('https');
var verifier = require('./private/util/verifier');
var webhookRouter = require('./private/router/FacebookRouter');


app.set('port', process.env.PORT || 5000);
app.set('view engine', 'ejs');
app.set('logger', winston);
app.use(bodyParser.json({verify: verifier.verifyRequestSignature}));
app.use(express.static('public'));


app.use('/', webhookRouter);

app.listen(app.get('port'), function () {
    console.log('Node app is running on port', app.get('port'));
});

module.exports = app;

