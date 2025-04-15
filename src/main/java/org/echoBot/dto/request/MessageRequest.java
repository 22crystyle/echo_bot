package org.echoBot.dto.request;

import java.util.List;

public record MessageRequest(
        int date,
        int from_id,
        int id,
        int version,
        int out,
        List<Object> fwd_messages,
        boolean important,
        boolean is_hidden,
        List<Object> attachments,
        int conversation_message_id,
        String text,
        int peer_id,
        int random_id
) {

}
