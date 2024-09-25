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

    // 生成唯一的 Case ID
    public static String generateUniqueCaseId() {
        return String.valueOf(counter.getAndIncrement());
    }

    // 获取当前时间戳
    public static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.now().format(formatter);
    }


    // 将日志写入CSV文件，检查文件是否存在，若不存在则先写入标题行
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


}
