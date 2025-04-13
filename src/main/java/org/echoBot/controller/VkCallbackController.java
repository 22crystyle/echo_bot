package org.echoBot.controller;

import org.echoBot.dto.request.RootMessageRequest;
import org.echoBot.handler.VkEventHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VkCallbackController {
    private final VkEventHandlerService eventHandlerService;

    @Autowired
    public VkCallbackController(VkEventHandlerService eventHandlerService) {
        this.eventHandlerService = eventHandlerService;
    }

    /**
     * Handle post request from VK Callback API
     *
     * @param request VK Callback API request
     * @return each bot should return "ok" status code
     * @see VkEventHandlerService
     */
    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> getMessage(@RequestBody RootMessageRequest request) {
        String response = eventHandlerService.handleEvent(request);
        return ResponseEntity.ok(response);
    }
}
