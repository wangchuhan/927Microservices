package com.scu927.config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chuhan
 * @date 2024/9/8
 */


@Configuration
public class RabbitMQConfig {

    // Names of the main queue and the dead letter queue
    private static final String RESERVATION_QUEUE = "reservationQueue";
    private static final String DLQ = "dlq-email";  // Dead Letter Queue name
    private static final String DLX_EXCHANGE = "dlx-exchange";  // Dead Letter Exchange name

    // Define the main queue with dead letter properties
    @Bean
    public Queue reservationQueue() {
        return QueueBuilder.durable(RESERVATION_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)  // Specify the dead letter exchange
                .withArgument("x-dead-letter-routing-key", DLQ)        // Specify the routing key for the dead letter queue
                .withArgument("x-message-ttl", 60000)                  // Message Time-To-Live (TTL) in milliseconds
                .build();
    }

    // Define the dead letter queue
    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DLQ).build();
    }

    // Define the dead letter exchange
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DLX_EXCHANGE);
    }

    // Bind the dead letter queue to the dead letter exchange
    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DLQ);
    }
}