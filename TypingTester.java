import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TypingTester {

    static String[] sentences = {
    "The quick brown fox jumps over the lazy dog",
    "Java is a powerful programming language",
    "Practice daily to improve your coding skills",
    "Consistency is the key to success",
    "Focus on progress not perfection",
    "Typing speed improves with regular practice"
    };

    static String sentence = "";
    static long startTime = 0, endTime = 0;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Typing Speed Tester");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        frame.add(mainPanel);

        // 🔹 Sentence Display
        JTextArea sentenceArea = new JTextArea(sentence);
        sentenceArea.setLineWrap(true);
        sentenceArea.setWrapStyleWord(true);
        sentenceArea.setEditable(false);
        sentenceArea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sentenceArea.setBackground(new Color(240,240,240));

        // 🔹 Input Area
        JTextArea inputArea = new JTextArea();
        inputArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        JScrollPane inputScroll = new JScrollPane(inputArea);

        // 🔹 Buttons
        JPanel buttonPanel = new JPanel();

        JButton startBtn = new JButton("Start");
        JButton finishBtn = new JButton("Finish");

        buttonPanel.add(startBtn);
        buttonPanel.add(finishBtn);

        // 🔹 Result Area
        JTextArea resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createTitledBorder("Result"));

        // 🔹 Layout
        mainPanel.add(sentenceArea, BorderLayout.NORTH);
        mainPanel.add(inputScroll, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);
        mainPanel.add(resultArea, BorderLayout.SOUTH);

        startBtn.addActionListener(e -> {

   	// 🔥 Pick random sentence
    	sentence = sentences[(int)(Math.random() * sentences.length)];

    	sentenceArea.setText(sentence);

    	inputArea.setText("");
    	resultArea.setText("");

    	startTime = System.currentTimeMillis();
    	inputArea.requestFocus();
    	});

        // 🔥 FINISH BUTTON
        finishBtn.addActionListener(e -> {

            if (startTime == 0) {
                JOptionPane.showMessageDialog(frame, "Click Start first!");
                return;
            }

            endTime = System.currentTimeMillis();

            double timeTaken = (endTime - startTime) / 1000.0;
            if (timeTaken == 0) timeTaken = 1;

            String typedText = inputArea.getText();

            int wordCount = typedText.trim().isEmpty() ? 0 : typedText.trim().split("\\s+").length;

            double wpm = (wordCount / timeTaken) * 60;

            int correctChars = 0;
            for (int i = 0; i < Math.min(sentence.length(), typedText.length()); i++) {
                if (sentence.charAt(i) == typedText.charAt(i)) {
                    correctChars++;
                }
            }

            double accuracy = ((double) correctChars / sentence.length()) * 100;

            resultArea.setText(
                "Time: " + String.format("%.2f", timeTaken) + " sec\n" +
                "WPM: " + String.format("%.2f", wpm) + "\n" +
                "Accuracy: " + String.format("%.2f", accuracy) + "%"
            );

            startTime = 0; // reset
        });

        frame.setVisible(true);
    }
}
