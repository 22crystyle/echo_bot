package org.echoBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class VkEchoBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(VkEchoBotApplication.class, args);
    }

}
