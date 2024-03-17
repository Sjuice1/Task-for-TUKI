package com.correnet.techtask.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue queue(){
        return new Queue("imageToTagsQueue");
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("imageToTagsExchange");
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(directExchange())
                .with("imageToTagsRK");
    }
}
