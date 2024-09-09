package com.scu927.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
@Configuration
public class RabbitMQConfig {

    // Define a queue for handling reservation messages
    @Bean
    public Queue reservationQueue() {
        return new Queue("reservationQueue", true);
    }

}