package hello;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.logging.Logger;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private static final Logger LOG = Logger.getLogger("WebSocketHandler");

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        LOG.info("error occured at sender " + session+" , message :"+throwable.getMessage());

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOG.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOG.info("Connected ... " + session.getId());
        session.sendMessage(new TextMessage(session.getId()));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage jsonTextMessage) throws Exception {
        LOG.info("message received: " + jsonTextMessage.getPayload());

        session.sendMessage(new TextMessage(jsonTextMessage.getPayload()));
    }
}