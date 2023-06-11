package org.example.components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/

// 单词对话框，用于新增和修改单词的弹框
public class WordDialog extends JDialog {
    private final JButton okButton;
    public JTextField wordField;
    public JTextField partOfSpeechField;
    public JTextField definitionField;
    public WordDialog(String title, Word word) {
        this.setTitle(title);
        this.setLayout(new BorderLayout());

        // 创建一个面板，用于放置输入框和标签
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        this.add(inputPanel, BorderLayout.CENTER);
        // 添加标签和输入框
        inputPanel.add(new JLabel("单词："));
        wordField = new JTextField(word.word, 10);
        inputPanel.add(wordField);

        inputPanel.add(new JLabel("词性："));
        partOfSpeechField = new JTextField(word.getPartOfSpeech(), 10);
        inputPanel.add(partOfSpeechField);

        inputPanel.add(new JLabel("释义："));
        definitionField = new JTextField(word.getDefinition(), 10);
        inputPanel.add(definitionField);

        // 创建一个面板，用于放置按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(buttonPanel, BorderLayout.SOUTH);

        // 添加确定按钮
        okButton = new JButton("确定");
        buttonPanel.add(okButton);

        // 显示对话框
        this.setResizable(false);
        this.setPreferredSize(new Dimension(600, 300));
        this.setVisible(true);
        this.pack();
    }

    public void addOKListener(ActionListener listener) {
        okButton.addActionListener(listener);
    }
}

