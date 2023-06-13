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
    public static void loadBooks(String username) {
        File dataDir = new File("data");
        if (!dataDir.exists()) dataDir.mkdir();

        File userDir = new File(dataDir, username);
        if (!userDir.exists()) {
            // 用户文件夹不存在，创建它
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
        for (File bookFile : Objects.requireNonNull(userDir.listFiles())) {
            if (bookFile.isFile() && bookFile.getName().endsWith(".txt")) {
                String bookName = bookFile.getName().substring(0, bookFile.getName().length() - 4);
                ArrayList<Word> words = (ArrayList<Word>) WordRender.readVocabulary(username, bookName);
                Cache.books.put(bookName, words);
            }
        }
    }

    public static void saveBooks(String username) {
        File dataDir = new File("data");
        if (!dataDir.exists()) dataDir.mkdir();

        File userDir = new File(dataDir, username);
        if (!userDir.exists()) userDir.mkdir();

        for (String bookName : Cache.books.keySet()) {
            File bookFile = new File(userDir, bookName + ".txt");
            try (FileWriter writer = new FileWriter(bookFile)) {
                for (Word word : Cache.books.get(bookName)) {
                    writer.write(word.word + " " + word.partOfSpeech + " " + word.definition + "\n");
                }
            } catch (IOException ignored) {

            }
        }
    }
}
