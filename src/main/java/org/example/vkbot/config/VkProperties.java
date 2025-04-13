package org.example.vkbot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "vk")
@Data
public class VkProperties {
    private String token;
    private String confirmation;
    private String apiUrl;
    private String v;
}
