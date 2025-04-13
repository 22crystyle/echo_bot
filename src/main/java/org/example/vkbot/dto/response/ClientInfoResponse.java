package org.example.vkbot.dto.response;

import java.util.ArrayList;

public record ClientInfoResponse(
        ArrayList<String> button_actions,
        boolean keyboard,
        boolean inline_keyboard,
        boolean carousel,
        int lang_id
) {
}
