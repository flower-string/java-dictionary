package org.example.utils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/

/**
 * 主题类，用于设置应用程序的字体主题
 */
public class Theme {
    /**
        初始化全局字体
        @param font 字体
    */
    public static void initGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        // 遍历UIManager中所有的键值对，将值为FontUIResource类型的键值对的值设置为指定的字体
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }
}
