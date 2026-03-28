import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TypingTester {

    static String sentence = "The quick brown fox jumps over the lazy dog";
    static long startTime, endTime;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Typing Speed Tester");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(15,15));

        JPanel mainPanel = new JPanel(new BorderLayout(15,15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        frame.add(mainPanel);

        // TOP (Sentence)
        JTextArea sentenceArea = new JTextArea(sentence);
        sentenceArea.setLineWrap(true);
        sentenceArea.setWrapStyleWord(true);
        sentenceArea.setEditable(false);
        sentenceArea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sentenceArea.setBackground(new Color(240,240,240));

        // CENTER (User Input)
        JTextArea inputArea = new JTextArea();
        inputArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        JScrollPane inputScroll = new JScrollPane(inputArea);

        // BUTTONS
        JPanel buttonPanel = new JPanel();

        JButton startBtn = new JButton("Start");
        JButton finishBtn = new JButton("Finish");

        buttonPanel.add(startBtn);
        buttonPanel.add(finishBtn);

        // RESULT AREA
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));

        mainPanel.add(sentenceArea, BorderLayout.NORTH);
        mainPanel.add(inputScroll, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.EAST);

        // START BUTTON
        startBtn.addActionListener(e -> {
            inputArea.setText("");
            resultArea.setText("");
            startTime = System.currentTimeMillis();
        });

        // FINISH BUTTON
        finishBtn.addActionListener(e -> {

            endTime = System.currentTimeMillis();

            String typedText = inputArea.getText();

            // TIME
            double timeTaken = (endTime - startTime) / 1000.0;

            // WORD COUNT
            int wordCount = typedText.trim().isEmpty() ? 0 : typedText.trim().split("\\s+").length;

            // WPM
            double wpm = (wordCount / timeTaken) * 60;

            // ACCURACY
            int correctChars = 0;
            for (int i = 0; i < Math.min(sentence.length(), typedText.length()); i++) {
                if (sentence.charAt(i) == typedText.charAt(i)) {
                    correctChars++;
                }
            }

            double accuracy = ((double) correctChars / sentence.length()) * 100;

            // DISPLAY
            resultArea.setText(
                "Time: " + String.format("%.2f", timeTaken) + " sec\n" +
                "WPM: " + String.format("%.2f", wpm) + "\n" +
                "Accuracy: " + String.format("%.2f", accuracy) + "%"
            );
        });

        frame.setVisible(true);
    }
}
