package com.scu927.mock;

/**
 * @author Chuhan
 * @date 2024/9/25
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class RoomBookingSystemSimulation {
    private static final AtomicLong counter = new AtomicLong(1);

    // 生成唯一的 Case ID
    public static String generateUniqueCaseId() {
        return String.valueOf(counter.getAndIncrement());
    }

    // Method to get a random delay within a given range (min to max milliseconds)
    private static int getRandomDelay(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static void main(String[] args) throws InterruptedException {
        String logType = "roomBooking";  // Log type is always "tourBooking"
        int totalRequests = 100;

        int successfulRequests = 50;
        int unPaymentRequests = 20;
        int failedTokenRequests = 10;
        int failedParameterRequests = 10;
        int fullyBookedRequests = 5;
        int dbFailureRequests = 5;


        for (int i = 1; i <= totalRequests; i++) {
            String caseId = generateUniqueCaseId();
            System.out.println("generating " + i + " row of data");

            if (i <= successfulRequests) {
                processSuccess(caseId, logType);
            } else if (i <= successfulRequests + unPaymentRequests) {
                processBookingSuccessAndPaymentFailed(caseId, logType);
            } else if (i <= successfulRequests + unPaymentRequests + failedTokenRequests) {
                processFailedTokenRequest(caseId, logType);
            } else if (i <= successfulRequests + unPaymentRequests + failedTokenRequests + failedParameterRequests) {
                processFailedParameterRequest(caseId, logType);
            } else if (i <= successfulRequests + unPaymentRequests + failedTokenRequests + failedParameterRequests + fullyBookedRequests) {
                processFullyBookedRequest(caseId, logType);
            } else  {
                processDBFailureRequest(caseId, logType);
            }
        }

    }


    private static void processSuccess(String caseId, String logType) throws InterruptedException {
        // 1 Accept Request
        String startAcceptRequest = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeAcceptRequest = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startAcceptRequest, completeAcceptRequest, "Accept Request", "Request Validator", "Validator");

        // 2 Check Token Validity
        String startCheckToken = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckToken = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckToken, completeCheckToken, "Check Token Validity", "Request Validator", "Validator");

        //3  Check Request Parameters
        String startCheckParameters = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckParameters = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckParameters, completeCheckParameters, "Check Request Parameters", "Request Validator", "Validator");

        // 4 Check Table Availability
        String startCheckAvailability = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckAvailability = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckAvailability, completeCheckAvailability, "Check Room Availability", "Room Database Entry", "Database Administrator");

        // 5 Lock Table
        String startLockTable = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(180, 220)); // Simulate delay with a random range (80ms to 120ms)
        String completeLockTable = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startLockTable, completeLockTable, "Lock Room", "Room Database Entry", "Database Administrator");

        //6  Reservation Success
        String startReservationSuccess = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeReservationSuccess = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startReservationSuccess, completeReservationSuccess, "Booking Pending Payment", "Booking System", "System");

        // 7 Send Payment Reminder to MQ
        String startSendMessageMq = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeSendMessageMq = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startSendMessageMq, completeSendMessageMq, "Send Payment Reminder to MQ", "Message Queue", "Payment Reminder System");

        // 8 Processing payment
        String startProcessingPayment = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeProcessingPayment = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startProcessingPayment, completeProcessingPayment, "Processing Payment", "Payment Gateway", "Payment Processor");

        // 9 waiting user payment
        String startWaitingPayment = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(500, 1000)); // Simulate delay
        String completeWaitingPayment = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startWaitingPayment, completeWaitingPayment, "Waiting Payment", "Payment Gateway", "Payment System");

        // 10 checking user payment status
        String startCheckStatus = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay
        String completeCheckStatus = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckStatus, completeCheckStatus, "Check Payment status", "Payment Gateway", "Payment System");



        // 11 Successful payment
        String startSuccessfulPayment = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeSuccessfulPayment = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startSuccessfulPayment, completeSuccessfulPayment, "Successful Payment", "Payment Gateway", "Payment System");


        if(Math.random() <0.8){
            // 12 Send successful booking Message to MQ
            String startSuccessfulBookingSendMessageMq = Utils.getCurrentTimestamp();
            Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
            String completeSuccessfulBookingSendMessageMq = Utils.getCurrentTimestamp();
            Utils.writeLogToCSV(logType, caseId, startSuccessfulBookingSendMessageMq, completeSuccessfulBookingSendMessageMq,
                    "Send Success Booking Message to MQ", "Message Queue", "Queue Manager");

        }else{
            // RabbitMQ Service Unavailable
            String startFailSend = Utils.getCurrentTimestamp();
            Thread.sleep(getRandomDelay(240, 360)); // Simulate delay with a random range (80ms to 120ms)
            String completeFailSend = Utils.getCurrentTimestamp();
            Utils.writeLogToCSV(logType, caseId, startFailSend, completeFailSend, "RabbitMQ Service Unavailable", "Message Queue", "Queue Manager");


        }

    }

    private static void processBookingSuccessAndPaymentFailed(String caseId, String logType) throws InterruptedException {
        // 1 Accept Request
        String startAcceptRequest = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeAcceptRequest = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startAcceptRequest, completeAcceptRequest, "Accept Request", "Request Validator", "Validator");

        // 2 Check Token Validity
        String startCheckToken = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckToken = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckToken, completeCheckToken, "Check Token Validity", "Request Validator", "Validator");

        //3  Check Request Parameters
        String startCheckParameters = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckParameters = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckParameters, completeCheckParameters, "Check Request Parameters", "Request Validator", "Validator");

        // 4 Check Room Availability
        String startCheckAvailability = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckAvailability = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckAvailability, completeCheckAvailability, "Check Room Availability", "Room Database Entry", "Database Administrator");

        // 5 Lock Room
        String startLockTable = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(180, 220)); // Simulate delay with a random range (80ms to 120ms)
        String completeLockTable = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startLockTable, completeLockTable, "Lock Room", "Room Database Entry", "Database Administrator");

        //6  Booking Success
        String startReservationSuccess = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeReservationSuccess = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startReservationSuccess, completeReservationSuccess, "Booking Pending Payment", "Booking System", "System");

        // 7 Send Payment Reminder to MQ
        String startSendMessageMq = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeSendMessageMq = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startSendMessageMq, completeSendMessageMq, "Send Payment Reminder to MQ", "Message Queue", "Payment Reminder System");

        // 8 Processing payment
        String startProcessingPayment = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeProcessingPayment = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startProcessingPayment, completeProcessingPayment, "Processing Payment", "Payment Gateway", "Payment Processor");

        // 9 waiting user payment
        String startWaitingPayment = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(1010, 2000)); // Simulate delay
        String completeWaitingPayment = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startWaitingPayment, completeWaitingPayment, "Waiting Payment", "Payment Gateway", "Payment System");


        // 10 checking user payment status
        String startCheckStatus = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay
        String completeCheckStatus = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckStatus, completeCheckStatus, "Check Payment status", "Payment Gateway", "Payment System");



        if(Math.random() <0.5){
            // 10 waiting user payment
            String startPayFailed = Utils.getCurrentTimestamp();
            Thread.sleep(getRandomDelay(1010, 2000)); // Simulate delay
            String completePayFailed = Utils.getCurrentTimestamp();
            Utils.writeLogToCSV(logType, caseId, startPayFailed, completePayFailed, "Payment Failed", "Payment Gateway", "Payment System");

        }else{
            // 11 waiting user payment
            String startPayTimeOut = Utils.getCurrentTimestamp();
            Thread.sleep(getRandomDelay(1010, 2000)); // Simulate delay
            String completePayTimeOut = Utils.getCurrentTimestamp();
            Utils.writeLogToCSV(logType, caseId, startPayTimeOut, completePayTimeOut, "Payment Timeout", "Payment Gateway", "Payment System");



        }

            // Send cancel booking Message to MQ
            String startSuccessfulBookingSendMessageMq = Utils.getCurrentTimestamp();
            Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
            String completeSuccessfulBookingSendMessageMq = Utils.getCurrentTimestamp();
            Utils.writeLogToCSV(logType, caseId, startSuccessfulBookingSendMessageMq, completeSuccessfulBookingSendMessageMq,
                    "Send Cancel Booking Message to MQ", "Message Queue", "Queue Manager");






    }


    private static void processFailedTokenRequest(String caseId, String logType) throws InterruptedException {
        // Accept Request
        String startAcceptRequest = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeAcceptRequest = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startAcceptRequest, completeAcceptRequest, "Accept Request", "Request Validator", "Validator");

        // Check Token Validity
        String startCheckToken = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckToken = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckToken, completeCheckToken, "Check Token Validity", "Request Validator", "Validator");

        // Return Invalid Token
        String startReturnToken = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeReturnToken = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startReturnToken, completeReturnToken, "Return Invalid Token", "Token Validator", "Validator");
    }

    private static void processFailedParameterRequest(String caseId, String logType) throws InterruptedException {
        // Accept Request
        String startAcceptRequest = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeAcceptRequest = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startAcceptRequest, completeAcceptRequest, "Accept Request", "Request Validator", "Validator");

        // Check Token Validity
        String startCheckToken = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckToken = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckToken, completeCheckToken, "Check Token Validity", "Request Validator", "Validator");

        // Check Request Parameters
        String startCheckParameters = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckParameters = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckParameters, completeCheckParameters, "Check Request Parameters", "Request Validator", "Validator");

        // Return Scenic Spot Error
        String startReturnError = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeReturnError = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startReturnError, completeReturnError, "Return Room Type Error", "Request Validator", "Validator");
    }

    private static void processFullyBookedRequest(String caseId, String logType) throws InterruptedException {
        // Accept Request
        String startAcceptRequest = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeAcceptRequest = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startAcceptRequest, completeAcceptRequest, "Accept Request", "Request Validator", "Validator");

        // Check Token Validity
        String startCheckToken = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckToken = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckToken, completeCheckToken, "Check Token Validity", "Request Validator", "Validator");

        // Check Request Parameters
        String startCheckParameters = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckParameters = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckParameters, completeCheckParameters, "Check Request Parameters", "Request Validator", "Validator");

        // Check Table Availability
        String startCheckAvailability = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(160, 230)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckAvailability = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckAvailability, completeCheckAvailability, "Check Room Availability", "Room Database Entry", "Database Administrator");

        // Return Fully Booked
        String startReturnFullyBooked = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeReturnFullyBooked = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startReturnFullyBooked, completeReturnFullyBooked, "Return Other Date and Room Type", "Booking System", "Database Administrator");
    }

    private static void processDBFailureRequest(String caseId, String logType) throws InterruptedException {
        // Accept Request
        String startAcceptRequest = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeAcceptRequest = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startAcceptRequest, completeAcceptRequest, "Accept Request", "Request Validator", "Validator");

        // Check Token Validity
        String startCheckToken = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckToken = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckToken, completeCheckToken, "Check Token Validity", "Request Validator", "Validator");

        // Check Request Parameters
        String startCheckParameters = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckParameters = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckParameters, completeCheckParameters, "Check Request Parameters", "Request Validator", "Validator");

        // Check Table Availability
        String startCheckAvailability = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckAvailability = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckAvailability, completeCheckAvailability, "Check Room Availability", "Room Database Entry", "Database Administrator");

        // Check Table Availability
        String startCheckAvailability1 = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(300, 400)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckAvailability1 = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckAvailability1, completeCheckAvailability1, "Database Connection Failed", "Database Connection", "Database Administrator");
    }



    // 获取当前时间戳
    public static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.now().format(formatter);
    }
}
