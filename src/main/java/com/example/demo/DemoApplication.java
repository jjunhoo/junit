package com.example.demo;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        System.out.println("start");
        SpringApplication.run(DemoApplication.class, args);
    }
}
@Component
class Sender {
    @Autowired
    RabbitMessagingTemplate template;
    @Value("${queueproperty}")
    String queue;
    @Value("${queueproperty2}")
    String queue2;

    // 외부와 연결되는 Queue 객체를 빈으로 등록한다.
    @Bean
    Queue queue1() {
        System.out.println("Queue1 Create !");
        return new Queue(queue, false);
    }
    // 외부와 연결되는 Queue 객체를 빈으로 등록한다.
    @Bean
    Queue queue2() {
        System.out.println("Queue2 Create !");
        return new Queue(queue2, false);
    }
    // 외부 큐에게 메시지전송
    public void send(String queue, String message) {
        template.convertAndSend(queue, message);
    }
}

// 외부 큐에서 메시지 이벤트를 수신할 Receiver 객체를 빈으로 등록
@Component
class Receiver1 {
    // 만약 외부 큐에서 이벤트가 발생하면(@RabbitListener) 해당 메소드를 수행한다
    @RabbitListener(queues = "${queueproperty}")
    public void processMessage(String content) {
        // 1번 Queue Liten
        System.out.println("[Receiver1] Listen Message : " + content);
    }
}
// 외부 큐에서 메시지 이벤트를 수신할 Receiver 객체를 빈으로 등록
@Component
class Receiver2 {
    // 만약 외부 큐에서 이벤트가 발생하면(@RabbitListener) 해당 메소드를 수행
    @RabbitListener(queues = "${queueproperty2}")
    public void processMessage(String content) {
        // 2번 Queue Liten
        System.out.println("[Receiver2] Listen Message : " + content);
    }
}