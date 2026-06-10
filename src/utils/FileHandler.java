package utils;

import java.io.*;
import java.util.List;

public class FileHandler {
    // ذخیره یک لیس در فایل
    public static <T> void writeToFile(String fileName, List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data); // نوشتن لیست در فابل
            System.out.println("Data successfully written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // خواندن از فایل
    public static <T> List<T> readFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject(); // خواندن از فایل
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return null;
    }

    // خیره اطلاعات در فایل متنی
    public static void writeTextToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data); // نوشتن داده ها در فایل
            System.out.println("Text successfully written ti file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing text to file: " + e.getMessage());
        }
    }

    // خواندن اطلاعات از فایل متنی
    public static String readTextFromFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n"); // خواندن هر خط و افزودن به نتیجه
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading text from file: " + e.getMessage());
        }
        return data.toString();
    }
}
