package com.fastgo.authentication.fatsgo_authentication.configuration;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

  
    @Value("${rabbitmq.exchange.sync:sync-exchange}")
    private String syncExchange;

   
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    
    @Bean
    public DirectExchange syncExchange() {
        return new DirectExchange(syncExchange);
    }
}