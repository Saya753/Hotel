import Means.Hotel;
import Means.Room;
import People.Booking;
import People.ContactInfo;
import People.Customer;
import People.Staff;
import Ui.MessageType;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Hotel> hotels;
    private static ArrayList<Customer> customers;
    private static ArrayList<Booking> bookings;

    // UI
    private static final String[] mainMenu = {
            "Add Hotel",
            "Add Room",
            "Add Staff",
            "Add Customer",
            "Book a Room",
            "Show Hotels",
            "Show Customers",
            "Show Bookings",
            "Show Payments",
            "Remove Hotel",
            "Remove Room",
            "Remove Staff",
            "Remove Customer",
            "Remove Booking",
            "Open Edit Menu",
            "EXIT"
    };

    private static final String[] editMenu = {
            "Edit Hotel",
            "Edit Staff",
            "Edit Room",
            "Edit Customer",
            "Pay Booking",
            "Back"
    };

    private static Scanner intScanner;
    private static Scanner floatScanner;
    private static Scanner strScanner;

    private static MessageType messageType = MessageType.NORMAL;
    private static String globalMessage = null;

    public static void main(String[] args) {
        init();
        runUI();
    }

    private static void init() {
        hotels = new ArrayList<>();
        customers = new ArrayList<>();
        bookings = new ArrayList<>();
        intScanner = new Scanner(System.in);
        strScanner = new Scanner(System.in);
        floatScanner = new Scanner(System.in);

        loadData();
    }

    private static void runUI() {
        clearScreen();
        System.out.println("--~~Welcome to the hotel management system~~--");

        outer: while (true) {
            printGlobalMessage();

            // Printout the menu
            for (int i = 0; i < mainMenu.length; i++) {
                System.out.println((i + 1)+ ".\t" + mainMenu[i]);
            }

            System.out.print(">> ");
            int input = intScanner.nextInt();
            clearScreen();

            switch (input) {
                case 1:
                    addHotel();
                    clearScreen();
                    break;

                case 2:
                    addRoom();
                    clearScreen();
                    break;

                case 3:
                    addStaff();
                    clearScreen();
                    break;


                case 4:
                    addCustomer();
                    clearScreen();
                    break;

                case 5:
                    bookRoom();
                    clearScreen();
                    break;

                case 6:
                    showHotels();
                    clearScreen();
                    break;

                case 7:
                    showCustomers();
                    clearScreen();
                    break;

                case 8:
                    showBookings();
                    clearScreen();
                    break;

                case 9:
                    showPayments();
                    clearScreen();
                    break;

                case 10:
                    removeHotel();
                    clearScreen();
                    break;

                case 11:
                    removeRoom();
                    clearScreen();
                    break;

                case 12:
                    removeStaff();
                    clearScreen();
                    break;

                case 13:
                    removeCustomer();
                    clearScreen();
                    break;

                case 14:
                    removeBooking();
                    clearScreen();
                    break;

                case 15:
                    editMenu();
                    clearScreen();
                    break;

                case 16:
                    clearScreen();
                    break outer;

                default:
                    globalMessage = "[out of range option]";
                    messageType = MessageType.ERROR;
                    clearScreen();
            }
        }

        saveData();
    }

    // Helper functions
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String getStringInWhile(String message) {
        String result = "";
        do {
            System.out.print(message);
            result = strScanner.nextLine();
        } while (result.isBlank());
        return result;
    }

    private static int getIntInWhile(String message, int min, int max) {
        int result;
        do {
            System.out.print(message);
            result = intScanner.nextInt();
        } while (result < min || result > max);
        return result;
    }

    private static float getFloatInWhile(String message, float min, float max) {
        float result;
        do {
            System.out.print(message);
            result = floatScanner.nextFloat();
        } while (result < min || result > max);
        return result;
    }

    private static void printGlobalMessage() {
        // Check for error and print it if there is one
        if (globalMessage != null) {
            if (messageType == MessageType.SUCCESS) {
                System.out.println("\033[32mSUCCESS: " + globalMessage + "\033[0m");
            } else if (messageType == MessageType.ERROR) {
                System.out.println("\033[31mERROR: " + globalMessage + "\033[0m");
            } else {
                System.out.println("\033[33m" + globalMessage + "\033[0m");
            }

            globalMessage = null;
        }
    }

    private static void addHotel() {
        String name = getStringInWhile("Name: ");
        String location = getStringInWhile("Location: ");

        for (Hotel h : hotels) {
            if (h.getName().equals(name) || h.getLocation().equals(location)) {
                globalMessage = "[This Hotel already exists]";
                messageType = MessageType.ERROR;
                return;
            }
        }

        hotels.add(new Hotel(name, location));
        globalMessage = "[Hotel added]";
        messageType = MessageType.SUCCESS;
    }

    private static void showHotels() {
        messageType = MessageType.NORMAL;

        if (hotels.isEmpty()) {
            globalMessage = "[No Hotels in the system]";
            return;
        }

        globalMessage = "";
        for (int i = 0; i < hotels.size(); i++) {
            globalMessage = globalMessage + "\n" + (i + 1) + ". " + hotels.get(i);
        }
    }

    private static void addRoom() {
        if (hotels.isEmpty()) {
            globalMessage = "[No Hotels in the system]";
            messageType = MessageType.NORMAL;
            return;
        }

        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelNum = getIntInWhile("Which Hotel: ", 1, hotels.size()) - 1;
        int roomNum = getIntInWhile("RoomNumber: ", 1, 100000);
        String type = getStringInWhile("Type: ");
        float price = getFloatInWhile("Price: ", 1, 100000);
        int bedCount = getIntInWhile("Bed Count: ", 1, 10);
        ArrayList<String> features = new ArrayList<>();
        System.out.println("Features(-1 to end): ");
        String feature = "";
        int n = 1;
        while (true) {
            feature = getStringInWhile(n + ": ");
            if (feature.equals("-1"))
                break;
            features.add(feature);
            n++;
        }

        if (hotels.get(hotelNum).addRoom(new Room(roomNum, type, price, bedCount, features))) {
            globalMessage = "[Room Added]";
            messageType = MessageType.SUCCESS;
        } else {
            globalMessage = "[Room already exists]";
            messageType = MessageType.ERROR;
        }
    }

    private static void addStaff() {
        if (hotels.isEmpty()) {
            globalMessage = "[No Hotels in the system]";
            messageType = MessageType.NORMAL;
            return;
        }

        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelNum = getIntInWhile("Which Hotel: ", 1, hotels.size()) - 1;
        int staffID = getIntInWhile("staffID: ", 1, 100000);
        String name = getStringInWhile("name: ");
        String position = getStringInWhile("position: ");


        if (hotels.get(hotelNum).addStaff(new Staff(staffID, name, position))) {
            globalMessage = "[Staff Added]";
            messageType = MessageType.SUCCESS;
        } else {
            globalMessage = "[Staff already exists]";
            messageType = MessageType.ERROR;
        }
    }

    private static void addCustomer() {
        String name = getStringInWhile("Name: ");
        String phone = getStringInWhile("Phone: ");
        String email = getStringInWhile("Email: ");

        customers.add(new Customer(name, new ContactInfo(phone, email)));

        globalMessage = "[Customer added]";
        messageType = MessageType.SUCCESS;
    }

    private static void showCustomers() {
        messageType = MessageType.NORMAL;

        if (customers.isEmpty()) {
            globalMessage = "[No Customers in the system]";
            return;
        }

        globalMessage = "";
        for (int i = 0; i < customers.size(); i++) {
            globalMessage = globalMessage + "\n" + (i + 1) + ". " + customers.get(i);
        }
    }

    private static void bookRoom() {
        if (hotels.isEmpty()) {
            globalMessage = "[No Hotels in the system]";
            messageType = MessageType.NORMAL;
            return;
        }

        if (customers.isEmpty()) {
            globalMessage = "[There are no customers at the moment]";
            messageType = MessageType.NORMAL;
            return;
        }

        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelNum = getIntInWhile("Which Hotel: ", 1, hotels.size()) - 1;

        ArrayList<Room> rooms = hotels.get(hotelNum).getAvilableRooms();

        if (rooms.isEmpty()) {
            globalMessage = "[No Available Rooms in this hotel]";
            messageType = MessageType.NORMAL;
            return;
        }

        for (int i = 0; i < rooms.size(); i++) {
            System.out.println((i + 1) + ". " + rooms.get(i));
        }

        int roomNum = getIntInWhile("Which room: ", 1, rooms.size()) - 1;

        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i));
        }

        int customerNum = getIntInWhile("Which Customer: ", 1, customers.size()) - 1;

        Booking booking = new Booking(customers.get(customerNum), hotels.get(hotelNum), rooms.get(roomNum));
        bookings.add(booking);

        globalMessage = "[Booking Added]";
        messageType = MessageType.SUCCESS;
    }

    private static void showBookings() {
        messageType = MessageType.NORMAL;

        if (bookings.isEmpty()) {
            globalMessage = "[No Bookings in the system]";
            return;
        }

        globalMessage = "";
        for (int i = 0; i < bookings.size(); i++) {
            globalMessage = globalMessage + "\n" + (i + 1) + ". " + bookings.get(i);
        }
    }

    private static void showPayments() {
        messageType = MessageType.NORMAL;

        ArrayList<Booking> payments = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.isPayed()) {
                payments.add(b);
            }
        }

        if (payments.isEmpty()) {
            globalMessage = "[No Payments yet]";
            return;
        }

        globalMessage = "";
        for (int i = 0; i < payments.size(); i++) {
            globalMessage = globalMessage + "\n" + (i + 1) + ". " + payments.get(i);
        }
    }

    private static void removeHotel() {
        if (hotels.isEmpty()) {
            messageType = MessageType.NORMAL;
            globalMessage = "[No Hotels in the system]";
            return;
        }

        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelNum = getIntInWhile("Which Hotel: ", 1, hotels.size()) - 1;

        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getHotel().getName().equals(hotels.get(hotelNum).getName())) {
                bookings.remove(i);
                break;
            }
        }

        hotels.remove(hotelNum);

        messageType = MessageType.SUCCESS;
        globalMessage = "[Hotel deleted]";
    }

    private static void removeRoom() {
        if (hotels.isEmpty()) {
            messageType = MessageType.NORMAL;
            globalMessage = "[No Hotels in the system]";
            return;
        }

        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelNum = getIntInWhile("Which Hotel: ", 1, hotels.size()) - 1;

        ArrayList<Room> rooms = hotels.get(hotelNum).getAvilableRooms();

        if (rooms.isEmpty()) {
            globalMessage = "[No Available Rooms in this hotel]";
            messageType = MessageType.NORMAL;
            return;
        }

        for (int i = 0; i < rooms.size(); i++) {
            System.out.println((i + 1) + ". " + rooms.get(i));
        }

        int roomNum = getIntInWhile("Which room: ", 1, rooms.size()) - 1;

        hotels.get(hotelNum).removeRoom(roomNum);

        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getRoom().getRoomNumber() == rooms.get(roomNum).getRoomNumber()) {
                bookings.remove(i);
                break;
            }
        }

        messageType = MessageType.SUCCESS;
        globalMessage = "[Room removed]";
    }

    private static void removeCustomer() {
        if (customers.isEmpty()) {
            messageType = MessageType.NORMAL;
            globalMessage = "[No Customers in the system]";
            return;
        }

        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i));
        }

        int customerNum = getIntInWhile("Which Customer: ", 1, customers.size()) - 1;

        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getCustomer().getName().equals(customers.get(customerNum).getName())) {
                bookings.remove(i);
                break;
            }
        }

        customers.remove(customerNum);

        messageType = MessageType.SUCCESS;
        globalMessage = "[Customer removed]";
    }

    private static void removeStaff() {
        if (hotels.isEmpty()) {
            messageType = MessageType.NORMAL;
            globalMessage = "[No Hotels in the system]";
            return;
        }

        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelNum = getIntInWhile("Which Hotel: ", 1, hotels.size()) - 1;

        ArrayList<Staff> staff = hotels.get(hotelNum).getStaff();

        for (int i = 0; i < staff.size(); i++) {
            System.out.println((i + 1) + ". " + staff.get(i));
        }

        int staffNum = getIntInWhile("Which Staff: ", 1, staff.size()) - 1;

        hotels.get(hotelNum).removeStaff(staffNum);

        messageType = MessageType.SUCCESS;
        globalMessage = "[Staff deleted]";
    }

    private static void removeBooking() {
        if (bookings.isEmpty()) {
            messageType = MessageType.NORMAL;
            globalMessage = "[No Bookings in the system]";
            return;
        }

        for (int i = 0; i < bookings.size(); i++) {
            System.out.println((i + 1) + ". " + bookings.get(i));
        }

        int bookingNum = getIntInWhile("Which Booking: ", 1, bookings.size()) - 1;

        bookings.remove(bookingNum);

        messageType = MessageType.SUCCESS;
        globalMessage = "[Booking removed]";
    }

    private static void editHotel() {
        if (hotels.isEmpty()) {
            messageType = MessageType.NORMAL;
            globalMessage = "[No Hotels in the system]";
            return;
        }

        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelNum = getIntInWhile("Which Hotel: ", 1, hotels.size()) - 1;

        String newName = getStringInWhile("New Name: ");
        String newLoc = getStringInWhile("New Loc: ");

        hotels.get(hotelNum).setName(newName);
        hotels.get(hotelNum).setLocation(newLoc);

        messageType = MessageType.SUCCESS;
        globalMessage = "[Hotel edited]";
    }

    private static void editStaff() {
        if (hotels.isEmpty()) {
            messageType = MessageType.NORMAL;
            globalMessage = "[No Hotels in the system]";
            return;
        }

        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelNum = getIntInWhile("Which Hotel: ", 1, hotels.size()) - 1;

        ArrayList<Staff> staff = hotels.get(hotelNum).getStaff();

        if (staff.isEmpty()) {
            messageType = MessageType.NORMAL;
            globalMessage = "[No Staff in this hotel]";
            return;
        }

        for (int i = 0; i < staff.size(); i++) {
            System.out.println((i + 1) + ". " + staff.get(i));
        }

        int staffNum = getIntInWhile("Which Staff: ", 1, staff.size()) - 1;

        String newName = getStringInWhile("New Name: ");
        String newPos = getStringInWhile("New Position: ");

        hotels.get(hotelNum).editStaff(staffNum, newName, newPos);

        messageType = MessageType.SUCCESS;
        globalMessage = "[Staff Edited]";
    }

    private static void editRoom() {
        if (hotels.isEmpty()) {
            messageType = MessageType.NORMAL;
            globalMessage = "[No Hotels in the system]";
            return;
        }

        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelNum = getIntInWhile("Which Hotel: ", 1, hotels.size()) - 1;

        ArrayList<Room> rooms = hotels.get(hotelNum).getAvilableRooms();

        for (int i = 0; i < rooms.size(); i++) {
            System.out.println((i + 1) + ". " + rooms.get(i));
        }

        int roomNum = getIntInWhile("Which Room: ", 1, rooms.size()) - 1;

        int newNum = getIntInWhile("New Number: ", 0, 100000);
        String newType = getStringInWhile("New Type: ");
        float newPrice = getFloatInWhile("New Price: ", 1, 100000);
        int newBed = getIntInWhile("New BedCount: ", 1, 10);

        hotels.get(hotelNum).editRoom(roomNum, newNum, newType, newPrice, newBed);

        messageType = MessageType.SUCCESS;
        globalMessage = "[Room Edited]";
    }

    private static void editCustomer() {
        if (customers.isEmpty()) {
            messageType = MessageType.NORMAL;
            globalMessage = "[No Customers yet]";
        }

        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i));
        }

        
        int customerNum = getIntInWhile("Which Customer: ", 1, customers.size()) - 1;


    }

    private static void payBooking() {
        if (bookings.isEmpty()) {
            messageType = MessageType.NORMAL;
            globalMessage = "[No Bookings yet]";
        }

        for (int i = 0; i < bookings.size(); i++) {
            System.out.println((i + 1) + ". " + bookings.get(i));
        }


        int bookingNum = getIntInWhile("Which Booking: ", 1, bookings.size()) - 1;

        bookings.get(bookingNum).pay();

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(bookings.get(bookingNum).getCustomer().getName())) {
                customers.get(i).addPoint();
                break;
            }
        }

        messageType = MessageType.SUCCESS;
        globalMessage = "[Booking Payed]";
    }
    private static void editMenu() {
        outer: while (true) {
            printGlobalMessage();

            // Printout the menu
            for (int i = 0; i < editMenu.length; i++) {
                System.out.println((i + 1)+ ".\t" + editMenu[i]);
            }

            System.out.print(">> ");
            int input = intScanner.nextInt();
            clearScreen();

            switch (input) {
                case 1:
                    editHotel();
                    clearScreen();
                    break;

                case 2:
                    editStaff();
                    clearScreen();
                    break;

                case 3:
                    editRoom();
                    clearScreen();
                    break;

                case 4:
                    editCustomer();
                    clearScreen();
                    break;

                case 5:
                    payBooking();
                    clearScreen();
                    break;

                case 6:
                    return;

                default:
                    globalMessage = "[out of range option]";
                    messageType = MessageType.ERROR;
                    clearScreen();
            }
        }
    }

    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            oos.writeObject(hotels);
            oos.writeObject(customers);
            oos.writeObject(bookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
            hotels = (ArrayList<Hotel>) ois.readObject();
            customers = (ArrayList<Customer>) ois.readObject();
            bookings = (ArrayList<Booking>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}