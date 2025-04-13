package org.echoBot.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.echoBot.config.ResponseProperties;
import org.echoBot.dto.MessageMapping;
import org.echoBot.dto.request.MessageRequest;
import org.echoBot.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

/**
 * Сервис для отправки сообщений через VK API.
 * <p>
 * Класс отвечает за формирование и отправку HTTP-запроса к VK API для передачи сообщений,
 * используя настройки, заданные в {@link ResponseProperties} и преобразование запроса через {@link MessageMapping}.
 * </p>
 *
 * @see MessageMapping
 * @see ResponseProperties
 * @see MessageRequest
 * @see MessageResponse
 */
@Service @Getter @AllArgsConstructor
public class VkService {
    private final MessageMapping messageMapping;
    private final String vkApiUrl;

    @Autowired
    public VkService(ResponseProperties responseProperties, MessageMapping messageMapping) {
        this.vkApiUrl = responseProperties.getApiUrl();
        this.messageMapping = messageMapping;
    }

    /**
     * Отправляет сообщение через VK API в асинхронном режиме.
     * <p>
     * Метод получает входящий {@link MessageRequest}, преобразует его в {@link MessageResponse}
     * с помощью {@link MessageMapping}, формирует набор параметров для HTTP-запроса
     * и отправляет POST-запрос с типом контента {@code application/x-www-form-urlencoded}.
     * </p>
     *
     * @param messageRequest объект запроса, содержащий данные сообщения,
     *                       такие как ID получателя, текст, и другие параметры
     */
    @Async
    public void sendMessage(MessageRequest messageRequest) {
        RestClient restClient = RestClient.create(vkApiUrl);
        MessageResponse message = messageMapping.toResponse(messageRequest);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("peer_id", String.valueOf(message.peer_id()));
        params.add("message", "Вы сказали: " + message.text());
        params.add("access_token", message.access_token());
        params.add("v", message.v());
        params.add("random_id", String.valueOf(message.random_id()));

        restClient.post()
                .body(params)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .body(String.class);
    }
}
