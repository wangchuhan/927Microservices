package com.scu927.controller.response;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
public class RoomBookingResponse {
    private String confirmationMessage;  // Booking confirmation message
    private String debitConfirmation;  // Debit confirmation
    private String alternativeOption;  // Alternative room option if unavailable
    private String cancelPolicy;  // Cancel policy details

    public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public void setConfirmationMessage(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }

    public String getDebitConfirmation() {
        return debitConfirmation;
    }

    public void setDebitConfirmation(String debitConfirmation) {
        this.debitConfirmation = debitConfirmation;
    }

    public String getAlternativeOption() {
        return alternativeOption;
    }

    public void setAlternativeOption(String alternativeOption) {
        this.alternativeOption = alternativeOption;
    }

    public String getCancelPolicy() {
        return cancelPolicy;
    }

    public void setCancelPolicy(String cancelPolicy) {
        this.cancelPolicy = cancelPolicy;
    }
}