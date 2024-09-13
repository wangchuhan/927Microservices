package com.scu927.controller.request;

import jakarta.validation.constraints.NotNull;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
public class RoomBookingRequest {
    @NotNull(message = "name cannot be null")
    private String name;  // Guest name
    @NotNull(message = "homeAddress cannot be null")
    private String homeAddress;  // Home address


    @NotNull(message = "bookingDate cannot be null")
    private String bookingDate;  // Booking date
    @NotNull(message = "roomGrade cannot be null")
    private String roomGrade;  // Room grade (Standard, Deluxe, Suite)


    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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