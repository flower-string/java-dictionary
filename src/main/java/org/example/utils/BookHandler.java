package org.example.utils;

import org.example.components.Word;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class BookHandler {
    private HashMap<String, ArrayList<Word>> books;

    public BookHandler(HashMap<String, ArrayList<Word>> books) {
        this.books = books;
    }

    public HashMap<String, ArrayList<Word>> getBooks() {
        return books;
    }

    public void setBooks(HashMap<String, ArrayList<Word>> books) {
        this.books = books;
    }

    public void loadBooks(String username) {
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
                ArrayList<Word> words = (ArrayList<Word>) VocabularyReader.readVocabulary(username, bookName);
                books.put(bookName, words);
            }
        }
    }

    public void saveBooks(String username) {
        File dataDir = new File("data");
        if (!dataDir.exists()) dataDir.mkdir();

        File userDir = new File(dataDir, username);
        if (!userDir.exists()) userDir.mkdir();

        for (String bookName : books.keySet()) {
            File bookFile = new File(userDir, bookName + ".txt");
            try (FileWriter writer = new FileWriter(bookFile)) {
                for (Word word : books.get(bookName)) {
                    writer.write(word.word + " " + word.partOfSpeech + " " + word.definition + "\n");
                }
            } catch (IOException ignored) {

            }
        }
    }
}
