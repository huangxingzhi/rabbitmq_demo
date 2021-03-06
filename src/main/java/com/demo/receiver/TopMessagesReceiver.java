package com.demo.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * topicMessages消费者
 */
@Component
@RabbitListener(queues = "topicMessages")
public class TopMessagesReceiver {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("topMessagesReceiver:" +msg);
    }
}