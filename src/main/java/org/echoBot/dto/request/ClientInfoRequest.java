package org.echoBot.dto.request;

import java.util.ArrayList;

public record ClientInfoRequest(
        ArrayList<String> button_actions,
        boolean keyboard,
        boolean inline_keyboard,
        boolean carousel,
        int lang_id
) {
}
