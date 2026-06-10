import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HotelBookingMenu {
    private static List<Hotel> hotels = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exist = false;



        while (!exist) {
            System.out.println("*** Hotel Management System ***");
            System.out.println("1.Add Hotel");
            System.out.println("2.Add Room");
            System.out.println("3.Add Staff");
            System.out.println("4.Add Customer");
            System.out.println("5.Book a Room");
            System.out.println("6.Show Hotels");
            System.out.println("7.Show Customers");
            System.out.println("8.Show Bookings");
            System.out.println("9.Show Payments");
            System.out.println("10.Remove Hotel");
            System.out.println("11.Remove Room");
            System.out.println("12.Remove Staff");
            System.out.println("13.Remove Customer");
            System.out.println("14.Remove Booking");
            System.out.println("15.Open Edit Menu");
            System.out.println("16.EXIT");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> { // add hotel
                    System.out.println("Enter hotel name: ");
                    String hotelName = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Enter hotel location: ");
                    String hotelLocation = scanner.nextLine();
                    hotels.add(new Hotel(hotelName, hotelLocation));
                    System.out.println("Hotel added successfully.");
                }
                case 2 -> { // add room
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available. Add a hotel first.");
                    } else {
                        System.out.println("Select a hotel: ");
                        for (int i = 0; i < hotels.size(); i++) {
                            System.out.println((i + 1) + ". " + hotels.get(i).getName());
                        }
                        int hotelIndex = scanner.nextInt() - 1;
                        scanner.nextLine(); // خط جدید
                        if (hotelIndex < 0 || hotelIndex >= hotels.size()) {
                            System.out.println("Invalid hotel selection!");
                        } else {
                            System.out.println("Enter room number: ");
                            int roomNumber = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter price per night: ");
                            double price = scanner.nextDouble();
                            scanner.nextLine(); // خط جدید
                            System.out.println("Enter room type: ");
                            String type = scanner.nextLine();
                            scanner.nextLine();
                            System.out.println("Enter bed count: ");
                            int bedCount = scanner.nextInt();
                            hotels.get(hotelIndex).addRoom(new Room(roomNumber, price, true, type, bedCount));
                            System.out.println("Room added successfully");
                        }
                    }
                }
                case 3 -> { // add staff
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available. Add a hotel first.");
                    } else {
                        System.out.println("Select a hotel: ");
                        for (int i = 0; i < hotels.size(); i++) {
                            System.out.println((i + 1) + ". " + hotels.get(i).getName());
                        }
                        int hotelIndex = scanner.nextInt() - 1;
                        scanner.nextLine(); // خط جدید
                        if (hotelIndex < 0 || hotelIndex >= hotels.size()) {
                            System.out.println("Invalid hotel selection!");
                        } else {
                            System.out.println("Enter staff name: ");
                            String staffName = scanner.nextLine();
                            scanner.nextLine();
                            System.out.println("Enter position: ");
                            String position = scanner.nextLine();
                            scanner.nextLine();
                            System.out.println("Enter staff ID: ");
                            int ID = scanner.nextInt();
                            hotels.get(hotelIndex).addStaff(new Staff(staffName, position, ID));
                            System.out.println("Staff added successfully");
                        }
                    }
                }
                case 4 -> { // add customer
                    System.out.println("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Enter contactInfo: ");
                    String contactInfo = scanner.nextLine();
                    System.out.println("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.println("Enter phone: ");
                    String phone = scanner.nextLine();
                    customers.add(new Customer(customerName, contactInfo, email, phone));
                }
                case 5 -> { // book a room
                    if (hotels.isEmpty() || customers.isEmpty()) {
                        System.out.println("Hotels or Customers are not available. Add them first.");
                    } else {
                        System.out.println("Select a customer: ");
                        for (int i = 0; i < customers.size(); i++) {
                            System.out.println((i + 1) + ". " + customers.get(i).getName());
                        }
                        int customerIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        System.out.println("Select a hotel: ");
                        for (int i = 0; i < hotels.size(); i++) {
                            System.out.println((i + 1) + ". " + hotels.get(i).getName());
                        }
                        int hotelIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        ArrayList<Room> rooms = (ArrayList<Room>) hotels.get(hotelIndex).getAvailableRooms();
                        System.out.println("Enter room number to book: ");
                        int roomNumber = scanner.nextInt();
                        Hotel selectedHotel = hotels.get(hotelIndex);
                        Room room = selectedHotel.getRooms().get(roomNumber);
                        if (room == null || !room.isAvailable()) {
                            System.out.println("Room is not available.");
                        }
                        bookings.add(new Booking(1, 101, new Date(), new Date(), customers.get(customerIndex), rooms.get(roomNumber)));
                        room.reserve();
                        System.out.println("Room added successfully");
                    }
                }
                case 6 -> { // Show Hotels
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available");
                    } else {
                        for (Hotel hotel : hotels) {
                            System.out.println(hotel.getHotelInfo());
                        }
                    }
                }
                case 7 -> { // Show Customers
                    if (customers.isEmpty()) {
                        System.out.println("No customers available");
                    } else {
                        for (Customer customer : customers) {
                            System.out.println(customer.getCustomerInfo());
                        }
                    }
                }
                case 8 -> { // Show Bookings
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings available");
                    } else {
                        for (Booking booking : bookings) {
                            System.out.println(booking.getBookingInfo());
                        }
                    }
                }
                case 9 -> { // Show Payments
                    if (bookings.isEmpty()) {
                        System.out.println("No payments available. No bookings have been made.");
                    } else {
                        ArrayList<Booking> payments = new ArrayList<>();
                        for (Booking booking : bookings) {
                            System.out.println("Transaction ID: " + booking.getPaymentStatus());
                        }
                    }
                }
                case 10 -> { // Remove Hotel
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available to remove.");
                    } else {
                        System.out.println("Select hotel to remove: ");
                        for (int i = 0; i < hotels.size(); i++) {
                            System.out.println((i + 1) + ". Hotel Name: " + hotels.get(i).getName());
                        }
                        int hotelIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (hotelIndex < 0 || hotelIndex >= hotels.size()) {
                            System.out.println("Invalid hotel selection.");
                        } else {
                            hotels.remove(hotelIndex);
                            System.out.println("Hotel removed successfully.");
                        }
                    }
                }
                case 11 -> { // Remove Room
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available. Add a hotel first.");
                    } else {
                        System.out.println("Select hotel: ");
                        for (int i = 0; i < hotels.size(); i++) {
                            System.out.println((i + 1) + ". Hotel Name: " + hotels.get(i).getName());
                        }
                        int hotelIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        ArrayList<Room> rooms = (ArrayList<Room>) hotels.get(hotelIndex).getAvailableRooms();
                        if (hotelIndex < 0 || hotelIndex >= hotels.size()) {
                            System.out.println("Invalid hotel selection.");
                        } else {
                            Hotel selectedHotel = hotels.get(hotelIndex);
                            System.out.println("Enter room number to remove: ");
                            int roomNumber = scanner.nextInt();
                            if (!rooms.isEmpty()) {
                                hotels.get(hotelIndex).removeRoom(roomNumber);
                                System.out.println("Room removed successfully.");
                            } else {
                                System.out.println("Room not found or cannot be removed.");
                            }
                        }
                    }
                }
                case 12 -> { // Remove Staff
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available. Add a hotel first.");
                    } else {
                        System.out.println("Select hotel: ");
                        for (int i = 0; i < hotels.size(); i++) {
                            System.out.println((i + 1) + ". Hotel Name: " + hotels.get(i).getName());
                        }
                        int hotelIndex = scanner.nextInt() - 1;
                        Hotel selectedHotel = hotels.get(hotelIndex);
                        System.out.println("Enter staff ID to remove: ");
                        int staffID = scanner.nextInt();
                        ArrayList<Staff> staff = hotels.get(hotelIndex).getStaff(staffID);
                        if (!staff.isEmpty()) {
                            hotels.get(hotelIndex).removeStaff(staffID);
                            System.out.println("Staff removed successfully.");
                        } else {
                            System.out.println("Staff not found.");
                        }
                    }
                }
                case 13 -> { // Remove Customer
                    if (customers.isEmpty()) {
                        System.out.println("No Customers available to remove.");
                    } else {
                        System.out.println("Select a customer to remove: ");
                        for (int i = 0; i < customers.size(); i++) {
                            System.out.println((i+1) + ". Hotel Name: " + hotels.get(i).getName() + "Customer Name: " + customers.get(i).getName());
                        }
                        int customerIndex = scanner.nextInt() - 1;
                        if (customerIndex < 0 || customerIndex >= customers.size()) {
                            System.out.println("Invalid customer selection.");
                        } else {
                            customers.remove(customerIndex);
                            System.out.println("Customer removed successfully.");
                        }
                    }
                }
                case 14 -> { // Remove Booking
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings available to remove.");
                    } else {
                        System.out.println("Select a booking to remove: ");
                        for (int i = 0; i < bookings.size(); i++) {
                            System.out.println((i+1) + ". Booking ID: " + bookings.get(i).getID());
                        }
                        int bookingIndex = scanner.nextInt() - 1;
                        if (bookingIndex < 0 || bookingIndex >= bookings.size()) {
                            System.out.println("Invalid booking selection.");
                        } else {
                            bookings.remove(bookingIndex);
                            System.out.println("Booking removed successfully.");
                        }
                    }
                }
                case 15 -> { // Open Edit Menu
                    System.out.println("Open Edit Menu");
                    new HotelEditMenu();
                }
                case 16 -> { // EXIT
                    System.out.println("Thanks for using our system.");
                    exist = true;
                }
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
        saveData();
        scanner.close();
    }
    
    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            oos.writeObject(hotels);
            oos.writeObject(customers);
            oos.writeObject(bookings);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*public static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
            Object ArrayList = null;
            //hotels = (ArrayList<Hotel> ois.readObject());
           // customers = (ArrayList<Customer> ois.readObject());
           // bookings = (ArrayList<Booking> ois.readObject());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
