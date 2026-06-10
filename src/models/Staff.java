package models;

import utils.FileHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Staff extends ArrayList<Staff> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String position;
    private int staffID;
    private List<Staff> staffList;

    // constructor
    public Staff(String name, String position, int staffID) {
        this.name = name;
        this.position = position;
        this.staffID = staffID;
        this.staffList = new ArrayList<>();
    }

    // getter , setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    // نمایش اطلاعات کارکنان
    public String viewStaffDetails() {
        return "Staff ID: " + staffID
                + " >>> " + "Staff Name: " + name
                + " >>> " + "Staff Position: " + position;
    }

    // اضافه کردن کارمند
    public void addStaff(Staff staff) {
        staffList.add(staff);
        saveToFile();
        System.out.println("Staff added successfully.");
    }

    // پیدا کردن کارمند بر اساس شناسه
    public Staff findStaffByID(int staffID) {
        for (Staff staff : staffList) {
            if (staff.getStaffID() == staffID) {
                return staff;
            } else {
                System.out.println("Staff " + staffID + " not found.");
            }
        }
        return null;
    }

    // حذف کارمند بر اساس شناسه
    public void removeStaffByID(int staffID) {
        Staff staff = findStaffByID(staffID);
        if (staff !=null) {
            staffList.remove(staffID);
            saveToFile();
            System.out.println("Staff " + staffID + " removed successfully.");
        } else {
            System.out.println("Staff not found.");
        }
    }

    // مایش تمام کارمندان
    public void showAllStaff() {
        if (staffList.isEmpty()) {
            System.out.println("No staff available.");
        } else {
            for (Staff staff : staffList) {
                System.out.println(staff.viewStaffDetails());
                System.out.println("-------------------------------------");
            }
        }
    }

    // ویرایش اطلاعات کارمند
    public void updateStaffDetails(int staffID, String newName, String newPosition) {
        Staff staff = findStaffByID(staffID);
        if (staff != null) {
            if (newName != null && !newName.isBlank()) {
                staff.setName(newName);
            }

            if (newPosition != null && !newPosition.isBlank()) {
                staff.setPosition(newPosition);
            }
            saveToFile();
            System.out.println("Staff details updated successfully.");
        }
    }

    // ذخیره کارمندان در فایل
    private void saveToFile() {
        FileHandler.writeToFile("staffList.dat", staffList);
        System.out.println("Staff data saved to file.");
    }
}
