package org.echoBot.dto;

import org.echoBot.config.ResponseProperties;
import org.echoBot.dto.request.MessageRequest;
import org.echoBot.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Компонент для преобразования входящего запроса в формат ответа для VK API.
 * <p>
 * Класс {@code MessageMapping} принимает объект {@link MessageRequest} и преобразует его в
 * {@link MessageResponse} с использованием настроек, заданных в {@link ResponseProperties}.
 * Это позволяет инкапсулировать логику формирования параметров для отправки сообщений через VK API.
 * </p>
 *
 * @see ResponseProperties
 * @see MessageRequest
 * @see MessageResponse
 */
@Component
public class MessageMapper {
    private final ResponseProperties properties;

    @Autowired
    public MessageMapper(ResponseProperties properties) {
        this.properties = properties;
    }

    /**
     * Преобразует входящий {@link MessageRequest} в {@link MessageResponse}, добавляя к сообщению
     * определённые параметры из настроек VK API.
     * <p>
     * При этом используется текст из запроса, access token и версия API, заданные в {@link ResponseProperties}.
     * Кроме того, генерируется случайное число для параметра {@code random_id}.
     * </p>
     *
     * @param request входящий запрос, содержащий информацию о сообщении
     * @return {@code MessageResponse} с параметрами, подготовленными для отправки через VK API
     */
    public MessageResponse toResponse(MessageRequest request) {
        return MessageResponse.of(
                request.peer_id(),
                request.text(),
                properties.getToken(),
                properties.getV(),
                ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE)
        );
    }
}
