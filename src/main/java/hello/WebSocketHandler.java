package hello;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private static final Logger LOG = Logger.getLogger("WebSocketHandler");
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = mapper.createObjectNode();



    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        LOG.info("error occured at sender " + session+" , message :"+throwable.getMessage());

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();
        Util.removeSession(session);
        LOG.info(String.format("Session %s closed", sessionId));
        TextMessage msg = new TextMessage(getJsonString("dis",sessionId,Util.getCount()));
        Util.getSessions().forEach(session1 -> {
            try {
                session1.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOG.info("Connected ... " + session.getId());
        String sessionId = session.getId();
        Util.addSession(session);
        TextMessage msg = new TextMessage(getJsonString("con",sessionId,Util.getCount()));
        Util.getSessions().forEach(session1 -> {
            try {
                session1.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage jsonTextMessage) throws Exception {
        LOG.info("message received: " + jsonTextMessage.getPayload());
    }

    private String getJsonString(String type,String msg,Integer count) throws JsonProcessingException {
        ((ObjectNode) jsonNode).put("type",type);
        ((ObjectNode) jsonNode).put("session",msg);
        ((ObjectNode) jsonNode).put("count",count);
        return mapper.writeValueAsString(jsonNode);
    }
}