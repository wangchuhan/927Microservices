package com.scu927.mock;

/**
 * @author Chuhan
 * @date 2024/9/24
 */
import java.util.Random;

public class ReservationSystemSimulation {

    public static void main(String[] args) {
        int totalRequests = 100;
        int successRequests = 50;
        int failedTokenRequests = 10;       // failed type1 : token is not effectiveness
        int failedParameterRequests = 10;   // failed type2 : request is not effectiveness
        int timeSlotFailedRequests = 10;    // failed type3 : time slot is full
        int dbConnectionFailedRequests = 10;// failed type4 : database disconnected
        int mqFailedRequests =10;           // failed type5 : MQ server is unavailable

        Random random = new Random();

        for (int i = 1; i <= totalRequests; i++) {
            // Generate unique case ID
            String caseId = Utils.generateUniqueCaseId();

            // Process each request based on the count and categorize them
            processRequest(caseId, i, random, successRequests, failedTokenRequests, failedParameterRequests, timeSlotFailedRequests, dbConnectionFailedRequests, mqFailedRequests);
            System.out.println("successful generate "+i+" a row of data ");
        }
    }

    /**
     * Processes a request and logs the necessary information to CSV.
     */
    private static void processRequest(String caseId, int requestId, Random random, int successRequests, int failedTokenRequests, int failedParameterRequests, int timeSlotFailedRequests, int dbConnectionFailedRequests, int mqFailedRequests) {
        try {
            // Step 1: Accept request (Simulate with 0.1 seconds)
            String startAcceptRequest = getCurrentTimestamp();
            Thread.sleep(100); // Simulate delay
            String completeAcceptRequest = getCurrentTimestamp();
            Utils.writeLogToCSV("tableReservation",caseId, startAcceptRequest, completeAcceptRequest, "Accept Request", "Request Validator", "Customer");

            // Step 2: Check token (Simulate with 0.2 seconds)
            String startCheckToken = getCurrentTimestamp();
            Thread.sleep(200); // Simulate delay
            String completeCheckToken = getCurrentTimestamp();
            Utils.writeLogToCSV("tableReservation",caseId, startCheckToken, completeCheckToken, "Check Token Validity", "Token Validator", "System");

            // Determine the type of response based on requestId
            if (requestId <= successRequests) {
                // Success path
                processSuccessFlow(caseId);
            } else if (requestId <= successRequests + failedTokenRequests) {
                // Failed type 1: Invalid Token
                processFailedTokenFlow(caseId);
            } else if (requestId <= successRequests + failedTokenRequests + failedParameterRequests) {
                // Failed type 2: Invalid parameters
                processFailedParameterFlow(caseId, random);
            } else if (requestId <= successRequests + failedTokenRequests + failedParameterRequests + timeSlotFailedRequests) {
                // Failed type 3: Return alternative time slots
                processTimeSlotFailedFlow(caseId);
            } else if (requestId <= successRequests + failedTokenRequests + failedParameterRequests + timeSlotFailedRequests + dbConnectionFailedRequests) {
                // Failed type 4: Database connect failed
                processDbFailedAfterTableCheckFlow(caseId);
            } else {
                // Failed type 5: RabbitMQ unavailable
                processMqFailedFlow(caseId);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Success flow: Full successful process from request acceptance to RabbitMQ.
     */
    private static void processSuccessFlow(String caseId) throws InterruptedException {
        // Step 3: Check request parameters
        String startCheckRequest = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeCheckRequest = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startCheckRequest, completeCheckRequest, "Check Request Parameters", "Request Validator", "Validator");

        // Step 4: Check table availability
        String startCheckTableAvailability = getCurrentTimestamp();
        Thread.sleep(200); // Simulate delay
        String completeCheckTableAvailability = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startCheckTableAvailability, completeCheckTableAvailability, "Check Table Availability", "Table Manager", "System");

        // Step 5: Lock table
        String startLockTable = getCurrentTimestamp();
        Thread.sleep(200); // Simulate delay
        String completeLockTable = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startLockTable, completeLockTable, "Lock Table", "Table Manager", "System");

        // Step 6: Reservation successful
        String startReservationSuccess = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeReservationSuccess = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startReservationSuccess, completeReservationSuccess, "Reservation Success", "Reservation System", "Customer");

