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

    // Listen to the "scenicSpotQueue" queue
    @RabbitListener(queues = "scenicSpotQueue")
    public void receiveScenicSpotMessage(String message) {
        processMessage(message,"ScenicSpot");
    }

    // Listen to the "tableReservationQueue" queue
    @RabbitListener(queues = "tableReservationQueue")
    public void receiveTableReservationMessage(String message) {
        processMessage(message,"tableReservation");
    }

    // Listen to the "roomBookingQueue" queue
    @RabbitListener(queues = "roomBookingQueue")
    public void receiveRoomBookingMessage(String message) {
        processMessage(message,"roomBooking");
    }

    // Listen to the "paymentReminderQueue" queue
    @RabbitListener(queues = "paymentReminderQueue")
    public void paymentReminderQueueMessage(String message) {
        processMessage(message,"paymentReminder");
    }

    // Listen to the "roomCancelReminderQueue" queue
    @RabbitListener(queues = "roomCancelReminderQueue")
    public void roomCancelReminderMessage(String message) {
        processMessage(message,"roomCancelReminder");
    }

    /**
     * Process the received message
     *
     * @param message The message content received
     */
    private void processMessage(String message,String messageType) {
        try {
            // Validate the message format
            if (isValidMessageFormat(message)) {
                // Extract email and reservation info
                String email = extractEmailFromMessage(message);
                String reservationInfo = extractReservationInfo(message);
                String subject = getEmailSubjectByMessageType(messageType);
                // Validate email format
                if (isValidEmail(email)) {
                    sendEmailNotification(email, reservationInfo,subject);
                    System.out.println("Email sent to: " + email);
                } else {
                    System.out.println("Invalid email format: " + email);
                    // Optional: log the error or take other actions
                }
            } else {
                System.out.println("Invalid message format: " + message);
                // Optional: log the error or take other actions
            }
        } catch (Exception e) {
            System.out.println("Failed to process message: " + e.getMessage());
            // Optional: log the error or take other actions
        }
    }

    /**
     * Send email notification
     *
     * @param toEmail Recipient's email
     * @param reservationInfo Reservation info
     */
    private void sendEmailNotification(String toEmail, String reservationInfo,String subjectType) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subjectType);
        mailMessage.setText(reservationInfo);
        mailMessage.setFrom(mailAddress);
        mailSender.send(mailMessage);
        System.out.println("Email sent to: " + toEmail);
    }

    /**
     * get subject from message type
     *
     * @param messageType The type of message
     * @return Email subject
     */
    private String getEmailSubjectByMessageType(String messageType) {
        switch (messageType) {
            case "scenicSpot":
                return "Scenic Spot Booking Success";
            case "tableReservation":
                return "Table Reservation Success";
            case "roomBooking":
                return "Room Booking Success - Pending Payment";
            case "paymentReminder":
                return "Payment Success";
            case "roomCancelReminder":
                return "Room Cancellation Success";
            default:
                return "Reservation Notification";
        }
    }

    /**
     * Extract the email address from the message
     *
     * @param message The message content
     * @return Email address
     */
    private static String  extractEmailFromMessage(String message) {
        try {
            // Use regex to extract the part after "Email: " until the first semicolon or newline
            Pattern emailPattern = Pattern.compile("Email:\\s*(\\S+)");
            Matcher matcher = emailPattern.matcher(message);
            if (matcher.find()) {
               String email= matcher.group(1).trim(); // Extract and return the email address
                email = email.replace(";", "");
                return email;

            } else {
                System.out.println("Email not found in message");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error extracting email from message: " + e.getMessage());
            return null;
        }
    }
    /**
     * Extract the reservation info from the message
     *
     * @param message The message content
     * @return Reservation information
     */
    /**
     * Extract the reservation info from the message
     *
     * @param message The message content
     * @return Reservation information
     */
    private static String extractReservationInfo(String message) {
        try {
            // Use regex to extract everything after "Email:"
            Pattern reservationPattern = Pattern.compile("Email:\\s*\\S+;\\s*(.*)", Pattern.DOTALL);
            Matcher matcher = reservationPattern.matcher(message);
            if (matcher.find()) {
                return matcher.group(1).trim(); // Extract and return everything after email
            } else {
                System.out.println("Reservation info not found in message");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error extracting reservation info from message: " + e.getMessage());
            return null;
        }
    }

    /**
     * Validate the email format
     *
     * @param email Email address
     * @return Whether it's a valid email format
     */
    private static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validate the message format
     *
     * @param message The message content
     * @return Whether the format is valid
     */
    private boolean isValidMessageFormat(String message) {
        // Check if the message contains the necessary fields: Email and Reservation
        return message.contains("Email:") ;
    }

    public static void main(String[] args) {
        String  s="Email: wangchuhan161@163.com;\n" +
                "\n" +
                "Dear Chuhan Wang,\n" +
                "\n" +
                "Thank you for choosing our service! We are pleased to confirm that your reservation has been successfully made. Here are the details of your reservation:\n" +
                "\n" +
                "Reservation Number: 39\n" +
                "Restaurant/Café: The Green Bistro\n" +
                "Restaurant Location: Melbourne\n" +
                "Table Number: 19\n" +
                "Table Location: Near the bar\n" +
                "Reservation Date: 2024-09-15\n" +
                "Time Slot: 08:00-10:00\n" +
                "Number of Guests: 4\n" +
                "\n" +
                "We look forward to welcoming you on 2024-09-15. If you have any questions or need further assistance, please feel free to contact us.\n" +
                "\n" +
                "Best regards,\n" +
                "The Restaurant Team";

       String email=extractEmailFromMessage(s);
        boolean validEmail = isValidEmail(email);
        String s1 = extractReservationInfo(s);
        System.out.println(email);
        System.out.println(validEmail);
        System.out.println(s1);
    }

//    @RabbitListener(queues = "scenicSpotQueue")
//    public void receiveScenicSpotMessage(String message) {
//
//
//        // Parse the email address and reservation information from the message
//        String email = extractEmailFromMessage(message);
//        String reservationInfo = extractReservationInfo(message);
//
//        if (isValidEmail(email)) {
//            // Send email notification to the user
//            sendEmailNotification(email, reservationInfo);
//            System.out.println("Email sent to: " + email);
//        } else {
//            System.out.println("Invalid email format: " + email);
//            // Optionally, log the error or take other actions
//        }
//    }
//    // Listen to the reservationQueue and process incoming messages
//    @RabbitListener(queues = "tableReservationQueue")
//    public void receiveTableReservationMessage(String message) {
//        System.out.println("Received Table Reservation Message: " + message);
//
//        // Parse the email address and reservation information from the message
//        String email = extractEmailFromMessage(message);
//        String reservationInfo = extractReservationInfo(message);
//
//        // Send email notification to the user
//        sendEmailNotification(email, reservationInfo);
//    }
//
//
//    @RabbitListener(queues = "roomBookingQueue")
//    public void receiveRoomBookingMessage(String message) {
//        System.out.println("Received message from queue: " + message);
//
//        // Parse the email address and reservation information from the message
//        String email = extractEmailFromMessage(message);
//        String reservationInfo = extractReservationInfo(message);
//
//        // Send email notification to the user
//        sendEmailNotification(email, reservationInfo);
//    }
//
//
//    @RabbitListener(queues = "paymentReminderQueue")
//    public void paymentReminderQueueMessage(String message) {
//        System.out.println("Received message from queue: " + message);
//
//        // Parse the email address and reservation information from the message
//        String email = extractEmailFromMessage(message);
//        String reservationInfo = extractReservationInfo(message);
//
//        // Send email notification to the user
//        sendEmailNotification(email, reservationInfo);
//    }
//
//    @RabbitListener(queues = "roomCancelReminderQueue")
//    public void roomCancelReminderMessage(String message) {
//        System.out.println("Received message from queue: " + message);
//
//        // Parse the email address and reservation information from the message
//        String email = extractEmailFromMessage(message);
//        String reservationInfo = extractReservationInfo(message);
//
//        // Send email notification to the user
//        sendEmailNotification(email, reservationInfo);
//    }
//
//
//
//    // Helper method to send an email notification
//    private void sendEmailNotification(String toEmail, String reservationInfo) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(toEmail);
//        mailMessage.setSubject("Reservation Confirmation");
//        mailMessage.setText(reservationInfo);
//        mailMessage.setFrom(mailAddress);
//        mailSender.send(mailMessage);
//        System.out.println("Email sent to: " + toEmail);
//    }
//
//    // Helper method to extract the email address from the message
//    private String extractEmailFromMessage(String message) {
//        // Assuming message format: "Email: example@example.com; Reservation: ..."
//        return message.split(";")[0].split(":")[1].trim();
//    }
//
//    // Helper method to extract the reservation info from the message
//    private String extractReservationInfo(String message) {
//        // Assuming message format: "Email: example@example.com; Reservation: ..."
//        return message.split(";")[1].trim();
//    }
//    // Email format validation method
//    private boolean isValidEmail(String email) {
//        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
//        Pattern pattern = Pattern.compile(emailRegex);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
}