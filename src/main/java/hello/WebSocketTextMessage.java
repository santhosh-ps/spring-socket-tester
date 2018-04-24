package hello;

import org.springframework.web.socket.WebSocketMessage;

public class WebSocketTextMessage implements WebSocketMessage<String> {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getPayload() {
        return this.message;
    }

    @Override
    public int getPayloadLength() {
        return this.message.length();
    }

    @Override
    public boolean isLast() {
        return false;
    }
}
