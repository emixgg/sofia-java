var express = require('express');

var webhookRouter = express.Router();

var webhookController = require('../controller/WebhookController');
var autorizacionController = require('../controller/AutorizacionController')

webhookRouter.get('/webhook', webhookController.resolverChallenge);
webhookRouter.post('/webhook', webhookController.recibirMensaje);
webhookRouter.get('/authorize', autorizacionController.autorizar);

module.exports = webhookRouter;
