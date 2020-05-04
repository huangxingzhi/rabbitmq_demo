package com;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitmqDemoApplication {
    /***************************************创建一个持久性的队列,当前rabbitmq不存在的话,就会进行创建***********************************************/
    @Bean
    public Queue helloQueue() {
        return new Queue("helloQueue");
    }

    @Bean
    public Queue topicMessage() {
        return new Queue("topicMessage");
    }

    @Bean
    public Queue topicMessages() {
        return new Queue("topicMessages");
    }

    @Bean
    public Queue fanoutA() {
        return new Queue("fanoutA");
    }

    @Bean
    public Queue fanoutB() {
        return new Queue("fanoutB");
    }

    @Bean
    public Queue fanoutC() {
        return new Queue("fanoutC");
    }
    /***************************************创建一个exchange,当前rabbitmq不存在的话,就会进行创建***********************************************/
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /***************************************将队列和exchange绑定***********************************************/

    /**
     * 将队列topicMessage与topicExchange绑定，
     * 只有栏目名为topic.Message才能匹配，
     * 得到当前的Queue
     * @param topicMessage
     * @param topicExchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue topicMessage, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicMessage).to(topicExchange).with("topic.Message");
    }

    /**
     * 将队列topicMessages与topicExchange绑定，
     * 以topic开头的栏目名均会模糊匹配,
     * 得到当前的Queue
     * @param topicMessages
     * @param topicExchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue topicMessages, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicMessages).to(topicExchange).with("topic.#");
    }

    /**
     * 将队列fanoutA与fanoutExchange绑定
     *
     * @param fanoutA
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeA(Queue fanoutA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutA).to(fanoutExchange);
    }

    /**
     * 将队列fanoutA与fanoutExchange绑定
     *
     * @param fanoutB
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeB(Queue fanoutB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutB).to(fanoutExchange);
    }

    /**
     * 将队列fanoutA与fanoutExchange绑定
     *
     * @param fanoutC
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeC(Queue fanoutC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutC).to(fanoutExchange);
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemoApplication.class, args);
    }
}
