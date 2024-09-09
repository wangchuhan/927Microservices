package com.scu927.entity;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
    public class Booking {
    private Long id;                // Unique identifier for the booking
    private Long scenicSpotId;       // ID of the scenic spot being booked
    private String username;         // Username of the user making the booking
    private String email;            // Email address of the user making the booking
    private String bookingDate;      // Booking date in string format (e.g., "2024-09-15")
    private String timeSlot;         // Time slot for the booking (e.g., "08:00-10:00")
    private Integer quantity;        // Number of spots being booked (e.g., 2 people)


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScenicSpotId() {
        return scenicSpotId;
    }

    public void setScenicSpotId(Long scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

