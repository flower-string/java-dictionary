package org.example.utils;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
import org.example.components.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 读取单词本的内容，返回一个单词列表
public class WordRender {
    public static List<Word> readVocabulary(String username, String bookname) {
        List<Word> vocabulary = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("data\\" + username + "\\" + bookname + ".txt"))) {
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

