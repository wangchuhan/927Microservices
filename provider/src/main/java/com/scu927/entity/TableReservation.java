package com.scu927.entity;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("table_reservations")
public class TableReservation {
    @TableId(type = IdType.AUTO)
    private Long id;               // Reservation ID
    private Long tableId;           // Foreign key to the table
    private String reservationDate; // Reservation date (e.g., "2024-09-15")
    private String timeSlot;        // Time slot for the reservation (e.g., "12:00-14:00")
    private int quantity;           // Number of guests for the reservation
    private String username;        // User's username
    private String email;           // User's email address
    private String name;            // User's full name
    private String phoneNumber;     // User's phone number

    // Getters and Setters


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
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
}