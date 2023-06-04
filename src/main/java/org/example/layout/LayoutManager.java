package org.example.layout;

import javax.swing.*;
import java.util.HashMap;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
public class LayoutManager {
    private static LayoutManager instance = null;
    private HashMap<String, JComponent> blocks;

    private LayoutManager() {}

    public static LayoutManager getInstance() {
        if(instance == null) {
            instance = new LayoutManager();
        }
        return instance;
    }

    public void depend(String name, JComponent component) {
        blocks.put(name, component);
    }

    public JComponent inject(String name) {
        return blocks.get(name);
    }
}
