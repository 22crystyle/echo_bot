package org.echoBot.dto.request;

import java.util.List;

public record ClientInfoRequest(
        List<String> button_actions,
        boolean keyboard,
        boolean inline_keyboard,
        boolean carousel,
        int lang_id
) {
}
