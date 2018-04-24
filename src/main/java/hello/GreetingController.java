package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
    ObjectMapper objectMapper = new ObjectMapper();

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(String message) throws Exception {
        return new Greeting(objectMapper.writeValueAsString(Util.getSessionIds()));
    }

}
