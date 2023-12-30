package ParkingLot.models;

import java.util.Date;

public class Payment {
    private PaymentMode paymentMode;
    private int amount; // Can store as integer and return by dividing 100(if 2 decimal supported)
    private Date time;
    private PaymentStaus paymentStaus;
    private String referenceNumber;

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public PaymentStaus getPaymentStaus() {
        return paymentStaus;
    }

    public void setPaymentStaus(PaymentStaus paymentStaus) {
        this.paymentStaus = paymentStaus;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
