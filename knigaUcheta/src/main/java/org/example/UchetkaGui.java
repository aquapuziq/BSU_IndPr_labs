package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.List;

public class UchetkaGui extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private List<Zachetka> students;

    public UchetkaGui() {
        setTitle("Табель успеваемости ФПМИ");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton loadButton = new JButton("Загрузить JSON");
        JButton greatStButton = new JButton("Показать отличников");
        JButton exportButton = new JButton("Экспорт в output.txt");

        JPanel top = new JPanel();
        top.add(loadButton);
        top.add(greatStButton);
        top.add(exportButton);
        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        add(pane, BorderLayout.CENTER);

        model.addColumn("ФИО");
        model.addColumn("Курс");
        model.addColumn("Группа");
        model.addColumn("Сессия");
        model.addColumn("Предмет");
        model.addColumn("Тип");
        model.addColumn("Оценка");

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UchetkaGui().setVisible(true));
    }
}
