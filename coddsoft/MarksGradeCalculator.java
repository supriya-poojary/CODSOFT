import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarksGradeCalculator {
    private JFrame frame;
    private JTextField[] subjectFields;
    private JLabel resultLabel;
    private JButton calculateButton;
    
    public MarksGradeCalculator() {
        setupUI();
    }
    
    private void setupUI() {
        frame = new JFrame("Marks & Grade Calculator");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(8, 1, 10, 10));
        frame.getContentPane().setBackground(new Color(30, 30, 30));
        
        JLabel titleLabel = new JLabel("Enter Marks (Out of 100)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 215, 0));
        frame.add(titleLabel);
        
        int numSubjects = 5;
        subjectFields = new JTextField[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            subjectFields[i] = new JTextField();
            subjectFields[i].setFont(new Font("Arial", Font.BOLD, 47));
            subjectFields[i].setHorizontalAlignment(JTextField.CENTER);
            subjectFields[i].setBackground(new Color(50, 50, 50));
            subjectFields[i].setForeground(Color.WHITE);
            frame.add(subjectFields[i]);
        }
        
        calculateButton = new JButton("Calculate Grade");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 22));
        calculateButton.setBackground(new Color(70, 130, 180));
        calculateButton.setForeground(Color.WHITE);
        frame.add(calculateButton);
        
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        resultLabel.setForeground(new Color(50, 205, 50));
        frame.add(resultLabel);
        
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });
        
        frame.setVisible(true);
    }
    
    private void calculateResults() {
        int totalMarks = 0;
        int numSubjects = subjectFields.length;
        
        for (JTextField field : subjectFields) {
            try {
                int marks = Integer.parseInt(field.getText());
                if (marks < 0 || marks > 100) {
                    resultLabel.setText("Marks must be between 0 and 100!");
                    resultLabel.setForeground(Color.RED);
                    return;
                }
                totalMarks += marks;
            } catch (NumberFormatException ex) {
                resultLabel.setText("Enter valid numbers!");
                resultLabel.setForeground(Color.RED);
                return;
            }
        }
        
        double averagePercentage = (double) totalMarks / numSubjects;
        String grade = calculateGrade(averagePercentage);
        
        resultLabel.setText("Total: " + totalMarks + " | Avg: " + String.format("%.2f", averagePercentage) + "% | Grade: " + grade);
        resultLabel.setForeground(Color.CYAN);
    }
    
    private String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 50) return "D";
        else return "F";
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MarksGradeCalculator::new);
    }
}