package com.scu927.controller.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * @author Chuhan
 * @date 2024/9/9
 */
public class PaymentRequest {
    @NotNull(message = "Booking ID cannot be null")
    private Long roomBookingId;          // booking  ID
    @NotNull(message = "roomPaymentAmount ID cannot be null")
    private BigDecimal roomPaymentAmount;
    @NotNull(message = "username cannot be null")

            private String username;
    @NotNull(message = "paymentMethod cannot be null")
    private String paymentMethod;

    @NotNull(message = "email cannot be null")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

// Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRoomBookingId() {
        return roomBookingId;
    }

    public void setRoomBookingId(Long roomBookingId) {
        this.roomBookingId = roomBookingId;
    }

    public BigDecimal getRoomPaymentAmount() {
        return roomPaymentAmount;
    }

    public void setRoomPaymentAmount(BigDecimal roomPaymentAmount) {
        this.roomPaymentAmount = roomPaymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
