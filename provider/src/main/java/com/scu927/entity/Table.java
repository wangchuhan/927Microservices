package com.scu927.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author Chuhan
 * @date 2024/9/8
 */


@TableName("tables")
public class Table {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long restaurantCafeId;
    private int tableNumber;
    private int capacity;
    private String location;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantCafeId() {
        return restaurantCafeId;
    }

    public void setRestaurantCafeId(Long restaurantCafeId) {
        this.restaurantCafeId = restaurantCafeId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
