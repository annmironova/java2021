package com.mironova.task4;

import static java.lang.Math.tan;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MultithreadedTanTest {

    @Test
    public void testGetFromFile() {
        try (FileWriter fileWriter = new FileWriter("test.txt")) {
            fileWriter.write("a 1.2//b 3,4 5 cdf6.7 &&" + System.lineSeparator() + "6 7* g");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Double> testNumbers = new ArrayList<>();
        testNumbers.add(3.4);
        testNumbers.add(5.0);
        testNumbers.add(6.0);
        assertEquals(testNumbers, MultithreadedTan.getFromFile("test.txt"));
    }

    @Test
    public void testComputeTangents() throws Exception {
        List<Double> testNumbers = new ArrayList<>();
        List<Double> testResults = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testNumbers.add((double)i);
            testResults.add(tan(i));
        }
        assertEquals(testResults, MultithreadedTan.computeTangents(testNumbers, 3));
    }

    @Test
    public void testWriteToFile() throws Exception {
        List<Double> testList = new ArrayList<>();
        testList.add(1.0);
        testList.add(2.0);
        File file = new File("test1.txt");
        if (file.exists()) {
            file.delete();
        }
        MultithreadedTan.writeToFile("test1.txt", testList);
        BufferedReader br = new BufferedReader(new FileReader("test1.txt"));
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        while ((s = br.readLine()) != null) {
            stringBuilder.append(s);
        }
        String resultStr = stringBuilder.toString();
        br.close();
        assertEquals("1.0 2.0 ", resultStr);
    }

    @Test
    public void testTime10() throws Exception {
        String fileName = "testTime.txt";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (int i = 0; i < 10; i++) {
                fileWriter.write(i + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Double> numbers = MultithreadedTan.getFromFile(fileName);
        long timeStart = System.currentTimeMillis();
        MultithreadedTan.computeTangents(numbers, 1);
        long timeSpent = System.currentTimeMillis() - timeStart;
        System.out.println("Time in ms for 10 numbers, 1 thread: " + timeSpent);
        timeStart = System.currentTimeMillis();
        MultithreadedTan.computeTangents(numbers, 4);
        timeSpent = System.currentTimeMillis() - timeStart;
        System.out.println("Time in ms for 10 numbers, 4 threads: " + timeSpent);
    }

    @Test
    public void testTime100() throws Exception {
        String fileName = "testTime.txt";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (int i = 0; i < 100; i++) {
                fileWriter.write(i + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Double> numbers = MultithreadedTan.getFromFile(fileName);
        long timeStart = System.currentTimeMillis();
        MultithreadedTan.computeTangents(numbers, 1);
        long timeSpent = System.currentTimeMillis() - timeStart;
        System.out.println("Time in ms for 100 numbers, 1 thread: " + timeSpent);
        timeStart = System.currentTimeMillis();
        MultithreadedTan.computeTangents(numbers, 4);
        timeSpent = System.currentTimeMillis() - timeStart;
        System.out.println("Time in ms for 100 numbers, 4 threads: " + timeSpent);
    }

    @Test
    public void testTime1000000() throws Exception {
        String fileName = "testTime.txt";
        try(FileWriter fileWriter = new FileWriter(fileName)) {
           for (int i = 0; i < 1000000; i++) {
               fileWriter.write(i + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Double> numbers = MultithreadedTan.getFromFile(fileName);
        long timeStart = System.currentTimeMillis();
        MultithreadedTan.computeTangents(numbers, 1);
        long timeSpent = System.currentTimeMillis() - timeStart;
        System.out.println("Time in ms for 1000000 numbers, 1 thread: " + timeSpent);
        timeStart = System.currentTimeMillis();
        MultithreadedTan.computeTangents(numbers, 4);
        timeSpent = System.currentTimeMillis() - timeStart;
        System.out.println("Time in ms for 1000000 numbers, 4 threads: " + timeSpent);
    }
}
