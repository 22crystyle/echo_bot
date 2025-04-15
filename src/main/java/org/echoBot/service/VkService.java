package org.echoBot.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.echoBot.config.ResponseProperties;
import org.echoBot.config.RestClientConfig;
import org.echoBot.dto.MessageMapper;
import org.echoBot.dto.request.MessageRequest;
import org.echoBot.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Сервис для отправки сообщений через VK API.
 * <p>
 * Класс отвечает за формирование и отправку HTTP-запроса к VK API для передачи сообщений,
 * используя настройки, заданные в {@link ResponseProperties} и преобразование запроса через {@link MessageMapper}.
 * </p>
 *
 * @see MessageMapper
 * @see ResponseProperties
 * @see MessageRequest
 * @see MessageResponse
 */
@Slf4j
@Service
@Getter
public class VkService {
    private final MessageMapper messageMapper;
    private final RestClientConfig restClientConfig;

    @Autowired
    public VkService(MessageMapper messageMapper, RestClientConfig restClientConfig) {
        this.messageMapper = messageMapper;
        this.restClientConfig = restClientConfig;
    }

    /**
     * Отправляет сообщение через VK API в асинхронном режиме.
     * <p>
     * Метод получает входящий {@link MessageRequest}, преобразует его в {@link MessageResponse}
     * с помощью {@link MessageMapper}, формирует набор параметров для HTTP-запроса
     * и отправляет POST-запрос с типом контента {@code application/x-www-form-urlencoded}.
     * </p>
     *
     * @param messageRequest объект запроса, содержащий данные сообщения,
     *                       такие как ID получателя, текст, и другие параметры
     */
    @Async
    public void sendMessage(MessageRequest messageRequest) {
        try {
            MessageResponse message = messageMapper.toResponse(messageRequest);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("peer_id", String.valueOf(message.peer_id()));
            params.add("message", "Вы сказали: " + message.text());
            params.add("access_token", message.access_token());
            params.add("v", message.v());
            params.add("random_id", String.valueOf(message.random_id()));

            log.info("Отправка сообщения с параметрами: {}", params);

            String response = restClientConfig.createRestClient().post()
                    .body(params)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .retrieve()
                    .body(String.class);

            System.out.println(response);

            log.info("Ответ VK: {}", response);
        } catch (Exception e) {
            log.error("Ошибка отправки сообщения в VK API", e);
        }
    }
}
