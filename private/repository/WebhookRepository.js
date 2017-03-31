const request = require('request'),
        config = require('../config/BotConfiguration'),
        Promise = require("promise");

function charlar(mensaje) {
    console.log("Entrando a charlar con el bot");
    return new Promise(function (resolve, reject) {
        request({
            uri: config.BOT_API,
            method: 'POST',
            json: mensaje

        }, function (error, response, body) {
            if (!error && response.statusCode == 200) {
                console.log("Devolviendo respuesta existente %s", body);
                resolve(body);
            } else {
                console.error("Error al llamar a BOT API", response.statusCode, response.statusMessage, body.error);
                return reject(error);
            }
        });

    });
}


module.exports = {
    charlar: charlar
}
