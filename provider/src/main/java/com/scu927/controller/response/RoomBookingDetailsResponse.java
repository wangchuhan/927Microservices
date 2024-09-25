package com.scu927.controller.response;

import java.math.BigDecimal;

/**
 * @author Chuhan
 * @date 2024/9/9
 */
public class RoomBookingDetailsResponse {

    private Long bookingId;
    private String bookingDate;
    private String name;
    private String homeAddress;
    private String phoneNumber;
    private String email;
    private String roomGrade;
    private BigDecimal totalAmount;

    // Room details
    private String roomGradeDetails;
    private Integer roomCapacity;
    private BigDecimal roomPrice;


    // Getters and Setters
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoomGrade() {
        return roomGrade;
    }

    public void setRoomGrade(String roomGrade) {
        this.roomGrade = roomGrade;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Room information
    public String getRoomGradeDetails() {
        return roomGradeDetails;
    }

    public void setRoomGradeDetails(String roomGradeDetails) {
        this.roomGradeDetails = roomGradeDetails;
    }

    public Integer getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(Integer roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }


}
