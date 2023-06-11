package org.example.utils;

import org.example.components.Word;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
// 缓存类，用于存储临时数据
public class Cache {
    public static String username;
    public static HashMap<String, ArrayList<Word>> books = new HashMap<>();
}
