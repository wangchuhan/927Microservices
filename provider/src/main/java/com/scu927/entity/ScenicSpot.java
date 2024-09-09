package com.scu927.entity;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
import java.math.BigDecimal;

public class ScenicSpot {
    private Long id;              // Unique identifier for the scenic spot
    private String name;           // Name of the scenic spot
    private String location;       // Location of the scenic spot (e.g., city or specific area)
    private Integer capacity;      // Maximum number of people the scenic spot can accommodate
    private String description;    // Description or details about the scenic spot
    private BigDecimal price;      // Price to book the scenic spot, using BigDecimal for precision


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}