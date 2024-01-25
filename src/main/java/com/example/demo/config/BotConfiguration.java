package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

import javax.annotation.PostConstruct;

@EnableScheduling
@ComponentScan(basePackageClasses = BotConfiguration.class)
@Configuration
public class BotConfiguration {
    @PostConstruct
    public void runBot(){
        ApiContextInitializer.init();
    }

    @Bean
    public DefaultBotOptions defaultBotOptions() {
        return ApiContext.getInstance(DefaultBotOptions.class);
    }
}