        // Step 7: Send message to RabbitMQ
        String startSendMessage = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeSendMessage = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startSendMessage, completeSendMessage, "Send Message to MQ", "Message Queue", "System");
    }

    /**
     * Failed flow 1: Invalid token.
     */
    private static void processFailedTokenFlow(String caseId) throws InterruptedException {
        // Return invalid token response
        String startReturnInvalidToken = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeReturnInvalidToken = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startReturnInvalidToken, completeReturnInvalidToken, "Return Invalid Token", "Token Validator", "System");
    }

    /**
     * Failed flow 2: Invalid parameters (random errors for different invalid parameters).
     */
    private static void processFailedParameterFlow(String caseId, Random random) throws InterruptedException {
        // Step 3: Check request parameters
        String startCheckRequest = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeCheckRequest = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startCheckRequest, completeCheckRequest, "Check Request Parameters", "Request Validator", "Validator");

        // Step 4: Detect parameter errors (randomly select the error type)
        int errorType = random.nextInt(3);
        switch (errorType) {
            case 0:
                // Invalid time slot
                String startInvalidTimeSlot = getCurrentTimestamp();
                Thread.sleep(100); // Simulate delay
                String completeInvalidTimeSlot = getCurrentTimestamp();
                Utils.writeLogToCSV("tableReservation",caseId, startInvalidTimeSlot, completeInvalidTimeSlot, "Return Invalid Time Slot", "Time Slot Validator", "Validator");
                break;
            case 1:
                // Invalid date format
                String startInvalidDateFormat = getCurrentTimestamp();
                Thread.sleep(100); // Simulate delay
                String completeInvalidDateFormat = getCurrentTimestamp();
                Utils.writeLogToCSV("tableReservation",caseId, startInvalidDateFormat, completeInvalidDateFormat, "Return Invalid Date Format", "Date Validator", "Validator");
                break;
            case 2:
                // Invalid quantity
                String startInvalidQuantity = getCurrentTimestamp();
                Thread.sleep(100); // Simulate delay
                String completeInvalidQuantity = getCurrentTimestamp();
                Utils.writeLogToCSV("tableReservation",caseId, startInvalidQuantity, completeInvalidQuantity, "Return Invalid Quantity", "Quantity Validator", "Validator");
                break;
        }
    }

    /**
     * Failed flow 3: Return alternative time slots.
     */
    private static void processTimeSlotFailedFlow(String caseId) throws InterruptedException {
        // Step 3: Check request parameters
        String startCheckRequest = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeCheckRequest = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startCheckRequest, completeCheckRequest, "Check Request Parameters", "Request Validator", "Validator");

        // Step 4: Check table availability
        String startCheckTableAvailability = getCurrentTimestamp();
        Thread.sleep(200); // Simulate delay
        String completeCheckTableAvailability = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startCheckTableAvailability, completeCheckTableAvailability, "Check Table Availability", "Table Manager", "System");

        // Step 5: Return alternative time slots
        String startReturnTimeSlots = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeReturnTimeSlots = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startReturnTimeSlots, completeReturnTimeSlots, "Return Alternative Time Slots", "Time Slot Manager", "System");
    }

    /**
     * Failed flow 4: Database connection failed after checking table availability.
     */
    private static void processDbFailedAfterTableCheckFlow(String caseId) throws InterruptedException {
        // Step 3: Check request parameters
        String startCheckRequest = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeCheckRequest = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startCheckRequest, completeCheckRequest, "Check Request Parameters", "Request Validator", "Validator");

        // Step 4: Check table availability
        String startCheckTableAvailability = getCurrentTimestamp();
        Thread.sleep(200); // Simulate delay
        String completeCheckTableAvailability = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startCheckTableAvailability, completeCheckTableAvailability, "Check Table Availability", "Table Manager", "System");

        // Step 5: Database connection failed
        String startDbConnectionFailed = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeDbConnectionFailed = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startDbConnectionFailed, completeDbConnectionFailed, "Database Connection Failed", "Database", "System");
    }

    /**
     * Failed flow 5: RabbitMQ service unavailable.
     */
    private static void processMqFailedFlow(String caseId) throws InterruptedException {
        // Step 3: Check request parameters
        String startCheckRequest = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeCheckRequest = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startCheckRequest, completeCheckRequest, "Check Request Parameters", "Request Validator", "Validator");

        // Step 4: Check table availability
        String startCheckTableAvailability = getCurrentTimestamp();
        Thread.sleep(200); // Simulate delay
        String completeCheckTableAvailability = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startCheckTableAvailability, completeCheckTableAvailability, "Check Table Availability", "Table Manager", "System");

        // Step 5: Lock table
        String startLockTable = getCurrentTimestamp();
        Thread.sleep(200); // Simulate delay
        String completeLockTable = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startLockTable, completeLockTable, "Lock Table", "Table Manager", "System");

        // Step 6: Reservation successful
        String startReservationSuccess = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeReservationSuccess = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startReservationSuccess, completeReservationSuccess, "Reservation Success", "Reservation System", "Customer");

        // Step 7: RabbitMQ unavailable
        String startMqUnavailable = getCurrentTimestamp();
        Thread.sleep(100); // Simulate delay
        String completeMqUnavailable = getCurrentTimestamp();
        Utils.writeLogToCSV("tableReservation",caseId, startMqUnavailable, completeMqUnavailable, "RabbitMQ Service Unavailable", "Message Queue", "System");
    }

    /**
     * Simulates getting the current timestamp as a formatted string.
     */
    private static String getCurrentTimestamp() {
        return Utils.getCurrentTimestamp();
    }
}
