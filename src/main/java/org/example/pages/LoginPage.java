package org.example.pages;


import org.example.utils.Cache;
import org.example.utils.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
public class LoginPage extends JPanel {
    private JTextField usernameField;
    private JButton loginButton;

    public LoginPage() {
        setLayout(new GridBagLayout());
        display();
    }

    private void display() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("用户名");
        nameLabel.setFont(new Font("宋体", Font.PLAIN, 24));
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        usernameField = new JTextField(10);
        usernameField.setFont(new Font("宋体", Font.PLAIN, 24));
        add(usernameField, gbc);

        loginButton = new JButton("登录");
        loginButton.setFont(new Font("宋体", Font.PLAIN, 26));
        loginButton.setOpaque(true);

        loginButton.addActionListener(e -> {
            Cache.username = usernameField.getText();
            if(Cache.username.trim().isEmpty()) return;
            System.out.println("用户 " + Cache.username + " 登录成功");
            SceneManager.getInstance().changeScene("main");
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);
    }
}