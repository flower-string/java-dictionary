/**

 LoginPage类，用于显示登录界面并获取用户输入的用户名
 */
package org.example.pages;
import org.example.utils.Cache;
import org.example.utils.SceneManager;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JPanel {
    private JTextField usernameField;
    public LoginPage() {
        setLayout(new GridBagLayout());
        display();
    }

    /**
     * 显示登录界面
     */
    private void display() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        // 添加用户名标签和文本框
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("用户名");
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        usernameField = new JTextField(10);
        add(usernameField, gbc);

        // 添加登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("宋体", Font.PLAIN, 26));
        loginButton.setOpaque(true);

        // 点击登录按钮后获取用户输入的用户名，并切换到主界面
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