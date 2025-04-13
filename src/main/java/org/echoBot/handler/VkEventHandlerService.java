package org.echoBot.handler;

import org.echoBot.config.ResponseProperties;
import org.echoBot.dto.request.MessageRequest;
import org.echoBot.dto.request.RootMessageRequest;
import org.echoBot.service.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для обработки событий, поступающих через VK Callback API.
 * <p>
 * Данный сервис определяет тип входящего запроса и выполняет соответствующую логику:
 * возвращает confirmation-токен при получении события типа "confirmation" или отправляет сообщение при получении "message_new".
 * </p>
 *
 * @see VkService
 * @see ResponseProperties
 * @see RootMessageRequest
 */
@Service
public class VkEventHandlerService {
    private final VkService vkService;
    private final ResponseProperties responseProperties;

    @Autowired
    public VkEventHandlerService(VkService vkService, ResponseProperties responseProperties) {
        this.vkService = vkService;
        this.responseProperties = responseProperties;
    }

    /**
     * Обрабатывает входящий запрос от VK Callback API.
     * <p>
     * Если тип запроса равен "confirmation", метод возвращает confirmation-токен,
     * необходимый для подтверждения сервера VK.
     * Если тип запроса равен "message_new", сервис вызывает отправку сообщения через {@link VkService#sendMessage(MessageRequest)}
     * и возвращает строку "ok".
     * Для остальных типов запросов также возвращается "ok".
     * </p>
     *
     * @param request объект запроса от VK, содержащий тип события и сопутствующую информацию
     * @return строка ответа для VK Callback API, например, confirmation-токен или "ok"
     */
    public String handleEvent(RootMessageRequest request) {
        return switch (request.type()) {
            case "confirmation" -> responseProperties.getConfirmation();
            case "message_new" -> {
                vkService.sendMessage(request.object().message());
                yield "ok";
            }
            default -> "ok";
        };
    }

}
