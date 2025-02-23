import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

class Student {
    String name;
    String rollNumber;
    String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll No: " + rollNumber + ", Grade: " + grade;
    }
}

public class StudentManagementSystem {
    private JFrame frame;
    private JTextField nameField, rollField, gradeField, searchField;
    private JTextArea displayArea;
    private ArrayList<Student> students;
    private static final String FILE_NAME = "students.txt";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadStudentsFromFile();
        frame = new JFrame("Student Management System");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel rollLabel = new JLabel("Roll Number:");
        rollLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rollField = new JTextField();
        rollField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gradeField = new JTextField();
        gradeField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel searchLabel = new JLabel("Search Roll No:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 20));
        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton addButton = new JButton("Add Student");
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.addActionListener(e -> addStudent());

        JButton searchButton = new JButton("Search Student");
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));
        searchButton.addActionListener(e -> searchStudent());

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
        deleteButton.addActionListener(e -> deleteStudent());

        JButton saveButton = new JButton("Save Data");
        saveButton.setFont(new Font("Arial", Font.BOLD, 20));
        saveButton.addActionListener(e -> saveStudentsToFile());

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(rollLabel);
        inputPanel.add(rollField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);
        inputPanel.add(searchLabel);
        inputPanel.add(searchField);
        inputPanel.add(addButton);
        inputPanel.add(searchButton);
        inputPanel.add(deleteButton);
        inputPanel.add(saveButton);

        displayArea = new JTextArea();
        displayArea.setFont(new Font("Arial", Font.PLAIN, 20));
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText();
        String rollNumber = rollField.getText();
        String grade = gradeField.getText();

        if (name.isEmpty() || rollNumber.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        students.add(new Student(name, rollNumber, grade));
        displayStudents();
        nameField.setText("");
        rollField.setText("");
        gradeField.setText("");
    }

    private void searchStudent() {
        String rollNumber = searchField.getText();
        if (rollNumber.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a roll number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (Student student : students) {
            if (student.rollNumber.equals(rollNumber)) {
                displayArea.setText(student.toString());
                return;
            }
        }
        displayArea.setText("Student not found");
    }

    private void deleteStudent() {
        String rollNumber = searchField.getText();
        if (rollNumber.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a roll number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        students.removeIf(student -> student.rollNumber.equals(rollNumber));
        displayStudents();
        searchField.setText("");
    }

    private void displayStudents() {
        displayArea.setText("");
        for (Student student : students) {
            displayArea.append(student.toString() + "\n");
        }
    }

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.name + "," + student.rollNumber + "," + student.grade + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }

    public static void main(String[] args) {
        new StudentManagementSystem();
    }
}
