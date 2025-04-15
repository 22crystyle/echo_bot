package org.echoBot.dto.response;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Record, представляющий параметры сообщения для отправки через VK API.
 *
 * @param peer_id      идентификатор получателя сообщения
 * @param text         текст сообщения
 * @param access_token токен доступа для VK API
 * @param v            версия API VK
 * @param random_id    случайное число для уникальной идентификации запроса
 */
public record MessageResponse(
        int peer_id,
        String text,
        String access_token,
        String v,
        int random_id
) implements IResponseWithBody {
    public static MessageResponse of(int peer_id, String message, String access_token, String v, int random_id) {
        return new MessageResponse(peer_id, message, access_token, v, random_id);
    }

    public MultiValueMap<String, String> toMultiValueParams() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("peer_id", String.valueOf(peer_id));
        params.add("message", "Вы сказали: " + text);
        params.add("access_token", access_token);
        params.add("v", v);
        params.add("random_id", String.valueOf(random_id));
        return params;
    }

    @Override
    public String getResponseBody() {
        return "MessageResponse{" +
                "peer_id=" + peer_id +
                ", text='" + text + '\'' +
                ", access_token='" + access_token + '\'' +
                ", v='" + v + '\'' +
                ", random_id=" + random_id +
                '}';
    }

    @Override
    public String toString() {
        return getResponseBody();
    }
}