package com.scu927.controller.request;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
public class RoomBookingRequest {

    private String homeAddress;  // Home address
    private String contactNumber;  // Contact number
    private String email;  // Email address
    private String bookingDate;  // Booking date
    private String roomGrade;  // Room grade (Standard, Deluxe, Suite)


    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public String getRoomGrade() {
        return roomGrade;
    }

    public void setRoomGrade(String roomGrade) {
        this.roomGrade = roomGrade;
    }
}