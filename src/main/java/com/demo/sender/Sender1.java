package com.demo.sender;

import com.demo.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * 生产者1
 */
@Component
public class Sender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String sendMsg = "hello1 " + new Date();
        System.out.println("Sender1:" + sendMsg);
        rabbitTemplate.convertAndSend("helloQueue", sendMsg);
    }

    public void sendUser(User user){
        System.out.println("user Sender1:" + user.getUserName()+"/"+user.getPassword());
        rabbitTemplate.convertAndSend("helloQueue", user);
    }

    public void testTopPicMessage() {
        String msg = "sendTopPicMessage";
        System.out.println("sendTopPicMessage1:" + msg);
        //第一个参数:指定了exchange
        //第二个参数:指定了接受消息的栏目名
        //第三个参数:消息内容
        //到指定exchange找出第二个参数符合的正则表达式,得到对应的Queue,监听相应Queue的消费者接受到消息
        rabbitTemplate.convertAndSend("topicExchange", "topic.Message", msg);//topic.Message、topic.#两个都符合

        msg = "sendTopPicMessages";
        System.out.println("sendTopPicMessages1:" + msg);
        rabbitTemplate.convertAndSend("topicExchange", "topic.Messages", msg);//只有topic.#符合
    }

    public void testFanoutMessage(){
        String sendMsg = "sendFanoutMessage";
        System.out.println("fanout Sender1:" + sendMsg);
        //第二个参数不会进行正则表达式的过滤
        //但是必须要填,才能根据exchange找到相关Queue
        rabbitTemplate.convertAndSend("fanoutExchange","", sendMsg);
    }
}