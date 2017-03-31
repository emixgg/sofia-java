const config = require('config'),
      webhookService = require('../service/WebhookService');

const APP_SECRET = (process.env.MESSENGER_APP_SECRET) ? process.env.MESSENGER_APP_SECRET : config.get('appSecret');

const VALIDATION_TOKEN = (process.env.MESSENGER_VALIDATION_TOKEN) ? (process.env.MESSENGER_VALIDATION_TOKEN) : config.get('validationToken');

const PAGE_ACCESS_TOKEN = (process.env.MESSENGER_PAGE_ACCESS_TOKEN) ? (process.env.MESSENGER_PAGE_ACCESS_TOKEN) : config.get('pageAccessToken');

const SERVER_URL = (process.env.SERVER_URL) ? (process.env.SERVER_URL) : config.get('serverURL');

if (!(APP_SECRET && VALIDATION_TOKEN && PAGE_ACCESS_TOKEN && SERVER_URL)) {
    console.error("Missing config values");
    process.exit(1);
}   


function resolverChallenge(req, res) {
    if (req.query['hub.mode'] === 'subscribe' && req.query['hub.verify_token'] === VALIDATION_TOKEN) {
        res.status(200).send(req.query['hub.challenge']);
    } else {
        res.sendStatus(403);
    }
}

function recibirMensaje(req, res) {
    var data = req.body;
    if (data.object == 'page') {
        data.entry.forEach(function (pageEntry) {
            pageEntry.messaging.forEach(function (messagingEvent) {
                if (messagingEvent.optin) {
                    webhookService.receivedAuthentication(messagingEvent);
                } else if (messagingEvent.message) {
                    webhookService.receivedMessage(messagingEvent);
                } else if (messagingEvent.delivery) {
                    webhookService.receivedDeliveryConfirmation(messagingEvent);
                } else if (messagingEvent.postback) {
                    webhookService.receivedPostback(messagingEvent);
                } else if (messagingEvent.read) {
                    webhookService.receivedMessageRead(messagingEvent);
                } else if (messagingEvent.account_linking) {
                    webhookService.receivedAccountLink(messagingEvent);
                } else {
                    console.log("Webhook received unknown messagingEvent: ", messagingEvent);
                }
            });
        });
        res.sendStatus(200);
    }
}

module.exports = {
    resolverChallenge: resolverChallenge,
    recibirMensaje: recibirMensaje
}
