package hello;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Util {
    private static Set<String> sessionIds = new HashSet<>();

    public static void addSessionId(String id){
        sessionIds.add(id);
    }

    public static Integer getCount(){
        return sessionIds.size();
    }
    public static void remove(String val){
        sessionIds.remove(val);
    }

    public static Set<String> getSessionIds() {
        return sessionIds;
    }
}
