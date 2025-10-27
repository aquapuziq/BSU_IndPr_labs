package org.example;

import java.util.Objects;

public class Student {
    long num; /* номер зачетки */
    String name; /* имя студента */
    int group; /* номер группы */
    double grade; /* средний бал */

    public Student(long num, String name, int group, double grade) {
        this.num = num;
        this.name = name;
        this.group = group;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return num + " " + name + " " + group + " " + grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return num == s.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }

    public static Student fromString(String line) {
        String[] parts = line.split(" ");
        return new Student(
                Long.parseLong(parts[0].trim()),
                parts[1].trim(),
                Integer.parseInt(parts[2].trim()),
                Double.parseDouble(parts[3].trim())
        );
    }
}
