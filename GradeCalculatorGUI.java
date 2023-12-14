import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorGUI extends JFrame {
    private JTextField[] subjectFields;
    private JButton calculateButton;

    public GradeCalculatorGUI() {
        setTitle("Grade Calculator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Input fields for English, Math, Science, and Social Science
        String[] subjects = {"English", "Math", "Science", "Social Science"};
        subjectFields = new JTextField[subjects.length];

        for (int i = 0; i < subjects.length; i++) {
            subjectFields[i] = new JTextField(5);
            add(new JLabel("Enter marks for " + subjects[i] + ":"));
            add(subjectFields[i]);
        }

        // Calculate button
        calculateButton = new JButton("Calculate");
        add(calculateButton);

        // Action listener for the Calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayResults();
            }
        });

        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private int calculateTotalMarks() {
        int totalMarks = 0;
        for (JTextField field : subjectFields) {
            totalMarks += Integer.parseInt(field.getText());
        }
        return totalMarks;
    }

    private double calculateAveragePercentage(int totalMarks, int numSubjects) {
        return (double) totalMarks / numSubjects;
    }

    private char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    private void calculateAndDisplayResults() {
        // Calculate total marks
        int totalMarks = calculateTotalMarks();

        // Calculate average percentage
        double averagePercentage = calculateAveragePercentage(totalMarks, subjectFields.length);

        // Calculate grade
        char grade = calculateGrade(averagePercentage);

        // Display results
        String resultMessage = "Results:\nTotal Marks: " + totalMarks +
                "\nAverage Percentage: " + String.format("%.2f", averagePercentage) +
                "\nGrade: " + grade;
        JOptionPane.showMessageDialog(this, resultMessage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculatorGUI();
            }
        });
    }
}
