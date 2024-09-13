package com.scu927.controller.request;

import jakarta.validation.constraints.NotNull;

/**
 * @author Chuhan
 * @date 2024/9/9
 */
public class CancelBookingRequest {


    @NotNull(message = "Booking ID cannot be null")
    private Long id;                     // book ID

    private String cancellationReason;   // option

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}