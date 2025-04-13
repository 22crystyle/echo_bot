package org.example.vkbot.controller;

import org.example.vkbot.config.VkProperties;
import org.example.vkbot.dto.request.MessageRequest;
import org.example.vkbot.dto.request.RootMessageRequest;
import org.example.vkbot.service.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VkCallbackController {
    public final VkService vkService;
    private final VkProperties vkProperties;

    @Autowired
    public VkCallbackController(VkService vkService, VkProperties vkProperties) {
        this.vkService = vkService;
        this.vkProperties = vkProperties;
    }

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> getMessage(@RequestBody RootMessageRequest request) {
        if ("confirmation".equals(request.type())) {
            return ResponseEntity.ok(vkProperties.getConfirmation());
        }

        if ("message_new".equals(request.type())) {
            MessageRequest incomingMsg = request.object().message();
            vkService.sendMessage(incomingMsg);

            return ResponseEntity.ok("ok");
        }

        return ResponseEntity.ok("ok");
    }
}
