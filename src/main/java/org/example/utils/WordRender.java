package org.example.utils;


import org.example.components.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * author 2107090411 相欣雨
 * version 1.0.0

/**
 * WordRender类，用于读取指定用户指定单词本中的单词，并将其存储到Word对象的列表中
 */
public class WordRender {
    /**
    读取指定用户指定单词本中的单词，并将其存储到Word对象的列表中
    @param username 用户名
    @param bookname 单词本名称
    @return Word对象的列表，其中存储了指定单词本中的所有单词
    */
    public static List<Word> readVocabulary(String username, String bookname) {
        List<Word> vocabulary = new ArrayList<>();
        // 读取指定用户指定单词本中的单词，并将其存储到Word对象的列表中
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data\\" + username + "\\" + bookname + ".txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                vocabulary.add(new Word(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vocabulary;
    }
}

