package org.example.layout;

import org.example.utils.SceneManager;

import javax.swing.*;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
public class Layout {
    public Layout() {
        LayoutManager layoutManager = LayoutManager.getInstance();
        layoutManager.depend("menu", new Menu());
        layoutManager.depend("search", new Search());

        JPanel mainScene = SceneManager.getInstance().getScene("main");
    }
}
