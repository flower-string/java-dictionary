package org.example.utils;


import java.io.*;
import java.util.*;
/**
 * author 相欣雨
 * version 1.0.0
 **/
// 修改文本格式
/**

 TxtConverter类，用于将输入文件中的内容转换为特定格式并写入输出文件中
 */
public class TxtConverter {
    public static void main(String[] args) throws IOException {
// 输入文件名
        String inputFileName = "C:\\Users\\Administrator\\Desktop\\java-课设\\dictionary\\src\\main\\java\\org\\example\\lib.txt";
// 输出文件名
        String outputFileName = "C:\\Users\\Administrator\\Desktop\\java-课设\\dictionary\\src\\main\\java\\org\\example\\cet.txt";

        // 读取输入文件
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        // 处理每一行数据并将其转换为特定格式
        List<String> outputLines = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            String word = parts[0];
            for (int i = 1; i < parts.length; i++) {
                int dotIndex = parts[i].indexOf('.');
                if (dotIndex == -1) {
                    // 如果该行数据不符合特定格式，则忽略该行
                    continue;
                }
                String pos = parts[i].substring(0, dotIndex);
                String definition = parts[i].substring(dotIndex + 1);
                outputLines.add(word + " " + pos + " " + definition + "\n");
            }
        }

        // 将转换后的数据写入输出文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String line : outputLines) {
                writer.write(line);
            }
        }
    }
}