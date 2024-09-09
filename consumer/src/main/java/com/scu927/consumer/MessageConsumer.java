package com.scu927.consumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
/**
 * @author Chuhan
 * @date 2024/9/8
 */


@Service
public class MessageConsumer {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private  String mailAddress;

    // Listen to the reservationQueue and process incoming messages
    @RabbitListener(queues = "reservationQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message from queue: " + message);

        // Parse the email address and reservation information from the message
        String email = extractEmailFromMessage(message);
        String reservationInfo = extractReservationInfo(message);

        // Send email notification to the user
        sendEmailNotification(email, reservationInfo);
    }

    // Helper method to send an email notification
    private void sendEmailNotification(String toEmail, String reservationInfo) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Reservation Confirmation");
        mailMessage.setText(reservationInfo);
        mailMessage.setFrom(mailAddress);
        mailSender.send(mailMessage);
        System.out.println("Email sent to: " + toEmail);
    }

    // Helper method to extract the email address from the message
    private String extractEmailFromMessage(String message) {
        // Assuming message format: "Email: example@example.com; Reservation: ..."
        return message.split(";")[0].split(":")[1].trim();
    }

    // Helper method to extract the reservation info from the message
    private String extractReservationInfo(String message) {
        // Assuming message format: "Email: example@example.com; Reservation: ..."
        return message.split(";")[1].trim();
    }

}