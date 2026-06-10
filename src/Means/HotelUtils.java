package Means;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HotelUtils {
    public static String currentDateTime() {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return currentDate.format(formatter);
    }
}
