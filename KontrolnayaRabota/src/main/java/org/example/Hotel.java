package org.example;

public class Hotel {
    String town;
    String name;
    int zvezda;

    public Hotel(String town, String name, int zvezda){
        this.town = town;
        this.name = name;
        this.zvezda = zvezda;
    }

    public String getTown() {
        return town;
    }

    public String getName() {
        return name;
    }

    public int getZvezda() {
        return zvezda;
    }

    @Override
    public String toString() {
        return town + " " + name + " " + zvezda;
    }

    public static Hotel readInfo(String line) {
        String[] parts = line.split(" ");
        return new Hotel(
                parts[0].trim(),
                parts[1].trim(),
                Integer.parseInt(parts[2].trim())
        );
    }
}
