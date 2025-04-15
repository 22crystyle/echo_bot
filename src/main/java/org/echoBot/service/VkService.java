package org.echoBot.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.echoBot.dto.MessageMapper;
import org.echoBot.dto.request.MessageRequest;
import org.echoBot.dto.response.MessageResponse;
import org.echoBot.rest.RestTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Slf4j
@Service
@Getter
public class VkService {
    private final MessageMapper messageMapper;
    private final RestTransportClient rest;

    @Autowired
    public VkService(MessageMapper messageMapper, RestTransportClient restTransportClient) {
        this.messageMapper = messageMapper;
        this.rest = restTransportClient;
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
        MessageResponse message = messageMapper.toResponse(messageRequest);
        MultiValueMap<String, String> params = message.toMultiValueParams();
        log.info("Отправка сообщения с параметрами: {}", params);
        try {
            String response = rest.post("messages.send", params);
            log.info("Ответ VK: {}", response);
        } catch (Exception e) {
            log.error("Ошибка отправки сообщения в VK API", e);
        }
    }
}
