package com.scu927.mock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @author Chuhan
 * @date 2024/9/23
 */
public class Utils {

    private static final String CSV_FILE_PATH = "reservation_log.csv"; // CSV 文件路径


    private static final AtomicLong counter = new AtomicLong(1);
    // 生成唯一的 Case ID
    public static String generateUniqueCaseId() {
        return String.valueOf(counter.getAndIncrement());
    }
    // 获取当前时间戳
    public static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.now().format(formatter);
    }




//    public static void writeLogToCSV(String caseId,
//                               String startTimestamp,
//                               String completeTimestamp,
//                               String reservationAction,
//                               String customerName,
//                               String role) {
//
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
//            String logEntry = String.format("%s,%s,%s,%s,%s,%s", caseId, startTimestamp, completeTimestamp, reservationAction, customerName, role);
//            writer.write(logEntry);
//            writer.newLine();  // 换行
//        } catch (IOException e) {
//            System.out.println("Failed to write log to CSV");
//        }
//
//
//    }
// 将日志写入CSV文件，检查文件是否存在，若不存在则先写入标题行
public static  void writeLogToCSV(String caseId,
                                  String startTimestamp,
                                  String completeTimestamp,
                                  String activity, String resource,
                                  String role) {
    File file = new File(CSV_FILE_PATH);
    boolean isNewFile = !file.exists();  // 检查文件是否存在

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
        // 如果文件是新创建的，先写入标题行
        if (isNewFile) {
            writer.write("Case ID,Start Times,Complete Timestamp,Activity,Resource,Role");
            writer.newLine();
        }

        // 写入日志数据
     //   String logEntry = String.format("%s,%s,%s,%s,%s,%s", caseId, startTimestamp, completeTimestamp, activity, resource, role);
        String logEntry = String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", caseId, startTimestamp, completeTimestamp, activity, resource, role);
        writer.write(logEntry);
        writer.newLine();  // 换行
    } catch (IOException e) {
        System.out.println("Failed to write log to CSV");
    }
}

    private static final String[] firstNames = {
            "John", "Emma", "Oliver", "Sophia", "Liam", "Mia", "Noah", "Ava",
            "William", "Isabella", "James", "Charlotte", "Benjamin", "Amelia",
            "Lucas", "Evelyn", "Henry", "Abigail"
    };

    private static final String[] lastNames = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller",
            "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez",
            "Wilson", "Anderson", "Thomas", "Taylor", "Moore"
    };

    public static String generateRandomName() {
        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }



    public static void main(String[] args) {
        int totalRequests = 500;
        int successRequests = 200;
        int userInputErrors = 150;
        int timeSlotReturned = 125;
        int systemErrors = 25;

        Random random = new Random();

        for (int i = 1; i <= totalRequests; i++) {
            // Generate unique case ID
            String caseId = Utils.generateUniqueCaseId();

            // Start processing request
            processRequest(caseId, i, random, successRequests, userInputErrors, timeSlotReturned, systemErrors);
            System.out.println("success write "+i);
        }
    }

    /**
     * Processes a request and logs the necessary information to CSV.
     */
    private static void processRequest(String caseId, int requestId, Random random, int successRequests, int userInputErrors, int timeSlotReturned, int systemErrors) {
        try {
            // Step 1: Check request parameters (Simulate with 0.5 seconds)
            String startCheckRequest = getCurrentTimestamp();
            Thread.sleep(100); // Simulate delay
            String completeCheckRequest = getCurrentTimestamp();
            Utils.writeLogToCSV(caseId, startCheckRequest, completeCheckRequest, "Check Request Parameters", "Request Validator", "Customer");

            if (requestId <= successRequests) {
                // Step 2: Fetch parameters (Simulate with 0.2 seconds)
                String startFetchParams = getCurrentTimestamp();
                Thread.sleep(100); // Simulate delay
                String completeFetchParams = getCurrentTimestamp();
                Utils.writeLogToCSV(caseId, startFetchParams, completeFetchParams, "Fetch Parameters", "Request Processor", "System");

                // Step 3: Query database (Simulate with 0.6 seconds)
                String startQueryDatabase = getCurrentTimestamp();
                Thread.sleep(180); // Simulate delay
                String completeQueryDatabase = getCurrentTimestamp();
                Utils.writeLogToCSV(caseId, startQueryDatabase, completeQueryDatabase, "Query Database", "Database", "System");

                // Step 4: Send message to MQ (Simulate with 0.3 seconds)
                String startSendMessage = getCurrentTimestamp();
                Thread.sleep(100); // Simulate delay
                String completeSendMessage = getCurrentTimestamp();
                Utils.writeLogToCSV(caseId, startSendMessage, completeSendMessage, "Send Message to MQ", "Message Queue", "System");

            } else if (requestId <= successRequests + userInputErrors) {
                // Simulate different input errors
                int errorType = random.nextInt(3);
                switch (errorType) {
                    case 0:
                        String startCheckError1 = getCurrentTimestamp();
                        Thread.sleep(100); // Simulate delay
                        String completeCheckError1 = getCurrentTimestamp();
                        Utils.writeLogToCSV(caseId, startCheckError1, completeCheckError1, "User Input Error - Missing username or email", "User Validation", "Customer");
                        break;
                    case 1:
                        String startCheckError2 = getCurrentTimestamp();
                        Thread.sleep(100); // Simulate delay
                        String completeCheckError2 = getCurrentTimestamp();
                        Utils.writeLogToCSV(caseId, startCheckError2, completeCheckError2, "User Input Error - Invalid time slot format", "Time Slot Validation", "Customer");
                        break;
                    case 2:
                        String startCheckError3 = getCurrentTimestamp();
                        Thread.sleep(100); // Simulate delay
                        String completeCheckError3 = getCurrentTimestamp();
                        Utils.writeLogToCSV(caseId, startCheckError3, completeCheckError3, "User Input Error - Missing reservation parameters", "Reservation Parameter Validation", "Customer");
                        break;
                }
            } else if (requestId <= successRequests + userInputErrors + timeSlotReturned) {
                // Return alternative time slots
                String startReturnTimeSlots = getCurrentTimestamp();
                Thread.sleep(100); // Simulate delay
                String completeReturnTimeSlots = getCurrentTimestamp();
                Utils.writeLogToCSV(caseId, startReturnTimeSlots, completeReturnTimeSlots, "Return Alternative Time Slots", "Time Slot Manager", "System");
            } else if (requestId <= successRequests + userInputErrors + timeSlotReturned + systemErrors) {
                // Simulate system errors
                int errorType = random.nextInt(2);
                switch (errorType) {
                    case 0:
                        String startSystemError1 = getCurrentTimestamp();
                        Thread.sleep(100); // Simulate delay
                        String completeSystemError1 = getCurrentTimestamp();
                        Utils.writeLogToCSV(caseId, startSystemError1, completeSystemError1, "System Error - Server is busy", "System Check", "Server");
                        break;
                    case 1:
                        String startSystemError2 = getCurrentTimestamp();
                        Thread.sleep(100); // Simulate delay
                        String completeSystemError2 = getCurrentTimestamp();
                        Utils.writeLogToCSV(caseId, startSystemError2, completeSystemError2, "System Error - Database connection failed", "Database Connection", "System");
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
