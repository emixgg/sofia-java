package com.somospnt.sofiabot.service.impl;

import com.somospnt.sofiabot.ab.Bot;
import com.somospnt.sofiabot.ab.Chat;
import com.somospnt.sofiabot.service.MensajeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private Bot bot;
    private static final Logger logger = LoggerFactory.getLogger(MensajeServiceImpl.class);

    @Override
    public String responder(String mensaje) {
        Chat chatSession = new Chat(bot);
        bot.brain.nodeStats();

        StringBuilder mensajed = new StringBuilder("STATE=")
                .append(mensaje).append(":THAT=")
                .append(chatSession.thatHistory.get(0).get(0))
                .append(":TOPIC=").append(chatSession.predicates.get("topic"));

        logger.debug(mensajed.toString());
        String response = chatSession.multisentenceRespond(mensaje);
        while (response.contains("&lt;")) {
            response = response.replace("&lt;", "<");
        }
        while (response.contains("&gt;")) {
            response = response.replace("&gt;", ">");
        }
        logger.info("Robot: " + response);
        return response;
    }

}
