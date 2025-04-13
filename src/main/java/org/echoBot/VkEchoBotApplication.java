package org.echoBot;

import org.echoBot.config.ResponseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableConfigurationProperties(ResponseProperties.class)
@SpringBootApplication
public class VkEchoBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(VkEchoBotApplication.class, args);
    }

}
