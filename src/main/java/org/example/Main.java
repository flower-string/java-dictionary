package org.example;

import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.GamePage;
import org.example.utils.SceneManager;

import javax.swing.*;
import java.awt.*;

import static org.example.utils.Theme.InitGlobalFont;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
public class Main extends JFrame {
    public Main() {
        // 统一设置字体
        InitGlobalFont(new Font("alias", Font.PLAIN, 20));

        // 设置场景
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.addScene("login", new LoginPage());
        sceneManager.addScene("main", new MainPage());
        sceneManager.addScene("game", new GamePage());
        sceneManager.changeScene("login");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}