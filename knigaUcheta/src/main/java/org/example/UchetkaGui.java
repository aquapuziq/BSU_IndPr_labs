package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.io.FileReader;
import java.util.List;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.BorderLayout;

public class UchetkaGui extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private List<Zachetka> students;

    public UchetkaGui() {
        setTitle("Табель успеваемости ФПМИ");
        setSize(677, 600);
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
        JScrollPane pane = new JScrollPane(table);
        add(pane, BorderLayout.CENTER);

        model.addColumn("ФИО");
        model.addColumn("Курс");
        model.addColumn("Группа");
        model.addColumn("Сессия");
        model.addColumn("Предмет");
        model.addColumn("Тип");
        model.addColumn("Оценка");

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(180);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(50);

        JScrollPane p = new JScrollPane(table);
        add(p, BorderLayout.CENTER);

        loadButton.addActionListener(e -> loadJSON());
        greatStButton.addActionListener(e -> showGreatSt());
        exportButton.addActionListener(e -> exportGreatSt());
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
    private void showGreatSt() {
        List<Zachetka> greatStudents = new ArrayList<>();
        for (Zachetka s : students) if (s.isGreatStudent()) greatStudents.add(s);
        fillTable(greatStudents);
    }


    private void exportGreatSt() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))) {
            for (Zachetka s : students) {
                if (!s.isGreatStudent()) continue;


                for (Zachetka.SessionRecord session : s.getSessions()) {
                    for (Zachetka.SessionRecord.Subject sub : session.getSubjects()) {
                        if (sub.getType().equals("экзамен")) {
                            pw.printf("%s, Курс: %d, Группа: %s, Сессия: %d, Предмет: %s, Оценка: %d%n",
                                    s.getFullName(),
                                    s.getCourse(),
                                    s.getGroup(),
                                    session.getSessionNumber(),
                                    sub.getName(),
                                    sub.getGrade());
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Файл output.txt успешно сохранён");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка сохранения файла: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UchetkaGui().setVisible(true));
    }
}
