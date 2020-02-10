package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MessageController {
    @Autowired
    Sender sender;
    @Value("${queueproperty}")
    String queue;
    @Value("${queueproperty2}")
    String queue2;

    @RequestMapping("/send")
    public void sendMessage(HttpServletResponse response) throws Exception {
        // Queue1으로 Send
        //sender.send(queue,"Queue1 From Controller To Receiver");
        // Queue2으로 Send
        sender.send(queue2,"Queue2 From Controller To Receiver");
        response.getWriter().print("Send Message Call Controller Init");
    }
}
