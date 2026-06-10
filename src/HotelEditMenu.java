import models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HotelEditMenu {
    private static List<Hotel> hotels = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exist = false;

        while (!exist) {
            System.out.println("*** Hotel Edit System ***");
            System.out.println("1.Edit Hotel Details");
            System.out.println("2.Edit Room Details");
            System.out.println("3.Edit Staff Details");
            System.out.println("4.Edit Customer Details");
            System.out.println("5.Edit Booking Details");
            System.out.println("6.Back to Main Menu");
            System.out.println("Choose an option: ");

            int editChoice = scanner.nextInt();

            switch (editChoice) {
                case 1 -> { // Edit Hotel Details
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available. Add a hotel first.");
                    } else {
                        System.out.println("Select hotel: ");
                        for (int i = 0; i < hotels.size(); i++) {
                            System.out.println((i + 1) + ". Hotel Name: " + hotels.get(i).getName());
                        }

                        int hotelIndex = scanner.nextInt() - 1;
                        if (hotelIndex < 0 || hotelIndex >= hotels.size()) {
                            System.out.println("Invalid hotel selection.");
                        } else {
                            Hotel selectedHotel = hotels.get(hotelIndex);

                            // ویرایش اسم هتل
                            System.out.println("Enter hotel new name: ");
                            String newName = scanner.nextLine();
                            if (!newName.isBlank()) {
                                selectedHotel.setName(newName);
                            }

                            // ویرایش مکان هتل
                            System.out.println("Enter hotel new location: ");
                            String newLocation = scanner.nextLine();
                            if (!newLocation.isBlank()) {
                                selectedHotel.setLocation(newLocation);
                            }

                            // ویرایش امتیاز هتل
                            System.out.println("Enter hotel new rating: ");
                            String newRating = scanner.nextLine();
                            if (!newRating.isBlank()) {
                                double number = Double.valueOf(newRating);
                                selectedHotel.setRating(number);
                            }

                            System.out.println("Hotel details updated successfully.");
                        }
                    }
                }
                case 2 -> { // Edit Room Details
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available. Add a hotel first.");
                    } else {
                        System.out.println("Select hotel: ");
                        for (int i = 0; i < hotels.size(); i++) {
                            System.out.println((i + 1) + ". Hotel Name: " + hotels.get(i).getName());
                        }
                        int hotelIndex = scanner.nextInt() - 1;
                        if (hotelIndex < 0 || hotelIndex >= hotels.size()) {
                            System.out.println("Invalid hotel selection.");
                        }

                        Hotel selectedHotel = hotels.get(hotelIndex);

                        System.out.println("Enter room number to edit: ");
                        int roomNumber = scanner.nextInt();

                        Room room = selectedHotel.getRooms(roomNumber);
                        if (room == null) {
                            System.out.println("Room not found.");
                        }

                        // تغییر قیمت اتاق
                        System.out.println("Enter new room price: ");
                        String newPriceInput = scanner.nextLine();
                        if (!newPriceInput.isBlank()) {
                            double newPrice = Double.parseDouble(newPriceInput);
                            room.setPrice(newPrice);
                        }

                        // تغییر نوع اتاق
                        System.out.println("Enter new room type: ");
                        String newType = scanner.nextLine();
                        if (!newType.isBlank()) {
                            room.setType(newType);
                        }

                        // ویرایش تعداد تخت های اتاق
                        System.out.println("Enter new room bed count: ");
                        String newBedCount = scanner.nextLine();
                        if (!newBedCount.isBlank()) {
                            int number = Integer.parseInt(newBedCount);
                            room.setBedCount(number);
                        }

                        System.out.println("Room details updated successfully.");
                    }
                }
                case 3 -> { // Edit Staff Details
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available. Add a hotel first.");
                    } else {
                        System.out.println("Select hotel: ");
                        for (int i = 0; i < hotels.size(); i++) {
                            System.out.println((i + 1) + ". Hotel Name: " + hotels.get(i).getName());
                        }

                        int hotelIndex = scanner.nextInt() - 1;

                        Hotel selectedHotel = hotels.get(hotelIndex);

                        // ویرایش ایدی کارمندان
                        System.out.println("Enter staff ID to edit: ");
                        int staffID = scanner.nextInt() -1;
                        Staff staff = selectedHotel.getStaff(staffID);
                        if (staff == null) {
                            System.out.println("Staff not found.");
                        }

                        // ویرایش اسم کارمند
                        System.out.println("Enter staff new name: ");
                        String newName = scanner.nextLine();
                        if (!newName.isBlank()) {
                            staff.setName(newName);
                        }

                        // ویرایش مقام کارمند
                        System.out.println("Enter staff new position: ");
                        String newPosition = scanner.nextLine();
                        if (!newPosition.isBlank()) {
                            staff.setPosition(newPosition);
                        }

                        System.out.println("Staff details updated successfully.");
                    }
                }
                case 4 -> { // Edit Customer Details
                    if (customers.isEmpty()) {
                        System.out.println("No customer available to edit.");
                    } else {
                        System.out.println("Select a customer to edit: ");
                        for (int i = 0; i < customers.size(); i++) {
                            System.out.println((i + 1) + ". " + customers.get(i).getName());
                        }

                        int customerIndex = scanner.nextInt() - 1;
                        if (customerIndex < 0 || customerIndex >= customers.size()) {
                            System.out.println("Invalid customer selection.");
                        }

                        Customer selectedCustomer = customers.get(customerIndex);

                        // ویرایش نام مشتری
                        System.out.println("Enter customer new name: ");
                        String newName = scanner.nextLine();
                        if (!newName.isBlank()) {
                            selectedCustomer.setName(newName);
                        }

                        // ویرایش مشخصات مشتری
                        System.out.println("Enter customer new contactInfo: ");
                        String newContactInfo = scanner.nextLine();
                        if (!newContactInfo.isBlank()) {
                            selectedCustomer.setContactInfo(newContactInfo);
                        }

                        // ویرایش ایمیل مشتری
                        System.out.println("Enter customer new email: ");
                        String newEmail = scanner.nextLine();
                        if (!newEmail.isBlank()) {
                            selectedCustomer.setEmail(newEmail);
                        }

                        // ویرایش شماره تماس مشتری
                        System.out.println("Enter customer new phone: ");
                        String newPhone = scanner.nextLine();
                        if (!newPhone.isBlank()) {
                            selectedCustomer.setPhone(newPhone);
                        }

                        // ویرایش امتیاز مشتری
                        System.out.println("Enter customer new loyalty points: ");
                        String newLoyaltyPoints = scanner.nextLine();
                        if (!newLoyaltyPoints.isBlank());{
                            int number = Integer.valueOf(newLoyaltyPoints);
                            selectedCustomer.setLoyaltyPoints(number);
                        }

                        System.out.println("Customer details updated successfully.");
                    }
                }
                case 5 -> { // Edit Booking Details
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings available to edit.");
                    } else {
                        System.out.println("Select a booking to edit: ");
                        for (int i = 0; i < bookings.size(); i++) {
                            System.out.println((i +1) + ". Booking ID: " + bookings.get(i).getID());
                        }

                        int bookingIndex = scanner.nextInt() - 1;

                        if (bookingIndex < 0 || bookingIndex >= bookings.size()) {
                            System.out.println("Invalid booking selection.");
                        }

                        Booking selectedBooking = bookings.get(bookingIndex);

                        // ویراش تاریخ ثبت رزرو
                        System.out.println("Enter new check-in date: ");
                        String newCheckIn = scanner.nextLine();
                        if (!newCheckIn.isBlank()) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");

                            try {
                                Date date = formatter.parse(newCheckIn);
                                selectedBooking.setCheckInDate(date);
                            } catch (ParseException e) {
                                System.out.println("Invalid date format. Error: " + e.getMessage());
                            }
                        }

                        // ویراش تاریخ تخلیه رزرو
                        System.out.println("Enter new check-out date: ");
                        String newCheckOut = scanner.nextLine();
                        if (!newCheckOut.isBlank()) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");

                            try {
                                Date date = formatter.parse(newCheckOut);
                                selectedBooking.setCheckInDate(date);
                            } catch (ParseException e) {
                                System.out.println("Invalid date format. Error: " + e.getMessage());
                            }
                        }

                        System.out.println("Booking details updated successfully. ");
                    }
                }
                case 6 -> { // Back to Main Menu
                    new HotelBookingMenu();
                }
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }
}