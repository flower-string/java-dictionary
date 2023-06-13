package org.example.utils;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/

/**
 场景管理器，用于管理应用程序的不同场景
 */
public class SceneManager extends JFrame {
    private static SceneManager instance = null;
    private final HashMap<String, JPanel> scenes = new HashMap<>();

    private JPanel activeScene = null;

    private SceneManager() {
        super("flatlaf 英汉词典");
        // 设置主题为FlatLaf
        FlatLightLaf.setup();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        // 读取并设置应用程序图标
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

    /**
     获取SceneManager的单例实例
     @return SceneManager的单例实例
     */
    public static SceneManager getInstance() {
        if(instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }
    /**
     切换场景
     @param name 场景名称
     */
    public void changeScene(String name) {
        System.out.println("切换场景为：" + name);
        // 移除当前的场景
        getContentPane().removeAll();
        // 添加要切换到的场景，并显示它
        add(this.getScene(name));
        this.getScene(name).show();
        // 重新计算并绘制界面
        revalidate();
        repaint();
    }
    /**
     添加场景
     @param name 场景名称
     @param scene 场景面板
     */
    public void addScene(String name, JPanel scene) {
        this.scenes.put(name, scene);
        // 如果当前没有激活的场景，则激活添加的场景
        if(activeScene == null) {
            activeScene = scene;
        }
    }
    /**
     获取场景面板
     @param name 场景名称
     @return 场景面板
     */
    public JPanel getScene(String name) {
        return this.scenes.get(name);
    }
}
