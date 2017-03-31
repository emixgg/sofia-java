const config = config = require('config');

const BOT_API = (process.env.BOT_API) ? process.env.BOT_API : config.get('botApi');

if (!(BOT_API)) {
    console.error("Falta configurar la api del bot");
    process.exit(1);
}

module.exports = {
    BOT_API: BOT_API
};


