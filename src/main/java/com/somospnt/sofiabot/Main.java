package com.somospnt.sofiabot;

import com.somospnt.sofiabot.ab.AIMLProcessor;
import com.somospnt.sofiabot.ab.Bot;
import com.somospnt.sofiabot.ab.Chat;
import com.somospnt.sofiabot.ab.MagicBooleans;
import com.somospnt.sofiabot.ab.MagicStrings;
import com.somospnt.sofiabot.ab.PCAIMLProcessorExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static Bot bot;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        MagicStrings.root_path = System.getProperty("user.dir");
       
        log.info("Working Directory = " + MagicStrings.root_path);
        AIMLProcessor.extension = new PCAIMLProcessorExtension();
        inicializarBot(args);
    }

    public static void inicializarBot(String[] args) {
        String nombreBot = "sofia";
        String action = "chat";
        log.info(MagicStrings.programNameVersion);
        log.info("trace mode = " + MagicBooleans.trace_mode);
        bot = new Bot(nombreBot, MagicStrings.root_path, "aiml2csv");
        //
    }

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

        log.info("STATE=" + body + ":THAT=" + chatSession.thatHistory.get(0).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
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
