package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortVisual extends JPanel {

    private int[] array;
    private int arraySize = 50;
    private int delay = 200;

    public SortVisual() {
        generateArray();
    }

    public void generateArray() {
        Random rand = new Random();
        array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = rand.nextInt(300) + 10;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int barW = width / arraySize;

        for (int i = 0; i < array.length; i++) {
            int x = i * barW;
            int barH = array[i];

            g.setColor(Color.GREEN);
            g.fillRect(x, height - barH, barW - 2, barH);
        }
    }

    private void pause() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void bubbleSort() {
        new Thread(() -> {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                    repaint();
                    pause();
                }
            }
        }).start();
    }

    public void selectionSort() {
        new Thread(() -> {
            for (int i = 0; i < array.length; i++) {
                int minIndex = i;

                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }

                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;

                repaint();
                pause();
            }
        }).start();
    }

    public void insertionSort() {
        new Thread(() -> {
            for (int i = 1; i < array.length; i++) {
                int key = array[i];
                int j = i - 1;

                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j--;
                    repaint();
                    pause();
                }
                array[j + 1] = key;
                repaint();
                pause();
            }
        }).start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Сортировки");
        SortVisual visualizer = new SortVisual();

        JButton bBubble = new JButton("Сортировка пузырьком");
        JButton bSelection = new JButton("Сортировка выбором");
        JButton bInsertion = new JButton("Сортировка вставками");
        JButton bGenerate = new JButton("Новый массив");

        bBubble.addActionListener(e -> visualizer.bubbleSort());
        bSelection.addActionListener(e -> visualizer.selectionSort());
        bInsertion.addActionListener(e -> visualizer.insertionSort());
        bGenerate.addActionListener(e -> visualizer.generateArray());

        JPanel controls = new JPanel();
        controls.add(bBubble);
        controls.add(bSelection);
        controls.add(bInsertion);
        controls.add(bGenerate);

        frame.setLayout(new BorderLayout());
        frame.add(visualizer, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);

        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
