package People;

import java.io.Serial;
import java.io.Serializable;

public class Staff extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int staffID;
    private String position;

    public Staff(int staffID, String name, String position) {
        this.staffID = staffID;
        this.name = name;
        this.position = position;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

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

    @Override
    public String toString() {
        return "Staff{" +
                "staffID=" + staffID +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
