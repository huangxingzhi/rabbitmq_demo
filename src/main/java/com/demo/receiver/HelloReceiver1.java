package com.demo.receiver;

import com.demo.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * helloQueue消费者1
 */
@Component
@RabbitListener(queues = "helloQueue")
public class HelloReceiver1 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1:" + hello);
    }

    @RabbitHandler
    public void processUser(User user) {
        System.out.println("user receive1:" + user.getUserName()+"/"+user.getPassword());
    }
}