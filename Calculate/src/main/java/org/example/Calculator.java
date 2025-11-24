package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField display;
    private String nowInput = "";
    private double result = 0;
    private String lastOp = "";
    private boolean startNew = true;

    public Calculator() {
        setTitle("Калькулятор");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("TimesNewRoman", Font.ITALIC, 60));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 1, 1));

        JButton minus = new JButton("-");
        JButton plus = new JButton("+");
        JButton perc = new JButton("%");
        JButton div = new JButton("/");
        JButton mul = new JButton("*");
        JButton equal = new JButton("=");
        JButton clear = new JButton("C");

        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");

        JButton b0 = new JButton("0");
        JButton decimal = new JButton(".");

        JButton rightBr = new JButton(")");
        JButton leftBr = new JButton("(");

        buttonPanel.add(leftBr);
        buttonPanel.add(rightBr);
        buttonPanel.add(perc);
        buttonPanel.add(div);

        buttonPanel.add(b7);
        buttonPanel.add(b8);
        buttonPanel.add(b9);
        buttonPanel.add(mul);

        buttonPanel.add(b4);
        buttonPanel.add(b5);
        buttonPanel.add(b6);
        buttonPanel.add(minus);

        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(plus);

        buttonPanel.add(decimal);
        buttonPanel.add(b0);
        buttonPanel.add(clear);
        buttonPanel.add(equal);

        ActionListener numList = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String digit = button.getText();

                if (startNew) {
                    nowInput = digit;
                    startNew = false;
                } else {
                    nowInput += digit;
                }
                display.setText(nowInput);
            }
        };

        b0.addActionListener(numList);
        b1.addActionListener(numList);
        b2.addActionListener(numList);
        b3.addActionListener(numList);
        b4.addActionListener(numList);
        b5.addActionListener(numList);
        b6.addActionListener(numList);
        b7.addActionListener(numList);
        b8.addActionListener(numList);
        b9.addActionListener(numList);

        decimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startNew) {
                    nowInput = "0.";
                    startNew = false;
                } else if (!nowInput.contains(".")) {
                    nowInput += ".";
                }
                display.setText(nowInput);
            }
        });

        ActionListener opListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String operator = ((JButton) e.getSource()).getText();

                if (!nowInput.isEmpty() && !lastCharOp()) {
                    if (!lastOp.isEmpty()) {
                        calculate();
                    } else {
                        result = Double.parseDouble(nowInput);
                    }
                    lastOp = operator;
                    nowInput += " " + operator + " ";
                    display.setText(nowInput);
                    startNew = true;
                }
            }
        };

        plus.addActionListener(opListener);
        minus.addActionListener(opListener);
        mul.addActionListener(opListener);
        div.addActionListener(opListener);

        perc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nowInput.isEmpty()) {
                    try {
                        double value = Double.parseDouble(nowInput);
                        value = value / 100;
                        nowInput = String.valueOf(value);
                        display.setText(nowInput);
                    } catch (NumberFormatException ex) {
                        display.setText("Error");
                        resetCalculator();
                    }
                }
            }
        });

        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nowInput.isEmpty() && !lastOp.isEmpty()) {
                    calculateFunc();
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetCalculator();
                display.setText("0");
            }
        });

        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        display.setText("0");
    }

    private boolean lastCharOp() {
        if (nowInput.isEmpty()) return false;
        String lastChar = nowInput.substring(nowInput.length() - 1);
        return "+-*/".contains(lastChar);
    }

    private void calculateFunc() {
        try {
            String expression = nowInput.replaceAll("\\s+", "");
            String[] parts = expression.split("[+\\-*/]");

            if (parts.length < 2) return;

            double firstNum = Double.parseDouble(parts[0]);
            double secondNum = Double.parseDouble(parts[1]);
            String operator = extractOp(expression);

            double calculatedResult = performOp(firstNum, secondNum, operator);

            if (calculatedResult == (long) calculatedResult) {
                nowInput = String.valueOf((long) calculatedResult);
            } else {
                nowInput = String.valueOf(calculatedResult);
            }

            display.setText(nowInput);
            result = calculatedResult;
            lastOp = "";
            startNew = true;

        } catch (Exception ex) {
            display.setText("Error");
            resetCalculator();
        }
    }

    private String extractOp(String expression) {
        for (char c : expression.toCharArray()) {
            if ("+-*/".indexOf(c) != -1) {
                return String.valueOf(c);
            }
        }
        return "";
    }

    private double performOp(double a, double b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            default: return b;
        }
    }

    private void calculate() {
        if (nowInput.isEmpty() || lastOp.isEmpty()) return;

        try {
            String numberPart = nowInput.substring(0, nowInput.lastIndexOf(lastOp)).trim();
            double currentValue = Double.parseDouble(numberPart);
            double secondValue = Double.parseDouble(nowInput.substring(nowInput.lastIndexOf(lastOp) + 1).trim());

            result = performOp(currentValue, secondValue, lastOp);

            if (result == (long) result) {
                nowInput = String.valueOf((long) result);
            } else {
                nowInput = String.valueOf(result);
            }
            display.setText(nowInput);

        } catch (Exception ex) {
            display.setText("Error");
            resetCalculator();
        }
    }

    private void resetCalculator() {
        nowInput = "";
        result = 0;
        lastOp = "";
        startNew = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator().setVisible(true));
    }
}