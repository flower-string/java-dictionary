package org.example.utils;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
import java.io.*;
import java.util.*;

public class TxtConverter {
    public static void main(String[] args) throws IOException {
        // Input file
        String inputFileName = "C:\\Users\\Administrator\\Desktop\\java-课设\\dictionary\\src\\main\\java\\org\\example\\lib.txt";
        // Output file
        String outputFileName = "C:\\Users\\Administrator\\Desktop\\java-课设\\dictionary\\src\\main\\java\\org\\example\\cet.txt";

        // Read input file
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        // Process lines
        List<String> outputLines = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            String word = parts[0];
            for (int i = 1; i < parts.length; i++) {
                int dotIndex = parts[i].indexOf('.');
                if (dotIndex == -1) {
                    // Skip line if it does not have the expected format
                    continue;
                }
                String pos = parts[i].substring(0, dotIndex);
                String definition = parts[i].substring(dotIndex + 1);
                outputLines.add(word + " " + pos + " " + definition + "\n");
            }
        }

        // Write output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String line : outputLines) {
                writer.write(line);
            }
        }
    }
}