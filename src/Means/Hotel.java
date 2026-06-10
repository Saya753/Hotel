package Means;

import People.Staff;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Hotel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String location;
    private ArrayList<Room> rooms;
    private ArrayList<Staff> staff;

    public Hotel(String name, String location) {
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>();
        this.staff = new ArrayList<>();
    }

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

    public ArrayList<Room> getAvilableRooms() {
        ArrayList<Room> avaRooms = new ArrayList<>();
        for (Room r : rooms) {
            if (r.checkAvailability()) {
                avaRooms.add(r);
            }
        }
        return avaRooms;
    }


    public void editStaff(int i, String newName, String newPos) {
        staff.get(i).setName(newName);
        staff.get(i).setPosition(newPos);
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public boolean addRoom(Room room) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == room.getRoomNumber()) {
                return false;
            }
        }
        rooms.add(room);
        return true;
    }

    public boolean addStaff(Staff staff_a) {
        for (Staff s : staff) {
            if (s.getStaffID() == staff_a.getStaffID()) {
                return false;
            }
        }

        staff.add(staff_a);
        return true;
    }

    public void editRoom(int i, int newNumber, String newType, float newPrice, int newBedCount) {
        rooms.get(i).setRoomNumber(newNumber);
        rooms.get(i).setType(newType);
        rooms.get(i).setPrice(newPrice);
        rooms.get(i).setBedCount(newBedCount);
    }

    public void removeRoom(int i) {
        rooms.remove(i);
    }

    public void removeStaff(int i) {
        staff.remove(i);
    }

    @Override
    public String toString() {
        String message = "[" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ']';

        if (!rooms.isEmpty()) {
            message += "\n[Rooms]\n";
            for (Room r : rooms) {
                message += "\t-" + r + "\n";
            }
        }

        if (!staff.isEmpty()) {
            message += "\n[Staff]\n";
            for (Staff s : staff) {
                message += "\t-" + s + "\n";
            }
        }

        return message;
    }
}
