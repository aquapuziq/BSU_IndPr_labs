package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class LibraryGUI extends JFrame {
    private Library library;
    private JTable table;
    private DefaultTableModel model;

    public LibraryGUI() {
        library = new Library();
        boolean load = library.initLibrary("library.xml", "library.xsd");
        if (!load) {
            JOptionPane.showMessageDialog(this, "Ошибка содержания xml-файла", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setTitle("Библиотека");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.setColumnIdentifiers(new String[]{"ID", "Название", "Автор", "Год", "Цена", "Колличество", "В наличии", "Жанр"});

        add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Добавить книгу");
        JButton searchButton = new JButton("Поиск");
        JButton changePriceButton = new JButton("Изменить цену");
        JButton issueButton = new JButton("Выдать книгу");
        panel.add(addButton);
        panel.add(searchButton);
        panel.add(changePriceButton);
        panel.add(issueButton);
        add(panel, BorderLayout.SOUTH);

        refreshTable(library.getBooks());
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(40);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(75);
        columnModel.getColumn(6).setPreferredWidth(60);
        columnModel.getColumn(7).setPreferredWidth(100);

        addButton.addActionListener(e -> addBook());
        searchButton.addActionListener(e -> searchBook());
        changePriceButton.addActionListener(e -> changePrice());
        issueButton.addActionListener(e -> issueBook());

        setVisible(true);
    }private void refreshTable(java.util.List<Book> books) {
        model.setRowCount(0);
        for (Book b : books) {
            model.addRow(new Object[]{b.getId(), b.getTitle(), b.getAuthor(),
                    b.getYear(), b.getPrice(), b.getQuantity(), b.getInStock(), b.getCategory()});
        }
    }

    private void addBook() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Введите ID:"));
            String title = JOptionPane.showInputDialog("Введите название:");
            String author = JOptionPane.showInputDialog("Введите автора:");
            int year = Integer.parseInt(JOptionPane.showInputDialog("Введите год публикации:"));
            double price = Double.parseDouble(JOptionPane.showInputDialog("Введите цену:"));
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Введите колличество:"));
            int instock = Integer.parseInt(JOptionPane.showInputDialog("Введите колличество книг в наличии:"));
            String category = JOptionPane.showInputDialog("Введите жанр:");

            Book book = new Book(id, title, author, year, price, quantity, instock, category);
            library.addBook(book);
            refreshTable(library.getBooks());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка ввода!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchBook() {
        String[] options = {"Автор", "Год", "Жанр"};
        int choice = JOptionPane.showOptionDialog(this, "Искать по:", "Поиск", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        java.util.List<Book> result = new java.util.ArrayList<>();
        switch (choice) {
            case 0:
                String author = JOptionPane.showInputDialog("Введите автора:");
                result = library.searchByAuthor(author);
                break;
            case 1:
                int year = Integer.parseInt(JOptionPane.showInputDialog("Введите год:"));
                result = library.searchByYear(year);
                break;
            case 2:
                String category = JOptionPane.showInputDialog("Введите жанр:");
                result = library.searchByCategory(category);
                break;
        }
        refreshTable(result);
    }

    private void changePrice() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Введите ID книги:"));
            double price = Double.parseDouble(JOptionPane.showInputDialog("Введите новую цену:"));
            library.changePrice(id, price);
            refreshTable(library.getBooks());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Некорректный ввод", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void issueBook() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ввкдите ID книги для выдачи:"));
            boolean success = library.issueBook(id);
            if (!success) JOptionPane.showMessageDialog(this, "Нет книг в наличии или неверный ID");
            refreshTable(library.getBooks());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Некорректный ввод", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryGUI::new);
    }
}