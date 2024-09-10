package com.scu927.entity;

import java.math.BigDecimal;

/**
 * @author Chuhan
 * @date 2024/9/9
 */
public class RoomBookingPayment {
    private Long id;
    private Long roomBookingId;
    private BigDecimal roomPaymentAmount;
    private String paymentDate;
    private String paymentMethod;
    private String paymentStatus;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}