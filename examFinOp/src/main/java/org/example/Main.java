package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить транзакцию");
            System.out.println("2. Показать все транзакции");
            System.out.println("3. Рассчитать и вывести текущий баланс");
            System.out.println("4. Показать все транзакции определенного типа");
            System.out.println("5. Выход");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите дату: ");
                    String Date = readDate(scanner);
                    System.out.print("Введите описание: ");
                    String Description = scanner.nextLine();

                    if (Description.isEmpty()) {
                        System.out.println("Описание не может быть пустым.");
                        return;
                    }

                    System.out.print("Введите сумму: ");
                    double Amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Введите тип (Доход/Расход): ");
                    String type = scanner.nextLine();

                    if (!type.equals("Доход") && !type.equals("Расход")) {
                        System.out.println("Неверный тип. Должен быть 'Доход' или 'Расход'.");
                        break;
                    }

                    transactions.add(new Transaction(Date, Description, Amount, type));
                    System.out.println("Транзакция добавлена");
                    break;

                case 2:
                    if (transactions.isEmpty()) {
                        System.out.println("Нет транзакций.");
                    } else {
                        System.out.println("Все транзакции:");
                        for (Transaction t : transactions) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 3:
                    double Balance = 0.0;
                    for (Transaction t : transactions) {
                        if (t.getType().equals("Доход")) {
                            Balance += t.getAmount();
                        } else if (t.getType().equals("Расход")) {
                            Balance -= t.getAmount();
                        }
                    }
                    System.out.println("Текущий баланс: " + Balance);
                    break;

                case 4:
                    System.out.print("Введите тип (Доход/Расход): ");
                    String filterType = scanner.nextLine();

                    if (!filterType.equals("Доход") && !filterType.equals("Расход")) {
                        System.out.println("Неверный тип. Должен быть 'Доход' или 'Расход'.");
                        break;
                    }

                    boolean found = false;
                    System.out.println("Транзакции типа '" + filterType + "':");
                    for (Transaction t : transactions) {
                        if (t.getType().equals(filterType)) {
                            System.out.println(t);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Нет транзакций этого типа.");
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Выход из программы.");
                    break;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова: ");
                    break;
            }
        }
        scanner.close();
    }
    private static String readDate(Scanner sc) {
        while (true) {
            String date = sc.nextLine().trim();
            if (date.matches("\\d{2}-\\d{2}")) {
                try {
                    int month = Integer.parseInt(date.substring(0, 3));
                    int day = Integer.parseInt(date.substring(4));

                    if (month >= 1 && month <= 12 && day >= 1 && day <= 31) {
                        return date;
                    }
                } catch (Exception ignored) {}
            }
            System.out.print("Неверный формат даты. Используйте мм-дд: ");
        }
    }
}