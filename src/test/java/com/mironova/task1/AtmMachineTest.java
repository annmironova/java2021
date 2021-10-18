package com.mironova.task1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * AtmMachine tester.
 */
public class AtmMachineTest {

    @Test
    public void testExchange1() {
        long[] values = {3, 4};
        List<List<Long>> res = new ArrayList<>();
        List<Long> var1 = new ArrayList<>();
        var1.add(values[1]);
        var1.add(values[0]);
        res.add(var1);
        var resTest = AtmMachine.exchange(values, 7, values[values.length - 1]);
        assertEquals(res.size(), resTest.size());
        assertEquals(res, resTest);
    }

    @Test
    public void testExchange2() {
        long[] values = {1, 3};
        List<List<Long>> res = new ArrayList<>();
        List<Long> var1 = new ArrayList<>();
        var1.add(values[1]);
        var1.add(values[0]);
        res.add(var1);
        List<Long> var2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            var2.add(values[0]);
        }
        res.add(var2);
        var resTest = AtmMachine.exchange(values, 4, values[values.length - 1]);
        assertEquals(res.size(), resTest.size());
        assertEquals(res, resTest);
    }

    @Test
    public void testChangeValues() {
        long[] values = {3, 5, 1, 1, 5, 3, 3, 3, 3};
        long[] valuesRes = {1, 3, 5};
        long[] valuesTest = AtmMachine.changeValues(values);
        assertArrayEquals(valuesRes, valuesTest);
    }

    @Test
    public void getValues1() {
        String input = "1 2" + System.lineSeparator() + "-1";
        String expectedOutput = "Input values:\n" + "Input total sum:\n"
                + "Error! A negative number or zero\n";
        InputStream stringStream = new java.io.ByteArrayInputStream(input.getBytes());
        OutputStream outputStream = new java.io.ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        AtmMachine.getValues(stringStream, printStream);
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void getValues2() {
        String input = "1 2" + System.lineSeparator() + "string";
        String expectedOutput = "Input values:\n" + "Input total sum:\n"
                + "Error! Not an integer or too big value\n";
        InputStream stringStream = new ByteArrayInputStream(input.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        AtmMachine.getValues(stringStream, printStream);
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void getValues3() {
        String input = "1 5" + System.lineSeparator() + "10";
        String expectedOutput = "Input values:\n" + "Input total sum:\n";
        InputStream stringStream = new java.io.ByteArrayInputStream(input.getBytes());
        OutputStream outputStream = new java.io.ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        AtmMachine.getValues(stringStream, printStream);
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void getValues4() {
        String input = "1 -5" + System.lineSeparator() + "10";
        String expectedOutput = "Input values:\n"
                + "Error! A negative number or zero through values\n";
        InputStream stringStream = new java.io.ByteArrayInputStream(input.getBytes());
        OutputStream outputStream = new java.io.ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        AtmMachine.getValues(stringStream, printStream);
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void getValues5() {
        String input = "2.3" + System.lineSeparator() + "10";
        String expectedOutput = "Input values:\n" + "Error! Not an integer\n";
        InputStream stringStream = new java.io.ByteArrayInputStream(input.getBytes());
        OutputStream outputStream = new java.io.ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        AtmMachine.getValues(stringStream, printStream);
        assertEquals(expectedOutput, outputStream.toString());
    }
}