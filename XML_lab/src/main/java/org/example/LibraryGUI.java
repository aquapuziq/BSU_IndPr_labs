package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryGUI::new);
    }
}