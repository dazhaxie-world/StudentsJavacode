package work16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelationCalculator extends JFrame {
    private JTextField display;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;

    public RelationCalculator() {
        setTitle("计算器");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 显示面板
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                ">", "<", "==", "C"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.addActionListener(new ButtonClickListener());
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "C":
                    currentInput = "";
                    operator = "";
                    firstNumber = 0;
                    display.setText("");
                    break;
                case "=":
                    calculateResult();
                    break;
                case ">":
                case "<":
                case "==":
                    handleComparison(command);
                    break;
                default:
                    if (isOperator(command)) {
                        handleOperator(command);
                    } else {
                        appendInput(command);
                    }
            }
        }
    }

    private void appendInput(String text) {
        currentInput += text;
        display.setText(currentInput);
    }

    private void handleOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
            operator = op;
            currentInput = "";
        }
    }

    private void handleComparison(String op) {
        if (!operator.isEmpty() && !currentInput.isEmpty()) {
            double secondNumber = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "*": result = firstNumber * secondNumber; break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        showError("除数不能为零");
                        return;
                    }
                    break;
                case ">": result = firstNumber > secondNumber ? 1 : 0; break;
                case "<": result = firstNumber < secondNumber ? 1 : 0; break;
                case "==": result = firstNumber == secondNumber ? 1 : 0; break;
            }

            display.setText(String.valueOf(result));
            currentInput = "";
            operator = "";
        }
    }

    private void calculateResult() {
        if (!operator.isEmpty() && !currentInput.isEmpty()) {
            try {
                double secondNumber = Double.parseDouble(currentInput);
                double result = 0;

                switch (operator) {
                    case "+": result = firstNumber + secondNumber; break;
                    case "-": result = firstNumber - secondNumber; break;
                    case "*": result = firstNumber * secondNumber; break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            showError("除数不能为零");
                            return;
                        }
                        break;
                }

                display.setText(String.valueOf(result));
                currentInput = "";
                operator = "";
            } catch (NumberFormatException ex) {
                showError("无效输入");
            }
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "错误", JOptionPane.ERROR_MESSAGE);
    }

    private boolean isOperator(String text) {
        return text.matches("[+\\-*/]");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RelationCalculator calculator = new RelationCalculator();
            calculator.setVisible(true);
        });
    }
}
