package com.example.crmticket.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class config {

    public static final String GENERATED = "generated";
    public static final String RECEIVE_GENERATED = "receiveGenerated";
    public static final String INPROGRESS = "inProgress";
    public static final String RECEIVE_INPROGRESS = "receiveInProgress";

    @Autowired
    private ConnectionFactory cachingConnectionFactory;

    // Setting the annotation listeners to use the jackson2JsonMessageConverter
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter());
        return factory;
    }

    // Standardize on a single objectMapper for all message queue items
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue generated() {
        Map<String, Object> args = new HashMap<String, Object>();

        args.put("x-dead-letter-exchange", "");

        args.put("x-dead-letter-routing-key", RECEIVE_GENERATED);
        // TTL 5 seconds
        args.put("x-message-ttl", 25000);
        return new Queue(GENERATED,false, false, false, args);
    }

    @Bean
    public Queue inProgress() {
        Map<String, Object> args = new HashMap<String, Object>();

        args.put("x-dead-letter-exchange", "");

        args.put("x-dead-letter-routing-key", RECEIVE_INPROGRESS);

        args.put("x-message-ttl", 25000);
        return new Queue(INPROGRESS, false, false, false, args);
    }

    @Bean
    public RabbitTemplate generatedTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setDefaultReceiveQueue(generated().getName());
        rabbitTemplate.setRoutingKey(generated().getName());
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public RabbitTemplate inProgressTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setDefaultReceiveQueue(inProgress().getName());
        rabbitTemplate.setRoutingKey(inProgress().getName());
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue receiveGenerated() {
        return new Queue(RECEIVE_GENERATED);
    }

    @Bean
    public Queue receiveInProgress() {
        return new Queue(RECEIVE_INPROGRESS);
    }

}




//@Configuration
//public class config {
//  //  @Bean
////    DirectExchange deadLetterExchange() {
////        return new DirectExchange("deadLetterExchange");
////    }
//  @Bean
//  FanoutExchange deadLetterExchange() {
//      return new FanoutExchange("deadLetterExchange");
//  }
//
//    @Bean
//    DirectExchange exchange() {
//        return new DirectExchange("geExchange");
//    }
//
//    @Bean
//    Queue dlq() {
//        return QueueBuilder.durable("deadLetter.queue").build();
//    }
//
//    @Bean
//    Queue queue() {
//        return QueueBuilder.durable("generated.queue").withArgument("x-dead-letter-exchange", "deadLetterExchange")
//                .withArgument("x-dead-letter-routing-key", "deadLetter").build();
//    }
//
//    @Bean
//    Binding DLQbinding() {
//        return BindingBuilder.bind(dlq()).to(deadLetterExchange());//.with("deadLetter");
//    }
//
//    @Bean
//    Binding binding() {
//        return BindingBuilder.bind(queue()).to(exchange()).with("generated");
//    }
//
//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//
//    @Bean
//    Queue deadQueue() {
//        return QueueBuilder.durable("dead.queue").build();
//    }
//    @Bean
//    Queue mainQueue() {
//        return QueueBuilder.durable("mainQueue.queue").withArgument("x-dead-letter-exchange", "deadLetterExchange")
//                .withArgument("x-dead-letter-routing-key", "dead").build();
//    }
//    @Bean
//    Binding DLbinding() {
//        return BindingBuilder.bind(deadQueue()).to(deadLetterExchange());//.with("deadLetter");
//    }
//    @Bean
//    Binding bind() {
//        return BindingBuilder.bind(mainQueue()).to(exchange()).with("mainQueue");
//    }
//
//
//
//
//
//
////    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
////
////        final RabbitTemplate rabbitTemplate = new RabbitTemplate((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory);
////        rabbitTemplate.setMessageConverter(jsonMessageConverter());
////        return rabbitTemplate;
////    }
//}
//
