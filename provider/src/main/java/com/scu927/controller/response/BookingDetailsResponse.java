package com.scu927.controller.response;

import java.math.BigDecimal;

/**
 * @author Chuhan
 * @date 2024/9/9
 */
public class BookingDetailsResponse {

    private Long bookingId;
    private String bookingDate;
    private String timeSlot;
    private Integer quantity;
    private String username;
    private String email;
    private String scenicSpotName;
    private String scenicSpotLocation;
    private Integer scenicSpotCapacity;
    private String scenicSpotDescription;
    private BigDecimal scenicSpotPrice;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    public String getScenicSpotName() {
        return scenicSpotName;
    }

    public void setScenicSpotName(String scenicSpotName) {
        this.scenicSpotName = scenicSpotName;
    }

    public String getScenicSpotLocation() {
        return scenicSpotLocation;
    }

    public void setScenicSpotLocation(String scenicSpotLocation) {
        this.scenicSpotLocation = scenicSpotLocation;
    }

    public Integer getScenicSpotCapacity() {
        return scenicSpotCapacity;
    }

    public void setScenicSpotCapacity(Integer scenicSpotCapacity) {
        this.scenicSpotCapacity = scenicSpotCapacity;
    }

    public String getScenicSpotDescription() {
        return scenicSpotDescription;
    }

    public void setScenicSpotDescription(String scenicSpotDescription) {
        this.scenicSpotDescription = scenicSpotDescription;
    }

    public BigDecimal getScenicSpotPrice() {
        return scenicSpotPrice;
    }

    public void setScenicSpotPrice(BigDecimal scenicSpotPrice) {
        this.scenicSpotPrice = scenicSpotPrice;
    }
}
