package hello;

import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Util {
    private static Set<WebSocketSession> sessions = new HashSet<>();

    public static void addSession(WebSocketSession session){
        sessions.add(session);
    }

    public static Set<WebSocketSession> getSessions() {
        return sessions;
    }

    public static void setSessions(Set<WebSocketSession> sessions) {
        Util.sessions = sessions;
    }

    public static Integer getCount(){

        return sessions.size();
    }
    public static void removeSession(WebSocketSession session){
        sessions.remove(session);
    }

}
