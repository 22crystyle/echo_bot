package org.echoBot.dto.request;

public record RootMessageRequest(
        int group_id,
        String type,
        String event_id,
        String v,
        MessageObject object

) {
    @Override
    public String toString() {
        return "RootMessageRequest{" +
                "group_id=" + group_id +
                ", type='" + type + '\'' +
                ", event_id='" + event_id + '\'' +
                ", v='" + v + '\'' +
                ", object=" + object +
                '}';
    }
}
