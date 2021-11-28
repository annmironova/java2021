package com.mironova.task4;

import static java.lang.Math.tan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * MultithreadedTan class implements multithreaded computation of numbers' tangents.
 */
public class MultithreadedTan {

    /**
     * GetFromFile method implements getting of numbers from file.
     *
     * @param fileName Name of file.
     * @return result List of numbers.
     */
    public static List<Double> getFromFile(String fileName) {
        List<Double> numbers = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(fileName))) {
            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    numbers.add(scanner.nextDouble());
                } else {
                    scanner.next();
                }
            }
        } catch (IOException e) {
            System.err.println("File does not exist or can not be read");
        }
        return numbers;
    }

    /**
     * ComputeTangents method implements getting of numbers from file.
     *
     * @param numbers List of numbers.
     * @param n Number of threads.
     * @return result List of tangents.
     */
    public static List<Double> computeTangents(List<Double> numbers, int n) throws Exception {
        if (n <= 0) {
            throw new Exception("Number of threads must be positive!");
        }
        List<Double> result = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(n);
        List<Future<Double>> futures = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            int finalI = i;
            Future<Double> values = executor.submit(() -> tan(numbers.get(finalI)));
            futures.add(values);
        }
        executor.shutdown();
        for (Future<Double> future : futures) {
            try {
                result.add(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * WriteToFile method implements getting of numbers from file.
     *
     * @param fileName Name of file.
     * @param result List of tangents.
     *
     */
    public static void writeToFile(String fileName, List<Double> result) throws Exception {
        File file = new File(fileName);
        if (file.exists()) {
            throw new Exception("File exists! Can not be rewritten: " + file);
        }
        if (file.createNewFile()) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                for (Double r : result) {
                    fileWriter.write(r + " ");
                }
            }
        }
    }

}
