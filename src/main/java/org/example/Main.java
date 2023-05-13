package org.example;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

import com.formdev.flatlaf.FlatDarkLaf;
import org.example.pages.LoginPage;
import org.example.pages.VocabularyBookPanel;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
public class Main extends JFrame {
    public Main() {
        super("flatlaf 单词本");
        FlatDarkLaf.setup();
        setLayout(new BorderLayout());

        LoginPage loginPage = new LoginPage();
        loginPage.addLoginActionListener(e -> {
            // 这里可以添加验证用户名和密码的代码
            String username = loginPage.getUsername();
            if(username.trim().isEmpty()) return;
            showVocabularyBookPanel(username);
        });
        add(loginPage, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);
        setVisible(true);
        InitGlobalFont(new Font("alias", Font.PLAIN, 24));  //统一设置字体
    }

    private void showVocabularyBookPanel(String username) {
        getContentPane().removeAll();
        add(new VocabularyBookPanel(username), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);

        for (Enumeration<Object> keys = UIManager.getDefaults().keys();

             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();

            Object value = UIManager.get(key);

            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }

    }
}