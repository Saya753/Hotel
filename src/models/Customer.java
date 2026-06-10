package models;

import utils.FileHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    // Attributes
    private String name; // نام
    private String contactInfo; // اطلاعات تماس
    private String email;
    private String phone; // شماره تلفن
    private int loyaltyPoints; // امتیثاز
    private List<Booking> bookingHistory; // رزروهای مشتری
    private List<Customer> customers;

    // Constructor
    public Customer(String name, String contactInfo, String email, String phone) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.email = email;
        this.phone =phone;
        this.loyaltyPoints = 0; // مقدار پیش فرض
        this.bookingHistory = new ArrayList<>();
        this.customers = FileHandler.readFromFile("customers.dat");
        if (this.customers == null) {
            this.customers = new ArrayList<>(); // اگر فایل خالی بود لیست جدید بسازه
        }
    }

    // Getter , Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }

    public void setBookingHistory(List<Booking> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    // اضافه کردن رزرو به تاریخچه رزروهای مشتری
    public void addBooking(Booking booking) {
        bookingHistory.add(booking);
        System.out.println("Booking added to " + name + "'s booking history.");
    }

    // نمایش تاریخچه رزروهای مشتری
    public void viewBookingHistory() {
        System.out.println("Booking History");
        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings found!");
        } else {
            for (Booking booking : bookingHistory) {
                System.out.println(booking.getBookingInfo());
            }
        }
    }

    // نظرات
    public void addReview(Hotel hotel, double review) {
        if (review >= 0 && review <= 5) {
            hotel.addReview(review);
            System.out.println(name + " added a review: " + review);
        } else {
            System.out.println("Invalid review. Must be between 0 and 5.");
        }
    }

    // اضافه کردن امتیاز
    public void addLoyaltyPoints(int points) {
        if (points > 0) {
            loyaltyPoints += points;
            System.out.println(points + " loyalty points added to " + name + ". ");
        } else {
            System.out.println("Points must be greater than 0.");
        }
    }

    // امتیاز دادن به مشتری بر اساس نام
    public void addLoyaltyPointsToCustomer(String name, int points) {
        Customer customer = findCustomerByName(name);
        if (customer != null) {
            customer.addLoyaltyPoints(points);
        }
    }

    // افزودن مشتری
    public void addCustomer(Customer customer) {
        customers.add(customer);
        saveToFile();
        System.out.println("Customer " + customer.getName() + " added successfully.");
    }

    // پیدا کردن مشتری بر اسای نام
    public Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            } else {
                System.out.println("Customer " + name + " not found.");
            }
        }
        return null;
    }

    // حذف مشتری بر اساس نام
    public void removeCustomerByName(String name) {
        Customer customer = findCustomerByName(name);
        if (customer != null) {
            customers.remove(customer);
            saveToFile();
            System.out.println("Customer removed successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    // نمایش اطلاعات مشتری
    public String getCustomerInfo() {
        return "Customer Name: " + name
                + " >>> " + "ContactInfo: " + contactInfo
                + " >>> " + "Email: " + email
                + " >>> " + "Phone Number: " + phone
                + " >>> " + "Loyalty Points: " + loyaltyPoints;
    }

    // نمایش تمام مشتریان
    public void showAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer.getCustomerInfo());
                System.out.println("---------------------------------");
            }
        }
    }

    // ویرایش اطلاعات مشتری
    public void updateCustomerDetails(String name, String newContactInfo, String newEmail, String newPhone) {
        Customer customer = findCustomerByName(name);
        if (customer != null) {
            if (newContactInfo != null && !newContactInfo.isBlank()) {
                customer.setContactInfo(newContactInfo);
            }

            if (newEmail != null && !newEmail.isBlank()) {
                customer.setEmail(newEmail);
            }

            if (newPhone != null && !newPhone.isBlank()) {
                customer.setPhone(newPhone);
            }
            saveToFile();
            System.out.println("Customer details updated successfully.");
        }
    }

    // ذخیره مشتریان در فایل
    private void saveToFile() {
        FileHandler.writeToFile("customers.dat", customers);
        System.out.println("Customer data saved to file.");
    }
}
