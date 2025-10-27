package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

class InvalidFileName extends Exception {
    public InvalidFileName(String message) {
        super(message);
    }
}

public class SetOperation {
    public static void initialization() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имена исходных файлов:");

        String file1 = in.nextLine();
        String file2 = in.nextLine();
        File f1 = new File(file1);
        File f2 = new File(file2);
        try {
            String invalidChars = "\"/:><\\?*";

            for (char c : invalidChars.toCharArray()) {
                if (file1.indexOf(c) >= 0) {
                    throw new InvalidFileName("Введеное имя " + file1 + " содержит недопустимый элемент: " + c);
                }
                if (file2.indexOf(c) >= 0) {
                    throw new InvalidFileName("Введеное имя " + file2 + " содержит недопустимый элемент: " + c);
                }
            }
            if (!f1.exists() || !f2.exists()) {
                System.out.println("Один из файлов не найден");
            }
            if (!file1.endsWith(".txt") || !file2.endsWith(".txt")) {
                throw new InvalidFileName("Файлы должны иметь расширение .txt");
            }
            if (file1.trim().isEmpty() || file2.trim().isEmpty()) {
                throw new InvalidFileName("Имя файла не может быть пустым.");
            }
        } catch (InvalidFileName e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        List<Student> list1 = readStudents(f1);
        List<Student> list2 = readStudents(f2);

        if (list1 == null || list2 == null) {
            System.err.println("Один из файлов пуст");
            return;
        }

    }

    public static List<Student> readStudents(File file) {
        List<Student> students = new ArrayList<>();

        if (!file.exists()) {
            System.err.println("Файл " + file + " не найден.");
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    students.add(Student.fromString(line));
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + file + ": " + e.getMessage());
            return null;
        }
        return students;
    }
}