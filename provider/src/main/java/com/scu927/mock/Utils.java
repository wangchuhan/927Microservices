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


    private static final AtomicLong counter = new AtomicLong(1);

    // Generate unique Case ID
    public static String generateUniqueCaseId() {
        return String.valueOf(counter.getAndIncrement());
    }

    // Get current Timestamp
    public static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.now().format(formatter);
    }


    // Write logs to CSV file
    public static void writeLogToCSV(String logType,
                                     String caseId,
                                     String startTimestamp,
                                     String completeTimestamp,
                                     String activity, String resource,
                                     String role) {
        File file;
        switch (logType) {
            case "tourBooking":
                file = new File("tourBooking.csv"); // Log for tour-related activities
                break;
            case "roomBooking":
                file = new File("roomBooking.csv"); // Log for room-related activities
                break;
            case "tableReservation":
                file = new File("tableReservation.csv"); // Log for reservation-related activities
                break;
            default:
                file = new File("general_log.csv"); // Default log file
                break;
        }
        boolean isNewFile = !file.exists();  // Check if the log file exists

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // If the new is newly created, write the title line firstly
            if (isNewFile) {
                writer.write("Case ID,Start Times,Complete Timestamp,Activity,Resource,Role");
                writer.newLine();
            }

            // Write log data
            //   String logEntry = String.format("%s,%s,%s,%s,%s,%s", caseId, startTimestamp, completeTimestamp, activity, resource, role);
            String logEntry = String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", caseId, startTimestamp, completeTimestamp, activity, resource, role);
            writer.write(logEntry);
            writer.newLine();  // Start a new line
        } catch (IOException e) {
            System.out.println("Failed to write log to CSV");
        }
    }


}
