package com.somospnt.sofiabot.config;

import com.somospnt.sofiabot.ab.Bot;
import com.somospnt.sofiabot.ab.MagicStrings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfig {

    @Value("${com.somospnt.sofiabot.bot.nombre}")
    private String nombreBot;

    @Value("${com.somospnt.sofiabot.bot.accion}")
    private String accion;

    @Bean
    public Bot bot() {
        MagicStrings.root_path = System.getProperty("user.dir");
        Bot bot = new Bot(nombreBot, MagicStrings.root_path, accion);
        return bot;
    }
}
