import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private JFrame frame;
    private JComboBox<String> baseCurrency, targetCurrency;
    private JTextField amountInput;
    private JLabel resultLabel;
    private JButton convertButton;
    private final String[] currencies = {"USD", "INR", "EUR", "GBP", "AUD", "CAD", "JPY", "CNY"};
    private final Map<String, Double> exchangeRates;

    public CurrencyConverter() {
        // Hardcoded exchange rates (1 unit of base currency to target currency)
        exchangeRates = new HashMap<>();
        exchangeRates.put("USDINR", 82.0);
        exchangeRates.put("USDEUR", 0.85);
        exchangeRates.put("USDGBP", 0.75);
        exchangeRates.put("INRUSD", 0.012);
        exchangeRates.put("INREUR", 0.011);
        exchangeRates.put("INRGBP", 0.009);
        exchangeRates.put("EURUSD", 1.18);
        exchangeRates.put("EURINR", 99.0);
        exchangeRates.put("EURGBP", 0.88);
        exchangeRates.put("GBPUSD", 1.33);
        exchangeRates.put("GBPINR", 110.0);
        exchangeRates.put("GBPEUR", 1.14);

        setupUI();
    }

    private void setupUI() {
        frame = new JFrame("Currency Converter");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(30, 30, 30));

        JLabel title = new JLabel("Currency Converter", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(100, 20, 300, 30);
        frame.add(title);

        baseCurrency = new JComboBox<>(currencies);
        baseCurrency.setBounds(50, 80, 120, 40);
        frame.add(baseCurrency);

        targetCurrency = new JComboBox<>(currencies);
        targetCurrency.setBounds(320, 80, 120, 40);
        frame.add(targetCurrency);

        amountInput = new JTextField();
        amountInput.setFont(new Font("Arial", Font.BOLD, 18));
        amountInput.setBounds(180, 80, 120, 40);
        frame.add(amountInput);

        convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.BOLD, 20));
        convertButton.setBounds(150, 150, 200, 50);
        convertButton.setBackground(Color.GREEN);
        convertButton.setForeground(Color.BLACK);
        frame.add(convertButton);

        resultLabel = new JLabel("Converted Amount: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 22));
        resultLabel.setForeground(Color.YELLOW);
        resultLabel.setBounds(50, 230, 400, 40);
        frame.add(resultLabel);

        convertButton.addActionListener(e -> convertCurrency());

        frame.setVisible(true);
    }

    private void convertCurrency() {
        String base = (String) baseCurrency.getSelectedItem();
        String target = (String) targetCurrency.getSelectedItem();
        String amountText = amountInput.getText().trim();

        try {
            double amount = Double.parseDouble(amountText);
            String key = base + target;

            if (exchangeRates.containsKey(key)) {
                double exchangeRate = exchangeRates.get(key);
                double convertedAmount = amount * exchangeRate;
                resultLabel.setText("Converted Amount: " + String.format("%.2f", convertedAmount) + " " + target);
            } else {
                resultLabel.setText("Exchange rate not available.");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Enter a valid amount!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverter::new);
    }
}
