package org.example.vkbot.handler;

import org.example.vkbot.config.VkProperties;
import org.example.vkbot.dto.request.RootMessageRequest;
import org.example.vkbot.service.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VkEventHandlerService {
    private final VkService vkService;
    private final VkProperties vkProperties;

    @Autowired
    public VkEventHandlerService(VkService vkService, VkProperties vkProperties) {
        this.vkService = vkService;
        this.vkProperties = vkProperties;
    }

    public String handleEvent(RootMessageRequest request) {
        return switch (request.type()) {
            case "confirmation" -> vkProperties.getConfirmation();
            case "message_new" -> {
                vkService.sendMessage(request.object().message());
                yield "ok";
            }
            default -> "ok";
        };
    }

}
