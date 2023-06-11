package org.example.components;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.example.utils.SceneManager;

import javax.swing.*;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
// 主题切换按钮
public class ThemeComboBox extends JComboBox {
    public ThemeComboBox() {
        super(new String[]{"Flat Light", "Flat Dark", "Flat IntelliJ", "Flat Darcula"});
        this.addActionListener(e -> {
            String selectedTheme = (String) this.getSelectedItem();
            try {
                if (selectedTheme != null) {
                    switch (selectedTheme) {
                        case "Flat Light":
                            FlatLightLaf.setup();
                            break;
                        case "Flat Dark":
                            FlatDarkLaf.setup();
                            break;
                        case "Flat IntelliJ":
                            FlatIntelliJLaf.setup();
                            break;
                        case "Flat Darcula":
                            FlatDarculaLaf.setup();
                            break;
                    }
                }
                SwingUtilities.updateComponentTreeUI(SceneManager.getInstance());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
