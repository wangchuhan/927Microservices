package com.scu927.controller.request;

/**
 * @author Chuhan
 * @date 2024/9/8
 */

public class TourBookingRequest {
    private Long scenicSpotId;     // ID of the scenic spot to be booked
    private String bookingDate;    // Date of the booking in string format (e.g., "2024-09-15")
    private String timeSlot;       // Time slot for the booking (e.g., "08:00-10:00")
    private int quantity;          // Number of spots to be booked (e.g., 2 people)


    // Getters and Setters


    // Getters and Setters
    public Long getScenicSpotId() {
        return scenicSpotId;
    }

    public void setScenicSpotId(Long scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}