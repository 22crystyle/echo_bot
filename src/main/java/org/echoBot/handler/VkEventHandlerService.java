package org.echoBot.handler;

import org.echoBot.config.ResponseProperties;
import org.echoBot.dto.request.RootMessageRequest;
import org.echoBot.service.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * Determines and processes the type of request
     * @param request to handle
     * @return <p>confirmation token if {@code request.type} is {@code confirmation}</p>
     * <p>send message to sender if {@code request.type} is {@code message_new} and return "ok"</p>
     * @see VkService
     * */
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
