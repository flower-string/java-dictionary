package org.example.pages;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/

import org.example.components.Word;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VocabularyGame extends JFrame {
    private JLabel wordLabel;
    private JTextField inputField;
    private final Random random = new Random();
    private String currentWord;
    private List<Word> vocabulary;

    public VocabularyGame(String username ,String bookname) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data\\" + username + "\\" + bookname + ".txt"))) {
            this.vocabulary = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                vocabulary.add(new Word(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 设置窗口标题和大小
        setTitle("Vocabulary Game");
        setSize(600, 200);
        setLayout(new BorderLayout());

        this.display();
        // 显示随机单词
        showRandomWord();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void showRandomWord() {
        int index = random.nextInt(vocabulary.size());
        currentWord = vocabulary.get(index).word;
        wordLabel.setText(vocabulary.get(index).definition);
    }

    private void checkSpelling() {
        String userInput = inputField.getText();
        if (userInput.equals(currentWord)) {
            JOptionPane.showMessageDialog(this, "拼对了");
            showRandomWord();
            inputField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "拼错了");
        }
    }

    private void display() {
        // 创建标签、文本框和按钮
        JPanel above = new JPanel();
        wordLabel = new JLabel();
        inputField = new JTextField(10);
        above.add(wordLabel);
        above.add(inputField);

        JPanel bottom = new JPanel();
        JButton checkButton = new JButton("Check");
        JButton closeButton = new JButton("关闭");
        JButton nextButton = new JButton("下一个");
        bottom.add(closeButton);
        bottom.add(checkButton);
        bottom.add(nextButton);

        // 添加组件到窗口
        add(above, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);

        // 为按钮添加事件监听器
        checkButton.addActionListener(e -> checkSpelling());
        closeButton.addActionListener(e -> this.dispose());
        nextButton.addActionListener(e -> {
            showRandomWord();
            inputField.setText("");
        });
    }
}