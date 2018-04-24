package hello;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import java.util.logging.Logger;

public class PresenceInt extends ChannelInterceptorAdapter {


    private static final Logger LOG = Logger.getLogger("StompDisconnectEvent");
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {

        StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);

        // ignore non-STOMP messages like heartbeat messages
        if(sha.getCommand() == null) {
            return;
        }

        String sessionId = sha.getSessionId();

        switch(sha.getCommand()) {
            case CONNECT:
                Util.addSessionId(sessionId);
                LOG.info("Socket connected:"+sessionId+", count :"+Util.getCount());
                break;
            case CONNECTED:
                Util.addSessionId(sessionId);
                LOG.info("Socket connected:"+sessionId+", count :"+Util.getCount());
                break;
            case DISCONNECT:
                Util.remove(sessionId);
                LOG.info("Socket disconnected:"+sessionId+", count :"+Util.getCount());
                break;
            default:
                break;

        }
    }
}
