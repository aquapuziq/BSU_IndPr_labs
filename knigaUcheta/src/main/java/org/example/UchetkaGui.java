package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.io.FileReader;
import java.util.List;

public class UchetkaGui extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private List<Zachetka> students;

    public UchetkaGui() {
        setTitle("Табель успеваемости ФПМИ");
        setSize(1000, 1000);
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

        loadButton.addActionListener(e -> loadJSON());
    }

    private void loadJSON() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            java.lang.reflect.Type listType = new TypeToken<List<Zachetka>>() {}.getType();

            students = gson.fromJson(new FileReader("kniga.json"), listType);
            fillTable(students);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка загрузки JSON: " + ex.getMessage());
        }
    }

    private void fillTable(List<Zachetka> list) {
        model.setRowCount(0);

        for (Zachetka s : list) {
            for (Zachetka.SessionRecord session : s.getSessions()) {
                for (Zachetka.SessionRecord.Subject sub : session.getSubjects()) {
                    model.addRow(new Object[]{
                            s.getFullName(),
                            s.getCourse(),
                            s.getGroup(),
                            session.getSessionNumber(),
                            sub.getName(),
                            sub.getType(),
                            sub.getGrade()
                    });
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UchetkaGui().setVisible(true));
    }
}
