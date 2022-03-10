package com.mironova.task5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * WordParser class implements parsing for specific words in a document
 */
public class WordParser {

    public static List<String> getWordsFromFile(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new Exception("Such file does not exist!");
        }
        if (file.isDirectory()) {
            throw new Exception("This is a directory!");
        }
        List<String> words = Files.lines(Paths.get(fileName))
                .flatMap(str -> Stream.of(str.split("\\p{Punct}|[\\s]+"))
                .filter(word -> word.length() != 0))
                .collect(Collectors.toList());
        return words;
    }

    public static HashMap<String, Integer> countWords(List<String> words) {
        HashMap<String, Integer> wordCount = words.stream()
                .collect(HashMap::new, (str, c) -> str.put(c, Collections.frequency(words, c)), HashMap::putAll);
        return wordCount;
    }

    public static void writeToFile(HashMap<String, Integer> wordCount, String filePath) throws Exception {
        File file = new File(filePath);
        if (file.exists()) {
            throw new Exception("File exists! Can not be rewritten: " + file);
        }
        if (file.createNewFile()) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                    for (int i = 0; i < entry.getValue(); i++) {
                        fileWriter.write(entry.getKey() + " ");
                    }
                    fileWriter.write(System.lineSeparator());
                }
            }
        }
    }

    public static  List<String> createFiles(HashMap<String, Integer> wordCount, String path) throws Exception {
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            throw new Exception("Such path does not exist!");
        }
        HashMap<String, CompletableFuture<String>> futureHashMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            futureHashMap.put(path + File.separator + entry.getKey() + ".txt", CompletableFuture.supplyAsync(() -> {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < entry.getValue(); i++) {
                    stringBuilder.append(entry.getKey()).append(" ");
                }
                return stringBuilder.toString();
            }));
        }
        List<String> createdFiles = new ArrayList<>();
        for (Map.Entry<String, CompletableFuture<String>> entry : futureHashMap.entrySet()) {
            String filePath = entry.getKey();
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(entry.getValue().get());
            } catch (IOException e) {
                e.printStackTrace();
            }
            createdFiles.add(filePath);
        }
        return createdFiles;
    }
}
