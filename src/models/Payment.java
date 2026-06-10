package models;

import javax.crypto.spec.PSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    // Attributes
    private String receipt; // رسید
    private Date paymentDate; // تاریخ پرداخت
    private String transactionID; // شناسه تراکنش
    private String status; // وضعیت پرداخت paid , unpaid
    private String paymentMethod; // روش پرداخت نقد یا کارت
    private double amount; // مبلغ پرداخت
    private List<Payment> payments; // لیست پرداخت ها

    // Constructor
    public Payment(double amount, String paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = "Unpaid";
        this.paymentDate = null;
        this.transactionID = null;
        this.receipt = null;
        this.payments = new ArrayList<>();
    }

    // Getter , Setter
    public String getReceipt() {
        return receipt;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // پروسه پرداخت
    public void processPayment() {
        if ("Paid".equals(status)) {
            System.out.println("Payment has already been processed.");
        } else {
            this.status = "Paid";
            this.paymentDate = new Date();
            this.transactionID = generateTransactionID();
            this.receipt = generateReceipt();
            System.out.println("Payment has been successfully processed.");
        }
    }

    // بازپرداخت با تخفیف
    public void refundPayment() {
        if ("Unpaid".equals(status)) {
            System.out.println("No payment to refund.");
        } else {
            this.status = "Unpaid";
            this.paymentDate = null;
            this.transactionID = null;
            this.receipt = null;
            System.out.println("Payment has been refunded.");
        }
    }

    // ساخت رسید
    private String generateReceipt() {
        return "Receipt: {TransactionID: " + transactionID
                + " >>> " + "Amount: $" + amount
                + " >>> " + "Payment Date: " + paymentDate
                + " >>> " + "Payment Method: " + paymentMethod
                + "}";
    }

    // ساخت شناسه تراکنش
    private String generateTransactionID() {
        return "TXN" + System.currentTimeMillis();
    }

    // تخفیف
    public void applyDiscount(float discount) {
        if (discount > 0 && discount <= 1) {
            this.amount = this.amount * (1 - discount);
            System.out.println("Discount applied. New amount: $" + this.amount);
        } else {
            System.out.println("Invalid discount value. Must be between 0 and 1.");
        }
    }

    // افزودن پرداخت جدید
    public void addPayment(Payment payment) {
        payments.add(payment);
        System.out.println("Payment added successfully.");
    }

    // یدا کردن پرداخت بر اساس شناسه
    public Payment findPaymentByTransactionID(String transactionID) {
        for (Payment payment : payments) {
            if (payment.getTransactionID() != null && payment.getTransactionID().equals(transactionID)) {
                return payment;
            } else {
                System.out.println("Payment " + transactionID + " not found.");
            }
        }
        return null;
    }

    // نمایش تمام پرداخت ها
    public void showAllPayments() {
        if (payments.isEmpty()) {
            System.out.println("No payments available.");
        }  else {
            for (Payment payment : payments) {
                System.out.println(payment.getReceipt());
            }
        }
    }

    // محاسبه کل مبلغ پرداخت ها
    public double calculateTotalPayments() {
        double total = 0;
        for (Payment payment : payments) {
            total += payment.getAmount();
        }
        return total;
    }

    // بازپرداخت با شناسه
    public void refundPaymentByTransactionID(String transactionID) {
        Payment payment = findPaymentByTransactionID(transactionID);
        if (payment != null) {
            payment.refundPayment();
        }
    }

    // یرایش
    public void updatePaymentDetails(String transactionID, String newPaymentMethod, Double newAmount) {
        Payment payment = findPaymentByTransactionID(transactionID);
        if (payment != null) {
            if (newPaymentMethod != null && !newPaymentMethod.isBlank()) {
                payment.setPaymentMethod(newPaymentMethod);
            }

            if (newAmount != null && newAmount > 0) {
                payment.setAmount(newAmount);
            }

            System.out.println("Payment details updated successfully.");
        }
    }
}
