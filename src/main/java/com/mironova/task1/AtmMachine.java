package com.mironova.task1;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * AtmMachine class implements exchanging a banknote to the sum of values.
 */
public class AtmMachine {

    /**
     * This is the main method which makes use of getValues method.
     */
    public static void main(String[] args) {
        List<List<Long>> result = getValues(System.in, System.out);
        System.out.println("Number of variants: " + result.size());
        for (List<Long> list : result) {
            for (Long l : list) {
                System.out.print(l + " ");
            }
            System.out.println();
        }
    }

    /**
     * GetValues method implements exchanging a banknote to the sum of values.
     *
     * @param input1 Input stream.
     * @param output Output stream.
     * @return List of lists of exchanging variants.
     */
    public static List<List<Long>> getValues(InputStream input1, PrintStream output) {
        long sumForExchange;
        output.print("Input values:\n");
        Scanner scanner = new Scanner(input1);
        String[] strValues = scanner.nextLine().split(" ");
        long[] values = new long[strValues.length];
        boolean flag = true;
        try {
            for (int i = 0; i < strValues.length; i++) {
                values[i] = Long.parseLong(strValues[i]);
                if (values[i] <= 0) {
                    flag = false;
                    output.print("Error! A negative number or zero through values\n");
                }
            }
        } catch (NumberFormatException e) {
            flag = false;
            output.print("Error! Not an integer\n");
        }
        if (flag) {
            output.print("Input total sum:\n");
            if (scanner.hasNextLong()) {
                sumForExchange = scanner.nextLong();
                if (sumForExchange <= 0) {
                    flag = false;
                    output.print("Error! A negative number or zero\n");
                }
                if (flag) {
                    long[] newValues = changeValues(values);
                    return exchange(newValues, sumForExchange, newValues[newValues.length - 1]);
                }
            } else {
                output.print("Error! Not an integer or too big value\n");
            }
        }
        scanner.close();
        return new ArrayList<>();
    }

    /**
     * Exchange method implements exchanging a banknote to the sum of values.
     *
     * @param values Array of available values.
     * @param sumForExchange A total sum.
     * @param maxValue Maximum value
     * @return result List of lists of exchanging variants.
     */
    public static List<List<Long>> exchange(long[] values, long sumForExchange, long maxValue) {
        List<List<Long>> result = new ArrayList<>();
        if (sumForExchange == 0) {
            result.add(new ArrayList<>());
        } else {
            for (int i = values.length; i > 0; i--) {
                long coin = values[i - 1];
                if (coin <= sumForExchange && coin <= maxValue) {
                    for (List<Long> preResult : exchange(values, sumForExchange - coin, coin)) {
                        List<Long> list = new ArrayList<>();
                        list.add(coin);
                        list.addAll(preResult);
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    /**
     * ChangeValues method implements array sorting and deleting repeated elements.
     *
     * @param values Array of values.
     * @return Sorted array without repeated elements
     */
    public static long[] changeValues(long[] values) {
        Arrays.sort(values);
        int compare = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[compare] != values[i]) {
                compare++;
                values[compare] = values[i];
            }
        }
        return Arrays.copyOf(values, compare + 1);
    }
}