package Means;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int roomNumber;
    private String type;
    private float price;
    private int bedCount;
    private boolean isAvailable;
    private ArrayList<String> feature;

    public Room(int roomNumber, String type, float price, int bedCount, ArrayList<String> features) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.bedCount = bedCount;
        this.isAvailable = true;
        this.feature = features;
    }

    public boolean checkAvailability() {
        return isAvailable;
    }

    public void reserve() {
        isAvailable = false;
    }

    public void release() {
        isAvailable = true;
    }

    public void addFeature(String feature) {
        this.feature.add(feature);
    }

    public float cost() {
        return price * bedCount;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public ArrayList<String> getFeature() {
        return feature;
    }

    public void setFeature(ArrayList<String> feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", bedCount=" + bedCount +
                ", isAvailable=" + isAvailable +
                ", features=" + feature +
                '}';
    }
}
