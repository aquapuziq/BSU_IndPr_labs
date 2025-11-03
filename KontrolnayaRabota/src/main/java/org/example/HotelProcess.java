package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class HotelProcess {
    private static List<Hotel> hotels = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    public static void initialization() {
        loadHotels("hotel.txt");
        while (true) {
            System.out.println("1. Показать все отели (сортировка по городам и звездам)");
            System.out.println("2. Найти отели по городу");
            System.out.println("3. Найти города по названию отеля");
            try {
                int choice = in.nextInt();
                in.nextLine();
                switch (choice) {
                    case 1:
                        showAllHotelsSorted();
                        break;
                    case 2:
                        findHotelsByCity();
                        break;
                    case 3:
                        findCitiesByHotelName();
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число от 1 до 3.");
                in.nextLine();
            }
        }
    }

    private static void loadHotels(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    try {
                        Hotel hotel = Hotel.readInfo(line);
                        hotels.add(hotel);
                    } catch (Exception e) {
                        System.out.println("Ошибка при чтении строки: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public static void showAllHotelsSorted() {
        if (hotels.isEmpty()) {
            System.out.println("Список отелей пуст.");
            return;
        }
        Comparator<Hotel> hotelComparator = Comparator
                .comparing(Hotel::getTown)
                .thenComparing(Comparator.comparing(Hotel::getZvezda).reversed());
        List<Hotel> sortedHotels = hotels.stream()
                .sorted(hotelComparator)
                .collect(Collectors.toList());
        System.out.println("\nВсе отели: ");
        System.out.println("Город\tНазвание\tЗвезды");
        String currentTown = "";
        for (Hotel hotel : sortedHotels) {
            if (!hotel.getTown().equals(currentTown)) {
                currentTown = hotel.getTown();
                System.out.println("\n" + currentTown);
            }
            System.out.println(hotel.getTown() + " " + hotel.getName() + " " + hotel.getZvezda());
        }
    }

    public static void findHotelsByCity() {
        System.out.print("Введите название города: ");
        String cityName = in.nextLine().trim();
        findHotelsByCity(cityName);
    }

    public static void findHotelsByCity(String cityName) {
        List<Hotel> cityHotels = hotels.stream()
                .filter(hotel -> hotel.getTown().equalsIgnoreCase(cityName))
                .sorted(Comparator.comparing(Hotel::getZvezda).reversed())
                .collect(Collectors.toList());
        if (cityHotels.isEmpty()) {
            System.out.println("Ошибка");
        } else {
            System.out.println("\nОтели в городе " + cityName + ":");
            for (Hotel hotel : cityHotels) {
                System.out.println(hotel.getName() + ":");
                System.out.println("звезд: " + hotel.getZvezda());
            }
        }
    }

    public static void findCitiesByHotelName() {
        System.out.print("Введите название отеля: ");
        String hotelName = in.nextLine().trim();
        findCitiesByHotelName(hotelName);
    }

    public static void findCitiesByHotelName(String hotelName) {
        Set<String> cities = hotels.stream()
                .filter(hotel -> hotel.getName().equalsIgnoreCase(hotelName))
                .map(Hotel::getTown)
                .collect(Collectors.toCollection(TreeSet::new));
        if (cities.isEmpty()) {
            System.out.println("Отель с названием '" + hotelName + "' не найден.");
        } else {
            System.out.println("\nГорода с отелем '" + hotelName + ":");
            cities.forEach(System.out::println);
            System.out.println("Город\tЗвезды");
            hotels.stream()
                    .filter(hotel -> hotel.getName().equalsIgnoreCase(hotelName))
                    .sorted(Comparator.comparing(Hotel::getTown))
                    .forEach(hotel -> System.out.println(hotel.getTown() + " " + hotel.getZvezda()));
        }
    }

    public static List<Hotel> getHotels() {
        return new ArrayList<>(hotels);
    }

    public static int getHotelsCount() {
        return hotels.size();
    }
}