package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Reader reader = new FileReader("kniga.json")) {

            Type listType = new TypeToken<List<Zachetka>>(){}.getType();
            List<Zachetka> students = gson.fromJson(reader, listType);

            List<Zachetka> excellentStudents = new ArrayList<>();

            for (Zachetka student : students) {
                if (student.isGreatStudent()) {
                    excellentStudents.add(student);
                }
            }

            try (PrintWriter writer = new PrintWriter("output.txt")) {

                for (Zachetka student : excellentStudents) {
                    for (Zachetka.SessionRecord session : student.getSessions()) {
                        for (Zachetka.SessionRecord.Subject subj : session.getSubjects()) {

                            if (subj.getType().equalsIgnoreCase("экзамен")) {

                                writer.printf(
                                        "%s, Курс: %d, Группа: %s, " +
                                                "Сессия: %d, Предмет: %s, Оценка: %d%n",
                                        student.getFullName(),
                                        student.getCourse(),
                                        student.getGroup(),
                                        session.getSessionNumber(),
                                        subj.getName(),
                                        subj.getGrade()
                                );
                            }
                        }
                    }
                }
            }

            System.out.println("Файл с отлитчниками успешно создан");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
