package org.example.pages;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/

import org.example.components.Word;
import org.example.utils.Cache;
import org.example.utils.SceneManager;
import org.example.utils.VocabularyReader;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GamePage extends JPanel {
    private JLabel wordLabel;
    private JTextField inputField;
    private String currentWord;
    private List<Word> vocabulary;

    public GamePage() {
        setLayout(new BorderLayout());
    }

    public void show() {
        this.display();
        this.vocabulary = VocabularyReader.readVocabulary(Cache.username, "cet");
        // 显示随机单词
        showRandomWord();
    }

    private void showRandomWord() {
        // 避免多线程下的竞争关系
        int index = ThreadLocalRandom.current().nextInt(vocabulary.size());
        currentWord = vocabulary.get(index).word;
        wordLabel.setText(vocabulary.get(index).definition);
    }

    private void checkSpelling() {
        String userInput = inputField.getText();
        // 忽略用户输入的大小写
        if (userInput.equalsIgnoreCase(currentWord)) {
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
        above.add(wordLabel);

        inputField = new JTextField(10);

        JPanel bottom = new JPanel();
        JButton checkButton = new JButton("Check");
        JButton closeButton = new JButton("关闭");
        JButton nextButton = new JButton("下一个");
        bottom.add(closeButton);
        bottom.add(checkButton);
        bottom.add(nextButton);

        // 添加组件到窗口
        add(above, BorderLayout.NORTH);
        add(inputField, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // 为按钮添加事件监听器
        checkButton.addActionListener(e -> checkSpelling());
        nextButton.addActionListener(e -> {
            showRandomWord();
            inputField.setText("");
        });
        closeButton.addActionListener(e -> SceneManager.getInstance().changeScene("main"));
    }

}
