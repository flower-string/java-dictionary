package org.example.utils;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/

// 场景管理器, 用于切换页面，使用单例模式
public class SceneManager extends JFrame {
    private static SceneManager instance = null;
    private final HashMap<String, JPanel> scenes = new HashMap<>();

    private JPanel activeScene = null;
    private SceneManager() {
        super("flatlaf 英汉词典");
        FlatLightLaf.setup();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);

        // 读取并设置图标
        ImageIcon icon = new ImageIcon("src/main/java/org/example/icon.png");
        Image image = icon.getImage();
        setIconImage(image);

        // 设置任务栏图标
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image trayIcon = new ImageIcon("src/main/java/org/example/icon.png").getImage();
            TrayIcon trayIconObj = new TrayIcon(trayIcon, "flatlaf 英汉词典");
            trayIconObj.setImageAutoSize(true);
            try {
                tray.add(trayIconObj);
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        }
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
        this.getScene(name).show();
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
