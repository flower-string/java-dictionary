package org.example.components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
public class WordPanel extends JPanel {
    private final JButton editBtn;
    private final JButton deleteButton;
    public WordPanel(int width, Word word) {
        int wordPanelHeight = 50;
        this.setMinimumSize(new Dimension(width, wordPanelHeight));
        this.setMaximumSize(new Dimension(width, wordPanelHeight));
        this.setPreferredSize(new Dimension(width, wordPanelHeight));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.add(new JLabel(word.toString()), BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(buttonPanel, BorderLayout.EAST);

        editBtn = new JButton("修改");
//        editBtn.addActionListener();
        buttonPanel.add(editBtn);

        deleteButton = new JButton("删除");
//        deleteButton.addActionListener();
        buttonPanel.add(deleteButton);
    }

    public void addBtn1(ActionListener listener) {
        editBtn.addActionListener(listener);
    }

    public void addBtn2(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
}
