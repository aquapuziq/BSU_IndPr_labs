package org.example;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private double price;
    private int quantity;
    private int inStock;
    private String category;

    public Book(int id, String title, String author, int year, double price, int
            quantity, int inStock, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.inStock = inStock;
        this.category = category;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getInStock() { return inStock; }
    public String getCategory() { return category; }

    public void setPrice(double price) { this.price = price; }
    public void setInStock(int instock) { this.inStock = instock; }
}
