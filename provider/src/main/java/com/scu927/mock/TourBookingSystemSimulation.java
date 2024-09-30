package com.scu927.mock;

/**
 * @author Chuhan
 * @date 2024/9/25
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class TourBookingSystemSimulation {
    private static final AtomicLong counter = new AtomicLong(1);

    // Generate unique Case ID
    public static String generateUniqueCaseId() {
        return String.valueOf(counter.getAndIncrement());
    }

    // Method to get a random delay within a given range (min to max milliseconds)
    private static int getRandomDelay(int min, int max) {
        Random random=new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static void main(String[] args) throws InterruptedException {
        String logType = "tourBooking";  // Log type is always "tourBooking"
        int totalRequests = 100;

        int successfulRequests = 50;
        int failedTokenRequests = 10;
        int failedParameterRequests = 10;
        int fullyBookedRequests = 10;
        int dbFailureRequests = 10;
        int mqFailureRequests = 10;


        for (int i = 1; i <= totalRequests; i++) {
            String caseId = generateUniqueCaseId();
            System.out.println("generating "+i+" row of data");

            if (i <= successfulRequests) {
                processSuccess(caseId, logType);
            } else if (i <= successfulRequests + failedTokenRequests) {
                processFailedTokenRequest(caseId, logType);
            } else if (i <= successfulRequests + failedTokenRequests + failedParameterRequests) {
                processFailedParameterRequest(caseId, logType);
            } else if (i <= successfulRequests + failedTokenRequests + failedParameterRequests + fullyBookedRequests) {
                processFullyBookedRequest(caseId, logType);
            } else if (i <= successfulRequests + failedTokenRequests + failedParameterRequests + fullyBookedRequests + dbFailureRequests) {
                processDBFailureRequest(caseId, logType);
            } else {
                processMQFailureRequest(caseId, logType);
            }
        }

    }


    private static void processSuccess(String caseId, String logType) throws InterruptedException {
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
        Utils.writeLogToCSV(logType, caseId, startCheckAvailability, completeCheckAvailability, "Check Scenic Spot Availability", "Scenic Spot Database Entry", "Database Administrator");

        // Lock Table
        String startLockTable = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(180, 220)); // Simulate delay with a random range (80ms to 120ms)
        String completeLockTable = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startLockTable, completeLockTable, "Lock Scenic Spot", "Scenic Spot Database Entry", "Database Administrator");

        // Reservation Success
        String startReservationSuccess = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeReservationSuccess = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startReservationSuccess, completeReservationSuccess, "Booking Success", "Booking System", "System");

        // Send Message to MQ
        String startSendMessageMq = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeSendMessageMq = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startSendMessageMq, completeSendMessageMq, "Send Message to MQ", "Message Queue", "Queue Manager");
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
        Utils.writeLogToCSV(logType, caseId, startReturnError, completeReturnError, "Return Scenic Spot Error", "Request Validator", "Validator");
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
        Utils.writeLogToCSV(logType, caseId, startCheckAvailability, completeCheckAvailability, "Check Scenic Spot Availability", "Scenic Spot Database Entry", "Database Administrator");

        // Return Fully Booked
        String startReturnFullyBooked = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeReturnFullyBooked = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startReturnFullyBooked, completeReturnFullyBooked, "Return Fully Booked", "Booking System", "Database Administrator");
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
        Utils.writeLogToCSV(logType, caseId, startCheckAvailability, completeCheckAvailability, "Check Scenic Spot Availability", "Scenic Spot Database Entry", "Database Administrator");

        // Check Table Availability
        String startCheckAvailability1 = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(800, 1600)); // Simulate delay with a random range (80ms to 120ms)
        String completeCheckAvailability1 = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startCheckAvailability1, completeCheckAvailability1, "Database Connection Failed", "Database Connection", "Database Administrator");
    }

    private static void processMQFailureRequest(String caseId, String logType) throws InterruptedException {
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
        Utils.writeLogToCSV(logType, caseId, startCheckAvailability, completeCheckAvailability, "Check Scenic Spot Availability", "Scenic Spot Database Entry", "Database Administrator");

        // Lock Table
        String startLockTable = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeLockTable = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startLockTable, completeLockTable, "Lock Scenic Spot", "Scenic Spot Database Entry", "Database Administrator");

        // Reservation Success
        String startReservationSuccess = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(80, 120)); // Simulate delay with a random range (80ms to 120ms)
        String completeReservationSuccess = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startReservationSuccess, completeReservationSuccess, "Booking Success", "Booking System", "System");

        // RabbitMQ Service Unavailable
        String startSendMessageMq = Utils.getCurrentTimestamp();
        Thread.sleep(getRandomDelay(240, 360)); // Simulate delay with a random range (80ms to 120ms)
        String completeSendMessageMq = Utils.getCurrentTimestamp();
        Utils.writeLogToCSV(logType, caseId, startSendMessageMq, completeSendMessageMq, "RabbitMQ Service Unavailable", "Message Queue", "Queue Manager");
    }

    // Get current Timestamp
    public static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.now().format(formatter);
    }
}
