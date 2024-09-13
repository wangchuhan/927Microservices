package com.scu927.common;

public class Response<T> {

    private int code;  // Business status code
    private String message;  // Return message
    private T data;  // Entity object

    // Constructors
    public Response() {
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Success response without data
    public static <T> Response<T> success() {
        return new Response<>(200, "Success", null);
    }

    // Success response with data
    public static <T> Response<T> success(T data) {
        return new Response<>(200, "Success", data);
    }

    // Success response with custom message, without data
    public static <T> Response<T> success(String message) {
        return new Response<>(200, message, null);
    }

    // Static method to construct error response
    public static <T> Response<T> error(int code, String message) {
        return new Response<>(code, message, null);
    }

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;  // Support for method chaining
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
