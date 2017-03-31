package com.somospnt.ab.cli;

import com.somospnt.ab.AIMLProcessor;
import com.somospnt.ab.Bot;
import com.somospnt.ab.Chat;
import com.somospnt.ab.Graphmaster;
import com.somospnt.ab.MagicBooleans;
import com.somospnt.ab.MagicStrings;
import com.somospnt.ab.PCAIMLProcessorExtension;
import com.somospnt.ab.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

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
        Graphmaster.enableShortCuts = true;
        Bot bot = new Bot(nombreBot, MagicStrings.root_path, action); //
        if (bot.brain.getCategories().size() < 100) {
            bot.brain.printgraph();
        }

        testChat(bot, MagicBooleans.trace_mode);
    }

    public static void testChat(Bot bot, boolean traceMode) {
        Chat chatSession = new Chat(bot);
        bot.brain.nodeStats();
        MagicBooleans.trace_mode = traceMode;
        String textLine = "";
        while (true) {
            System.out.print("Human: ");
            textLine = IOUtils.readInputTextLine();
            if (textLine == null || textLine.length() < 1) {
                textLine = MagicStrings.null_input;
            }
            if (textLine.equals("q")) {
                System.exit(0);
            } else {
                String request = textLine;
                log.debug("STATE=" + request + ":THAT=" + chatSession.thatHistory.get(0).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                String response = chatSession.multisentenceRespond(request);
                while (response.contains("&lt;")) {
                    response = response.replace("&lt;", "<");
                }
                while (response.contains("&gt;")) {
                    response = response.replace("&gt;", ">");
                }
                log.info("Robot: " + response);

            }

        }
    }

}
