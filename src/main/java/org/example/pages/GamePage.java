package org.example.pages;


import org.example.components.Word;
import org.example.utils.Cache;
import org.example.utils.SceneManager;
import org.example.utils.WordRender;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * 相欣雨
 * version 1.0.0
 **/

public class GamePage extends JPanel {
    private JLabel wordLabel;
    private JTextField inputField;
    private String currentWord;
    private List<Word> vocabulary;
    private int score;
    private final JLabel scoreLabel;

    // 构造函数
    public GamePage() {
        setLayout(new BorderLayout());
        score = 0;
        wordLabel = new JLabel();
        scoreLabel = new JLabel("Score: " + score);
    }

    @Override
    public void show() {
        this.start();
    }

    // 开始游戏
    public void start() {
        try {
            // Load vocabulary from a file or database
            display();
            vocabulary = WordRender.readVocabulary(Cache.username, "cet");

            // Display a random word
            showRandomWord();
//            SoundUtils.playSound("bg.mp3");
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
    }

    // 随机抽取一个单词
    private void showRandomWord() {
        // Check if wordLabel is null to avoid NullPointerException
        if (wordLabel != null) {
            try {
                // Avoid race conditions in multithreaded environments
                int index = (int) (Math.random() * vocabulary.size());
                currentWord = vocabulary.get(index).word;
                wordLabel.setText(vocabulary.get(index).definition);
            } catch (Exception e) {
                // Handle any exceptions that may occur
                e.printStackTrace();
            }
        }
    }

    private void checkSpelling() {
        String userInput = inputField.getText();

        // 检查拼写是否正确
        if (userInput.equalsIgnoreCase(currentWord)) {
            JOptionPane.showMessageDialog(this, "拼对了");
            score++;
        } else {
            JOptionPane.showMessageDialog(this, "拼错了");
        }

        // 展示新的单词
        showRandomWord();
        inputField.setText("");
        scoreLabel.setText("Score: " + score);
    }

    private void display() {
// Create labels, text fields, and buttons
        JPanel above = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton closeButton = new JButton("关闭");
        JLabel scoreLabel = new JLabel("Score:");
        JLabel scoreValueLabel = new JLabel(Integer.toString(score));
        above.add(closeButton);
        above.add(scoreLabel);
        above.add(scoreValueLabel);

        JPanel middle = new JPanel(new BorderLayout());
        wordLabel = new JLabel("", SwingConstants.CENTER);
        wordLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
        middle.add(wordLabel, BorderLayout.NORTH);

        JPanel hintPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel hintLabel = new JLabel("请输入单词拼写：");
        hintPanel.add(hintLabel);
        middle.add(hintPanel, BorderLayout.CENTER);

        inputField = new JTextField(10);
        inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        middle.add(inputField, BorderLayout.SOUTH);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton checkButton = new JButton("Check");
        JButton nextButton = new JButton("下一个");
        bottom.add(checkButton);
        bottom.add(nextButton);

        // Add components to the window
        add(above, BorderLayout.NORTH);
        add(middle, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // Add event listeners to the buttons
        checkButton.addActionListener(e -> checkSpelling());
        nextButton.addActionListener(e -> showRandomWord());
        closeButton.addActionListener(e -> SceneManager.getInstance().changeScene("main"));

        // Update score label
        scoreValueLabel.setText(Integer.toString(score));
    }
}