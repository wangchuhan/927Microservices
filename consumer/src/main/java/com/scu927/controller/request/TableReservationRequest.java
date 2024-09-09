package com.scu927.controller.request;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
public class TableReservationRequest {

    private Long restaurantCafeId;  // Restaurant/Cafe ID
    private String reservationDate; // Reservation date in the format (e.g., "2024-09-15")
    private String timeSlot;        // Time slot for the reservation (e.g., "12:00-14:00")
    private int quantity;           // Number of guests
    private String username;        // User's username
    private String email;           // User's email address
    private String name;            // User's full name
    private String phoneNumber;     // User's contact number

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

// Getters and Setters

    public Long getRestaurantCafeId() {
        return restaurantCafeId;
    }

    public void setRestaurantCafeId(Long restaurantCafeId) {
        this.restaurantCafeId = restaurantCafeId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
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
}
