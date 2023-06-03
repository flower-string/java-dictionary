package org.example.utils;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.util.HashMap;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/

// 场景管理器, 用于切换页面
public class SceneManager extends JFrame {
    private static SceneManager instance = null;
    private HashMap<String, JPanel> scenes = new HashMap<>();

    private JPanel activeScene = null;
    private SceneManager() {
        super("flatlaf 单词本");
        FlatLightLaf.setup();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);
        setVisible(true);
    }

    public static SceneManager getInstance() {
        if(instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void changeScene(String name) {
        System.out.println("切换场景为：" + name);
        getContentPane().removeAll();
        add(this.getScene(name));
        revalidate();
        repaint();
    }

    public void addScene(String name, JPanel scene) {
        this.scenes.put(name, scene);
        if(activeScene == null) {
            activeScene = scene;
        }
    }

    public JPanel getScene(String name) {
        return this.scenes.get(name);
    }
}
