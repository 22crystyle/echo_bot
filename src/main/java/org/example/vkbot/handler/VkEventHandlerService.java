package org.example.vkbot.handler;

import org.example.vkbot.dto.request.RootMessageRequest;
import org.example.vkbot.service.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VkEventHandlerService {
    private final VkService vkService;

    @Autowired
    public VkEventHandlerService(VkService vkService) {
        this.vkService = vkService;
    }

    public String handleEvent(RootMessageRequest request) {
        return switch (request.type()) {
            case "confirmation" -> vkService.getConfirmationToken();
            case "message_new" -> {
                vkService.sendMessage(request.object().message());
                yield "ok";
            }
            default -> "ok";
        };
    }

}
