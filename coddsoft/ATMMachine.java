import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

public class ATMMachine {
    private JFrame frame;
    private JLabel screen;
    private JTextField inputField;
    private JButton withdrawButton, depositButton, balanceButton;
    private BankAccount account;

    public ATMMachine() {
        account = new BankAccount(5000);
        setupUI();
    }

    private void setupUI() {
        frame = new JFrame("ATM Machine");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(50, 50, 50));

        // ATM Screen (Shaped Like Real ATM Display)
        screen = new JLabel("Welcome to ATM", SwingConstants.CENTER);
        screen.setFont(new Font("Digital", Font.BOLD, 28));
        screen.setForeground(Color.GREEN);
        screen.setOpaque(true);
        screen.setBackground(Color.BLACK);
        screen.setBounds(100, 50, 400, 60);
        frame.add(screen);

        // Input Field (For Entering Amount)
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.BOLD, 24));
        inputField.setBounds(150, 130, 300, 50);
        frame.add(inputField);

        // ATM Buttons Panel (Designed Like ATM Keypad)
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(new Color(50, 50, 50));
        buttonPanel.setBounds(150, 220, 300, 200);

        withdrawButton = createStyledButton("Withdraw");
        depositButton = createStyledButton("Deposit");
        balanceButton = createStyledButton("Check Balance");

        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(balanceButton);
        frame.add(buttonPanel);

        // Card Slot Representation
        JLabel cardSlot = new JLabel("Insert Card Here", SwingConstants.CENTER);
        cardSlot.setFont(new Font("Arial", Font.BOLD, 18));
        cardSlot.setForeground(Color.WHITE);
        cardSlot.setBackground(Color.DARK_GRAY);
        cardSlot.setOpaque(true);
        cardSlot.setBounds(200, 450, 200, 40);
        frame.add(cardSlot);

        // Event Listeners
        withdrawButton.addActionListener(e -> handleWithdraw());
        depositButton.addActionListener(e -> handleDeposit());
        balanceButton.addActionListener(e -> showBalance());

        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 22));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        return button;
    }

    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(inputField.getText());
            if (account.withdraw(amount)) {
                screen.setText("Withdrawn: " + amount);
            } else {
                screen.setText("Insufficient Balance!");
            }
        } catch (NumberFormatException e) {
            screen.setText("Enter valid amount!");
        }
        inputField.setText("");
    }

    private void handleDeposit() {
        try {
            double amount = Double.parseDouble(inputField.getText());
            account.deposit(amount);
            screen.setText("Deposited: " + amount);
        } catch (NumberFormatException e) {
            screen.setText("Enter valid amount!");
        }
        inputField.setText("");
    }

    private void showBalance() {
        screen.setText("Balance: " + account.getBalance());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMMachine::new);
    }
}
