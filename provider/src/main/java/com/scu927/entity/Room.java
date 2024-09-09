package com.scu927.entity;

import java.math.BigDecimal;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
public class Room {
    private Long id;  // Room ID
    private String roomGrade;  // Room grade (e.g., Standard, Deluxe, Suite)
    private Integer capacity;  // Room capacity
    private BigDecimal price;  // Room price
    private Boolean isAvailable;  // Availability status

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomGrade() {
        return roomGrade;
    }

    public void setRoomGrade(String roomGrade) {
        this.roomGrade = roomGrade;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}