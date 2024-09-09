package com.scu927.producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chuhan
 * @date 2024/9/8
 */


@Service
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Sends reservation message to RabbitMQ queue
    public void sendReservationMessage(String message) {
        rabbitTemplate.convertAndSend("reservationQueue", message);
        System.out.println("Message sent to queue: " + message);
    }
}