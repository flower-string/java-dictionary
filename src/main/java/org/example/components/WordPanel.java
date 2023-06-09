package org.example.components;


import org.example.pages.MainPage;
import org.example.utils.BookHandler;
import org.example.utils.Cache;
import org.example.utils.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * author 陈云云
 * version 1.0.0
 **/
public class WordPanel extends JPanel {
    private final JButton deleteButton;
    public WordPanel(int width, Word word) {
        int wordPanelHeight = 50;
        //  设置面板的高度固定
        this.setMinimumSize(new Dimension(width, wordPanelHeight));
        this.setMaximumSize(new Dimension(width, wordPanelHeight));
        this.setPreferredSize(new Dimension(width, wordPanelHeight));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.add(new JLabel(word.toString()), BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(buttonPanel, BorderLayout.EAST);

        JButton editBtn = new JButton("修改");
        editBtn.addActionListener(e -> {
            WordDialog wordDialog = new WordDialog("修改单词", word);
            wordDialog.addOKListener(e1 -> {
                // 更新单词信息
                word.setWord(wordDialog.wordField.getText());
                word.setPartOfSpeech(wordDialog.partOfSpeechField.getText());
                word.setDefinition(wordDialog.definitionField.getText());

                // 关闭对话框并更新单词列表
                wordDialog.dispose();
                BookHandler.saveBooks(Cache.username);
                ((MainPage)SceneManager.getInstance().getScene("main")).updateWordList();
            });
        });
        buttonPanel.add(editBtn);

        deleteButton = new JButton("删除");
        buttonPanel.add(deleteButton);
    }

    public void addBtn2(ActionListener listener) {
        // 为删除按钮添加监听器
        this.deleteButton.addActionListener(listener);
    }
}
