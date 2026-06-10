package People;

import Means.Hotel;
import Means.HotelUtils;
import Means.Room;

import java.io.Serial;
import java.io.Serializable;

public class Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Customer customer;
    private Hotel hotel;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private boolean paymentStatus;

    public Booking(Customer customer, Hotel hotel, Room room) {
        this.customer = customer;
        this.hotel = hotel;
        this.room = room;
        this.checkInDate = HotelUtils.currentDateTime();
        this.checkOutDate = "In reserve";
        this.paymentStatus = false;
    }

    public void pay() {
        paymentStatus = true;
        checkOutDate = HotelUtils.currentDateTime();
    }

    public boolean isPayed() {
        return paymentStatus;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public float calculateTotalCost() {
        return room.cost();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "customer=" + customer.getName() +
                ", hotel=" + hotel.getName() +
                ", room=" + room.getRoomNumber() +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
