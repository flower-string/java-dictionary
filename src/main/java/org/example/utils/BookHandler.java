package org.example.utils;

import org.example.components.Word;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;
/**
 * author 2107090411 相欣雨
 * version 1.0.0
 **/
// 用于操作单词本
public class BookHandler {
    /**
     加载单词本
     @param username 用户名
     */
    public static void loadBooks(String username) {
        // 创建存储数据的目录
        File dataDir = new File("data");
        if (!dataDir.exists()) dataDir.mkdir();

        // 获取用户目录，如果不存在则创建它
        File userDir = new File(dataDir, username);
        if (!userDir.exists()) {
            userDir.mkdir();
            // 将特定的文本文件复制到新创建的用户文件夹中
            File sourceFile = new File("src\\main\\java\\org\\example\\cet.txt");
            File destFile = new File(userDir, "cet.txt");

            try {
                Files.copy(sourceFile.toPath(), destFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 遍历用户目录下的所有文件，如果是以".txt"结尾的文件，则将其读取为单词本并保存到Cache.books中
        for (File bookFile : Objects.requireNonNull(userDir.listFiles())) {
            if (bookFile.isFile() && bookFile.getName().endsWith(".txt")) {
                // 获取单词本的名称
                String bookName = bookFile.getName().substring(0, bookFile.getName().length() - 4);
                // 从文件中读取单词本
                ArrayList<Word> words = (ArrayList<Word>) WordRender.readVocabulary(username, bookName);
                // 将单词本保存到Cache.books中
                Cache.books.put(bookName, words);
            }
        }
    }

    /**
     保存单词本
     @param username 用户名
     */
    public static void saveBooks(String username) {
        // 创建存储数据的目录
        File dataDir = new File("data");
        if (!dataDir.exists()) dataDir.mkdir();

        // 获取用户目录，如果不存在则创建它
        File userDir = new File(dataDir, username);
        if (!userDir.exists()) userDir.mkdir();

        // 遍历Cache.books中所有的单词本，将其保存到用户目录下以".txt"结尾的文件中
        for (String bookName : Cache.books.keySet()) {
            File bookFile = new File(userDir, bookName + ".txt");
            try (FileWriter writer = new FileWriter(bookFile)) {
                // 遍历单词本中的所有单词，将其写入文件
                for (Word word : Cache.books.get(bookName)) {
                    writer.write(word.word + " " + word.partOfSpeech + " " + word.definition + "\n");
                }
            } catch (IOException ignored) {
                // 处理保存失败的情况
                System.out.println("保存失败");
            }
        }
    }
}
