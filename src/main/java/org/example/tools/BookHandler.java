package org.example.tools;

import org.example.components.Word;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
public class BookHandler {
    private final HashMap<String, ArrayList<Word>> books;
    public BookHandler(HashMap<String, ArrayList<Word>> books) {
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

            try (InputStream inputStream = Files.newInputStream(sourceFile.toPath());
                 OutputStream outputStream = Files.newOutputStream(destFile.toPath())) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (File bookFile : Objects.requireNonNull(userDir.listFiles())) {
            if (bookFile.isFile() && bookFile.getName().endsWith(".txt")) {
                String bookName = bookFile.getName().substring(0, bookFile.getName().length() - 4);
                ArrayList<Word> words = new ArrayList<>();
                try (Scanner scanner = new Scanner(bookFile)) {
                    while (scanner.hasNextLine()) {
                        String[] parts = scanner.nextLine().split(" ");
                        words.add(new Word(parts[0], parts[1], parts[2]));
                    }
                } catch (IOException ignored) {
                }
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
