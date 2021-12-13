package com.mironova.task5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * WordParser tester.
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WordParserTest {
    private List<String> testList = new ArrayList<>();
    private HashMap<String, Integer> expectedHashMap = new HashMap<>();
    private String path = "C:\\Users\\anna\\IdeaProjects\\java2021\\resources";

    @BeforeAll
    public void setUp() {
        testList.add("one");
        testList.add("two");
        testList.add("three");
        testList.add("one");
        testList.add("two");
        testList.add("one");
        expectedHashMap.put("one", 3);
        expectedHashMap.put("two", 2);
        expectedHashMap.put("three", 1);
    }

    @Test
    public void getWordsFromFileTest() throws Exception {
        try (FileWriter fileWriter = new FileWriter("test.txt")) {
            fileWriter.write("..one,two three one two" + System.lineSeparator() + "!??one");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(testList, WordParser.getWordsFromFile("test.txt"));
    }

    @Test
    public void countWordsTest() {
        assertEquals(expectedHashMap, WordParser.countWords(testList));
    }

    @Test
    public void writeToFileTest() throws Exception {
        String fileName = path + File.separator + "counts.txt";
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        WordParser.writeToFile(expectedHashMap, fileName);
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        while ((s = br.readLine()) != null) {
            String str = s + System.lineSeparator();
            stringBuilder.append(str);
        }
        String resultStr = stringBuilder.toString();
        br.close();
        assertEquals("one one one " + System.lineSeparator() + "two two "
                + System.lineSeparator() + "three " + System.lineSeparator(), resultStr);
    }

    @Test
    public void createFilesTest1() throws Exception {
        List<String> expectedCreatedFiles = new ArrayList<>();
        expectedCreatedFiles.add(path + "\\one.txt");
        expectedCreatedFiles.add(path + "\\three.txt");
        expectedCreatedFiles.add(path + "\\two.txt");
        List<String> actualCreatedFiles = WordParser.createFiles(expectedHashMap, path);
        Collections.sort(actualCreatedFiles);
        assertEquals(expectedCreatedFiles, actualCreatedFiles);
    }
}
