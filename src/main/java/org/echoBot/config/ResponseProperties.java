package org.echoBot.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

/**
 * Конфигурационный компонент для параметров VK API.
 * <p>
 * Класс {@code ResponseProperties} используется для маппинга настроек VK API, указанных в
 * application.properties с префиксом <code>vk</code>. Он содержит параметры,
 * необходимые для аутентификации и взаимодействия с VK API, такие, как токен доступа, токен подтверждения, URL API
 * и версия API.
 * </p>
 *
 * <p>Пример конфигурации в application.properties:</p>
 * <pre>
 *     vk.token=your_access_token
 *     vk.confirmation=your_confirmation_token
 *     vk.apiUrl=<a href="https://api.vk.com/method/messages.send">...</a>
 *     vk.v=5.199
 * </pre>
 *
 * @see ConfigurationProperties
 */
@ConfigurationProperties(prefix = "vk")
@Getter
public class ResponseProperties {
    private final String token;
    private final String confirmation;
    private final String apiUrl;
    private final String v;

    @ConstructorBinding
    public ResponseProperties(String token, String confirmation, String apiUrl, String v) {
        this.token = token;
        this.confirmation = confirmation;
        this.apiUrl = apiUrl;
        this.v = v;
    }
}
