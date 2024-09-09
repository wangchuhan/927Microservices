package com.scu927.controller.response;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
public class TableRecommendationResponse {

    private String timeSlot;  // timeSlot recommended
    private int availableTables;  // the tables can be reserved

    // 构造方法
    public TableRecommendationResponse() {
    }

    public TableRecommendationResponse(String timeSlot, int availableTables) {
        this.timeSlot = timeSlot;
        this.availableTables = availableTables;
    }

    // Getters and Setters
    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getAvailableTables() {
        return availableTables;
    }

    public void setAvailableTables(int availableTables) {
        this.availableTables = availableTables;
    }

    // 可选: 重写 toString() 方法以便调试和日志记录
    @Override
    public String toString() {
        return "TableRecommendationResponse{" +
                "timeSlot='" + timeSlot + '\'' +
                ", availableTables=" + availableTables +
                '}';
    }
}
