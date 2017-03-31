package com.somospnt.sofiabot.controller;

import com.somospnt.sofiabot.SofiaApplication;
import com.somospnt.sofiabot.ab.Bot;
import com.somospnt.sofiabot.ab.Chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComunicacionController {

    @Autowired
    private Bot bot;

    private static final Logger log = LoggerFactory.getLogger(SofiaApplication.class);

    @GetMapping("/saludar")
    public String devolverSaludo() {

        Chat chatSession = new Chat(bot);

        bot.brain.nodeStats();

        String response = chatSession.multisentenceRespond("Hello");
        while (response.contains("&lt;")) {
            response = response.replace("&lt;", "<");
        }
        while (response.contains("&gt;")) {
            response = response.replace("&gt;", ">");
        }
        log.info("Robot: " + response);
        return response;
    }

    @PostMapping("/charlar")
    public String charlar(@RequestBody String body) {

        Chat chatSession = new Chat(bot);

        bot.brain.nodeStats();

        log.debug("STATE=" + body + ":THAT=" + chatSession.thatHistory.get(0).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
        String response = chatSession.multisentenceRespond(body);
        while (response.contains("&lt;")) {
            response = response.replace("&lt;", "<");
        }
        while (response.contains("&gt;")) {
            response = response.replace("&gt;", ">");
        }
        log.info("Robot: " + response);
        return response;
    }

}
