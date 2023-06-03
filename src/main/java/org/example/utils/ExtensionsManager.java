package org.example.utils;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
public class ExtensionsManager {
    private ExtensionsManager instance = null;
    private ExtensionsManager() {

    }
    public ExtensionsManager getInstance() {
        if(instance == null) {
            instance = new ExtensionsManager();
        }
        return instance;
    }

    public void install(String key) {

    }
}

