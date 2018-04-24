package hello;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.logging.Logger;

public class StompDisconnectEvent implements ApplicationListener<SessionDisconnectEvent> {

    private static final Logger LOG = Logger.getLogger("StompDisconnectEvent");
    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        LOG.info("Dis event [sessionId: " + sha.getSessionId());
    }
}