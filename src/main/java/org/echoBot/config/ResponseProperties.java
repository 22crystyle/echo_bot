package org.echoBot.config;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Component;

/**
 * Конфигурационный компонент для параметров VK API.
 * <p>
 * Класс {@code ResponseProperties} используется для маппинга настроек VK API, указанных в
 * application.properties с префиксом <code>vk</code>. Он содержит параметры,
 * необходимые для аутентификации и взаимодействия с VK API, такие как токен доступа, токен подтверждения, URL API
 * и версия API.
 * </p>
 *
 * <p>Пример конфигурации в application.properties:</p>
 * <pre>
 *     vk.token=your_access_token
 *     vk.confirmation=your_confirmation_token
 *     vk.apiUrl=https://api.vk.com/method/messages.send
 *     vk.v=5.199
 * </pre>
 *
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 */
@Component @ConfigurationProperties(prefix = "vk")
@Data @RequiredArgsConstructor
public class ResponseProperties {
    private String token;
    private String confirmation;
    private String apiUrl;
    private String v;
}
