package com.ldy.shch91;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;



@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
@ImportResource(locations={"classpath:spring/spring.xml"})
public class Shch91Application {

    private static final String SS="test";

    public static void main(String[] args) {
        SpringApplication.run(Shch91Application.class, args);
    }

    @Bean
    @Qualifier(value = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //===============以下是验证Direct Exchange的队列==========
    @Bean
    @Qualifier(value = "hello")
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    @Qualifier(value="user")
    public Queue userQueue() {
        return new Queue("user");
    }

    //===============以下是验证topic Exchange的队列==========
    @Bean
    @Qualifier(value="topic.A")
    public Queue topicMessageA() {
        return new Queue("topic.message.A");
    }

    @Bean
    @Qualifier(value="topic.B")
    public Queue topicMessageB() {
        return new Queue("topic.message.B");
    }



    //===============以下是验证Fanout Exchange的队列==========
    @Bean
    @Qualifier(value="fanout.A")
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    @Qualifier(value="fanout.B")
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    @Qualifier(value="fanout.C")
    public Queue CMessage() {
        return new Queue("fanout.C");
    }

    //===============以上是验证Fanout Exchange的队列==========

    @Bean
    @Qualifier(value = "topicExchange")
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    @Qualifier(value = "fanoutExchange")
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 将队列topic.message.A与exchange绑定，binding_key为topic.message.A,就是完全匹配
     * @param topicMessageA
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue topicMessageA, TopicExchange exchange) {
        return BindingBuilder.bind(topicMessageA).to(exchange).with("topic.message.A");
    }

    /**
     * 将队列topic.message.B与exchange绑定，binding_key为topic.#,模糊匹配
     * @param topicMessageB
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue topicMessageB, TopicExchange exchange) {
        return BindingBuilder.bind(topicMessageB).to(exchange).with("topic.message.*");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }


}
