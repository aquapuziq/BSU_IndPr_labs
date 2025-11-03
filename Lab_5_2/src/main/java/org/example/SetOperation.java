package org.example;
import java.io.*;
import java.util.*;

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

        System.out.println("Введите имя файла для записи результата:");
        String fileOut = in.nextLine();
        File fOut = new File(fileOut);
        try {
            String invalidChars = "\"/:><\\?*";

            for (char c : invalidChars.toCharArray()) {
                if (file1.indexOf(c) >= 0) {
                    throw new InvalidFileName("Введеное имя " + file1 + " содержит недопустимый элемент: " + c);
                }
                if (file2.indexOf(c) >= 0) {
                    throw new InvalidFileName("Введеное имя " + file2 + " содержит недопустимый элемент: " + c);
                }
                if (fileOut.indexOf(c) >= 0) {
                    throw new InvalidFileName("Введеное имя " + fileOut + " содержит недопустимый элемент: " + c);
                }
            }
            if (!f1.exists() || !f2.exists()) {
                System.out.println("Один из входных файлов не найден");
            }
            if (!file1.endsWith(".txt") || !file2.endsWith(".txt") ||!fileOut.endsWith(".txt")) {
                throw new InvalidFileName("Файлы должны иметь расширение .txt");
            }
            if (file1.trim().isEmpty() || file2.trim().isEmpty() || fileOut.trim().isEmpty()) {
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

        boolean status = true;
        while (status) {
            System.out.println("\nВыберите операцию:");
            System.out.println("1 — Объединение");
            System.out.println("2 — Пересечение");
            System.out.println("3 — Разность");

            String operation = in.nextLine();
            List<Student> result = new ArrayList<>();
            switch (operation) {
                case "1":
                    result = union(list1, list2);
                    saveResult(result, fileOut);
                    System.out.println("Операция объединения завершена");
                    status = false;
                    break;
                case "2":
                    result = intersection(list1, list2);
                    saveResult(result, fileOut);
                    System.out.println("Операция пересечения завершена.");
                    status = false;
                    break;
                case "3":
                    result = difference(list1, list2);
                    saveResult(result, fileOut);
                    System.out.println("Операция разности завершена.");
                    status = false;
                    break;
                default:
                    System.out.println("Некорректный выбор операции, попробуйте снова");
            }
        }
    }

    private static List<Student> readStudents(File file) {
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

    private static List<Student> union(List<Student> a, List<Student> b) {
        Set<Student> result = new HashSet<>(a);
        result.addAll(b);
        return new ArrayList<>(result);
    }

    public static List<Student> intersection(List<Student> a, List<Student> b) {
        Set<Student> setA = new HashSet<>(a);
        setA.retainAll(b);
        return new ArrayList<>(setA);
    }

    public static List<Student> difference(List<Student> a, List<Student> b) {
        Set<Student> setA = new HashSet<>(a);
        setA.removeAll(b);
        return new ArrayList<>(setA);
    }

    private static void saveResult(List<Student> students, String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                pw.println(s.toString());
            }
            System.out.println("Результат сохранён в файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

}