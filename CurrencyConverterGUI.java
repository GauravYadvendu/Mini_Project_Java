import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverterGUI extends JFrame {
    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField, resultField;
    private JButton convertButton;

    private final String[] currencies = {"USD", "EUR", "INR", "JPY"};
    private final double[][] rates = {
        // USD   EUR     INR     JPY
        {1.0,   0.85,   83.12,  151.23},  // USD
        {1.18,  1.0,    97.56,  177.84},  // EUR
        {0.012, 0.010,  1.0,    1.82},    // INR
        {0.0066, 0.0056, 0.55,  1.0}      // JPY
    };

    public CurrencyConverterGUI() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Components
        add(new JLabel("From Currency:"));
        fromCurrency = new JComboBox<>(currencies);
        add(fromCurrency);

        add(new JLabel("To Currency:"));
        toCurrency = new JComboBox<>(currencies);
        add(toCurrency);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Converted Amount:"));
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);

        convertButton = new JButton("Convert");
        add(convertButton);

        // Empty cell
        add(new JLabel());

        // Convert button action
        convertButton.addActionListener(e -> convertCurrency());

        setVisible(true);
    }

    private void convertCurrency() {
        try {
            int from = fromCurrency.getSelectedIndex();
            int to = toCurrency.getSelectedIndex();
            double amount = Double.parseDouble(amountField.getText());

            double result = amount * rates[from][to];
            resultField.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverterGUI::new);
    }
}
