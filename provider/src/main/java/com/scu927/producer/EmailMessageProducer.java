package com.scu927.producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chuhan
 * @date 2024/9/8
 */


@Service
public class EmailMessageProducer  {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Sends reservation message to RabbitMQ queue
    // Sends email message to RabbitMQ queue
    // Sends email message to RabbitMQ queue with a specific routingKey
    public void sendEmailMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend(routingKey, message);
        System.out.println("Message sent to queue with routingKey: " + routingKey + " - " + message);
    }
}