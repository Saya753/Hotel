package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking implements Serializable {
    private static final long seralVersionUID = 1L;

    // Attributes
    private int ID; // شناسه رزرو
    private int reception; // شناسه پذیرش
    private Customer customer; // مشتری
    private Room room; // اتاق
    private Date checkInDate; // تاریخ پذیرش
    private Date checkOutDate; // تاریخ خروج
    private String paymentStatus; // وضعیت پرداخت (paid, unpaid)
    private List<Booking> bookings;

    // Constructor
    public Booking(int ID, int reception, Date checkInDate, Date checkOutDate, Customer customer, Room room) {
        this.ID = ID;
        this.reception = reception;
        this.checkInDate = checkInDate;
        this.checkOutDate =checkOutDate;
        this.paymentStatus = "Pending";
        this.customer = customer;
        this.room = room;
        this.bookings = new ArrayList<>();
    }

    // Getter , Setter
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getReception() {
        return reception;
    }

    public void setReception(int reception) {
        this.reception = reception;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // افزودن رزرو جدید
    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.getRoom().reserve(); // تغییر وضعیت اتاق به رزرو شده
        System.out.println("Booking added successfully. Booking ID: " + booking.getID());
    }

    // هزینه رزرو
    public double calculateTotalCost() {
        long duration = checkOutDate.getTime() - checkInDate.getTime(); // محاسبه زمان
        long days = duration / (1000 * 60 * 60 * 24); // محاسبه تعداد روزها
        return room.getPrice() * days;
    }

    // محاسبه کل هزینه رزروها
    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Booking booking : bookings) {
            totalRevenue += booking.calculateTotalCost();
        }
        return totalRevenue;
    }

    // پیدا کردن رزرو بر اساس شناسه
    public Booking findBookingByID(int bookingID) {
        for (Booking booking : bookings) {
            if (booking.getID() == bookingID) {
                return booking;
            } else {
                System.out.println("Booking ID " + bookingID + " not found.");
            }
        }
        return null;
    }

    // کنسل کردن رزرو
    public boolean cancelBooking() {
        Booking booking = findBookingByID(ID);
        if (booking != null) {
            if ("Paid".equals(booking.getPaymentStatus())) {
                System.out.println("Booking is already paid and cannot be canceled.");
                return false;
            } else {
                bookings.remove(booking);
                booking.getRoom().release(); // ازاد کردن اتاق
                System.out.println("Booking " + ID + " has been canceled.");
                return true;
            }
        }
        return false;
    }

    // تغییر وضعیت پرداخت به پرداخت شده
    public void processPayment() {
        this.paymentStatus = "Paid";
        System.out.println("Payment for booking " + ID + " has been processed.");
    }

    // اطلاعات رزرو
    public String getBookingInfo() {
        return "Booking ID: " + ID
                + " >>> " + "Customer: " + customer.getName()
                + " >>> " + "Room: " + room.getRoomNumber()
                + " >>> " + "Check-in Date: " + checkInDate
                + " >>> " + "Check-out Date: " + checkOutDate
                + " >>> " + "Payment Status: " + paymentStatus
                + " >>> " + "Reception: " + reception;
    }

    // نمایش تمام رزروها
    public void showAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking.getBookingInfo());
                System.out.println("-------------------------------------------");
            }
        }
    }

    // ویرایش اطلاعات رزرو
    public void updateBookingDetails(int ID, Date newCheckInDate, Date newCheckOutDate, String newPaymentStatus) {
        Booking booking = findBookingByID(ID);
        if (booking != null) {
            if (newCheckInDate != null) {
                booking.setCheckInDate(newCheckInDate);
            }

            if (newCheckOutDate != null) {
                booking.setCheckOutDate(newCheckOutDate);
            }

            if (newPaymentStatus != null && (newPaymentStatus.equals("Paid") || newPaymentStatus.equals("Unpaid"))) {
                booking.setPaymentStatus(newPaymentStatus);
            }

            System.out.println("Booking details updated successfully.");
        }
    }
}
