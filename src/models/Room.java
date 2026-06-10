package models;

import utils.FileHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private int roomNumber;
    private double price;
    private boolean isAvailable;
    private String type;
    private int bedCount;
    private List<String> features; //ویژگی های اتاق مانند تراس و اینترنت و کولر
    private List<Room> rooms;

    // constructor
    public Room(int roomNumber, double price, boolean isAvailable, String type, int bedCount) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.isAvailable = isAvailable;
        this.type = type;
        this.bedCount = bedCount;
        this.features = new ArrayList<>();
        this.rooms = FileHandler.readFromFile("room.dat");
        if (this.rooms == null) {
            this.rooms = new ArrayList<>();
        }
    }

    // getter , setter
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    // متد دردسترس بودن اتاق
    public boolean isAvailable() {
        return isAvailable;
    }

    // متد برای رزرو اتاق
    public void reserve() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Room " + roomNumber + " has been reserved.");
        } else {
            System.out.println("Room " + roomNumber + " is already reserved.");
        }
    }

    // خارج کردن اتاق از رزرو
    public void release() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Room " + roomNumber + " is now available.");
        } else {
            System.out.println("Room " + roomNumber + " is already available.");
        }
    }

    // اضافه کردن ویژگی جدید به اتاقف
    public void addFeature(String feature) {
        if (!features.contains(feature)) {
            features.add(feature);
            System.out.println("Feature " + feature + " has been added to the room " + roomNumber + ". ");
        } else {
            System.out.println("Feature " + feature + " already exist in the room " + roomNumber + ". ");
        }
    }

    // اطلاعات اتاق
    public String getRoomInfo() {
        return "Room Number: " + roomNumber
                + " >>> " + "Type: " + type
                + " >>> " + "Price: " + price
                + " >>> " + "Bed Count: " + bedCount
                + " >>> " + "Available: " + (isAvailable ? "Yes" : "No")
                + " >>> " + "Features: " + String.join(", ", features);
    }

    // افزودن اتاق جدید
    public void addRoom(Room room) {
        rooms.add(room);
        saveToFile();
        System.out.println("Room " + room.getRoomNumber() + " added successfully.");
    }

    // پیدا کردن اتاق بر اساس شماره
    public Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            } else {
                System.out.println("Room " + roomNumber + " not found.");
            }
        }
        return null;
    }

    // نمایش تمام اتاق ها
    public void showAllRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            for (Room room : rooms) {
                System.out.println(room.getRoomInfo());
                System.out.println("-------------------------------");
            }
        }
    }

    // حذف اتاق بر اساس شماره
    public void removeRoom(int roomNumber) {
        Room room = findRoomByNumber(roomNumber);
        if (room != null) {
            rooms.remove(room);
            saveToFile();
            System.out.println("Room " + roomNumber + " removed successfully.");
        } else {
            System.out.println("Room not found.");
        }
    }

    // نمایش اتاق های دردسترس
    public void showAvailableRooms() {
        boolean found = false;
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room.getRoomInfo());
                System.out.println("-------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available rooms found.");
        }
    }

    // ویرایش اطلاعات اتاق
    public void updateRoomDetails(int roomNumber, Double newPrice, String newType, Boolean newAvailability) {
        Room room = findRoomByNumber(roomNumber);
        if (room != null) {
            if (newPrice != null) {
                room.setPrice(newPrice);
            }

            if (newType != null && !newType.isBlank()) {
                room.setType(newType);
            }

            if (newAvailability != null) {
                room.setAvailable(newAvailability);
            }
            saveToFile();
            System.out.println("Room details updated successfully");
        }
    }

    // ذخیره اتاق ها در فایل
    private void saveToFile() {
        FileHandler.writeToFile("rooms.dat", rooms);
        System.out.println("Room data saved to file.");
    }
}