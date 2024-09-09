package com.scu927.consumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @RabbitListener(queues = "scenicSpotQueue")
    public void receiveScenicSpotMessage(String message) {


        // Parse the email address and reservation information from the message
        String email = extractEmailFromMessage(message);
        String reservationInfo = extractReservationInfo(message);

        if (isValidEmail(email)) {
            // Send email notification to the user
            sendEmailNotification(email, reservationInfo);
            System.out.println("Email sent to: " + email);
        } else {
            System.out.println("Invalid email format: " + email);
            // Optionally, log the error or take other actions
        }
    }
    // Listen to the reservationQueue and process incoming messages
    @RabbitListener(queues = "tableReservationQueue")
    public void receiveTableReservationMessage(String message) {
        System.out.println("Received Table Reservation Message: " + message);

        // Parse the email address and reservation information from the message
        String email = extractEmailFromMessage(message);
        String reservationInfo = extractReservationInfo(message);

        // Send email notification to the user
        sendEmailNotification(email, reservationInfo);
    }


    @RabbitListener(queues = "roomBookingQueue")
    public void receiveRoomBookingMessage(String message) {
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
    // Email format validation method
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}