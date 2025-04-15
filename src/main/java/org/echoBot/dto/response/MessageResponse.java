package org.echoBot.dto.response;

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
    /**
     * Фабричный метод для создания экземпляра {@code MessageResponse}.
     *
     * @param peer_id      идентификатор получателя сообщения
     * @param message      текст сообщения
     * @param access_token токен доступа для VK API
     * @param v            версия API VK
     * @param random_id    случайное число для уникальной идентификации запроса
     * @return новый экземпляр {@code MessageResponse}
     */
    public static MessageResponse of(int peer_id, String message, String access_token, String v, int random_id) {
        return new MessageResponse(peer_id, message, access_token, v, random_id);
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
