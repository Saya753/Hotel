package People;

import java.io.Serial;
import java.io.Serializable;

public class Customer extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private ContactInfo contactInfo;
    private int royaltyPoints;

    public Customer(String name, ContactInfo contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.royaltyPoints = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPoint() { this.royaltyPoints++; }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone=" + contactInfo.getPhone() +
                ", email=" + contactInfo.getEmail() +
                ", royaltyPoints=" + royaltyPoints +
                '}';
    }
}
