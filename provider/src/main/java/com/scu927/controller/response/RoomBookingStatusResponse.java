package com.scu927.controller.response;

import java.math.BigDecimal;

/**
 * @author Chuhan
 * @date 2024/9/9
 */
public class RoomBookingStatusResponse {

    private Long bookingId;  // 返回给前端的预定号
    private String status;   // 返回状态信息

    public RoomBookingStatusResponse(Long bookingId, String status) {
        this.bookingId = bookingId;
        this.status = status;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
