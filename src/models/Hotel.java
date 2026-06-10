package models;

import utils.FileHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String location;
    private double rating;
    private List<Room> rooms;
    private List<Staff> staffList;
    private List<Double> reviews;// لیستی از امتیازات داده شده به هتل
    private List<Hotel> hotels;

    // constructor
    public Hotel(String name, String location){
        this.name = name;
        this.location = location;
        this.rating = 0.0; // default rating
        this.rooms = new ArrayList<>();
        this.staffList = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.hotels = new ArrayList<>();
    }

    // متدهای getter , setter
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

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public List<Double> getReviews() {
        return reviews;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    // add a new room to hotel
    public boolean addRoom(Room room) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == room.getRoomNumber()) {
                return false;
            }
        }
        rooms.add(room);
        System.out.println("Room " + room.getRoomNumber() + " added to hotel " + name);
        return true;
    }

    // remove room from hotel
    public boolean removeRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                rooms.remove(room);
                System.out.println("Room " + roomNumber + " removed from hotel " + name);
                return true;
            } else {
                System.out.println("Room " + roomNumber + " not found in hotel " + name);
            }
        }
        return false;
    }

    // دریافت لیست اتاق های در دسترس
    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    // متدی برای بازگزداندن شماره اتاق

    // اضافه کردن کارمند
    public boolean addStaff(Staff staff) {
        for (Staff s : staffList) {
            if (s.getStaffID() == staff.getStaffID()) {
                return false;
            }
        }
        staffList.add(staff);
        System.out.println("Staff " + staff.getName() + " added to hotel " + name);
        return true;
    }

    // حذف کارمند بر اساس شناسه
    public boolean removeStaff(int staffID) {
        for (Staff staff : staffList) {
            if (staff.getStaffID() == staffID) {
                staffList.remove(staffID);
                System.out.println("Staff " + staffID + " removed from hotel " + name);
                return true;
            } else {
                System.out.println("Staff " + staffID + " not found in hotel " + name);
            }
        }
        return false;
    }

    public void addReview(double review) {
        if (review >= 0 && review <= 5) {
            reviews.add(review);
            calculateOverallRating();
            System.out.println("Review added. New rating: " + rating);
        } else {
            System.out.println("Review must be between 0 and 5.");
        }
    }

    // حساب کردن rating بر اساس review
    public void calculateOverallRating() {
        if (reviews.isEmpty()) {
            this.rating = 0.0;
        } else {
            double totalRating = 0.0;
            for (double review : reviews) {
                totalRating += review;
            }
            this.rating = totalRating / reviews.size();
        }
    }

    // اطلاعات هتل
    public String getHotelInfo() {
        return "Hotel Name: " + name
                + " >>> " + "Location: " + location
                + " >>> " + "Number Of Rooms: " + rooms.size()
                + " >>> " + "Rating: " + rating;
    }

    // افزودن هتل
    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
        saveToFile();
        System.out.println("Hotel " + hotel.getName() + " added successfully.");
    }

    // حذف هتل بر اساس اسم
    public void removeHotelByName(String name) {
        Hotel hotel = findHotelByName(name);
        if (hotel != null) {
            hotels.remove(hotel);
            saveToFile();
            System.out.println("Hotel " + name + " removed successfully.");
        } else {
            System.out.println("Hotel not found.");
        }
    }

    // پیدا کردن هتل بر اساس نام
    public Hotel findHotelByName(String name) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(name)) {
                return hotel;
            } else {
                System.out.println("Hotel " + name + " not found.");
            }
        }
        return null;
    }

    // نمایش اطلاعات تمام هتل ها
    public void showAllHotels() {
        if (hotels.isEmpty()) {
            System.out.println("No hotels available.");
        } else {
            for (Hotel hotel : hotels) {
                System.out.println(hotel.getHotelInfo());
                System.out.println("----------------------------");
            }
        }
    }

    // یرایش اطلاعات هتل
    public void updateHotelDetails(String name, String newName, String newLocation, Double newRating) {
        Hotel hotel = findHotelByName(name);
        if (hotel != null) {
            if (newName != null && !newName.isBlank()) {
                hotel.setName(newName);
            }

            if (newLocation != null && !newLocation.isBlank()) {
                hotel.setLocation(newLocation);
            }

            if (newRating != null && newRating > 0) {
                hotel.setRating(newRating);
            }
            saveToFile();
            System.out.println("Hotel details updated successfully.");
        }
    }

    // ذخیره هتل در فایل
    private void saveToFile() {
        FileHandler.writeToFile("hotels.dat", hotels);
        System.out.println("Hotel data saved to file.");
    }

    public Staff getStaff(int staffID) {
        return null;
    }

    public Room getRooms(int roomNumber) {
        return null;
    }
}