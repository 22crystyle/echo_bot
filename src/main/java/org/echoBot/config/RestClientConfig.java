package org.echoBot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    private final ResponseProperties responseProperties;

    @Autowired
    public RestClientConfig(ResponseProperties responseProperties) {
        this.responseProperties = responseProperties;
    }

    @Bean
    public RestClient createRestClient() {
        return RestClient.create(responseProperties.getApiUrl());
    }
}
