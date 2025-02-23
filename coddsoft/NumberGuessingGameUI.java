import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameUI {
    private int targetNumber;
    private int attemptsLeft;
    private int totalScore;
    private int round;
    private Random random;
    private JFrame frame;
    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel scoreLabel;
    private JButton guessButton;
    private JButton restartButton;
    
    public NumberGuessingGameUI() {
        random = new Random();
        initializeGame();
        setupUI();
    }
    
    private void initializeGame() {
        round++;
        targetNumber = random.nextInt(100) + 1;
        attemptsLeft = 7;
    }
    
    private void setupUI() {
        frame = new JFrame("Number Guessing Game");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(30, 144, 255));
        JLabel titleLabel = new JLabel("Round " + round + " - Guess a number (1-100)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);
        frame.add(topPanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1));
        centerPanel.setBackground(new Color(50, 50, 50));
        
        guessField = new JTextField(10);
        guessField.setFont(new Font("Arial", Font.BOLD, 50));
        centerPanel.add(guessField);
        
        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 18));
        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.BOLD, 18));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guessButton);
        buttonPanel.add(restartButton);
        centerPanel.add(buttonPanel);
        
        messageLabel = new JLabel("Enter your guess!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        messageLabel.setForeground(Color.WHITE);
        centerPanel.add(messageLabel);
        
        frame.add(centerPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(30, 144, 255));
        scoreLabel = new JLabel("Score: " + totalScore, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        bottomPanel.add(scoreLabel);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        
        guessButton.addActionListener(new GuessListener());
        restartButton.addActionListener(e -> resetGame());
        
        frame.setVisible(true);
    }
    
    private class GuessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int userGuess = Integer.parseInt(guessField.getText());
                attemptsLeft--;
                
                if (userGuess == targetNumber) {
                    messageLabel.setText("Correct! You guessed the number.");
                    totalScore += attemptsLeft + 1;
                    scoreLabel.setText("Score: " + totalScore);
                    guessButton.setEnabled(false);
                } else if (userGuess > targetNumber) {
                    messageLabel.setText("Too high! Attempts left: " + attemptsLeft);
                } else {
                    messageLabel.setText("Too low! Attempts left: " + attemptsLeft);
                }
                
                if (attemptsLeft == 0 && userGuess != targetNumber) {
                    messageLabel.setText("Out of attempts! The number was: " + targetNumber);
                    guessButton.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            }
            guessField.setText("");
        }
    }
    
    private void resetGame() {
        initializeGame();
        messageLabel.setText("Enter your guess!");
        scoreLabel.setText("Score: " + totalScore);
        guessButton.setEnabled(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuessingGameUI::new);
    }
}
