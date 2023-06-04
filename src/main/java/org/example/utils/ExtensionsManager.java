package org.example.utils;

import java.util.HashMap;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
public class ExtensionsManager {
    private HashMap<String, Extension> extensions;
    private ExtensionsManager instance = null;
    private ExtensionsManager() {

    }
    public ExtensionsManager getInstance() {
        if(instance == null) {
            instance = new ExtensionsManager();
            // 在这里加载各个类
        }
        return instance;
    }

    public void install(String key) {
        extensions.get(key).install();
    }

    public void load() {

    }
}

