package com.mironova.task2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * MyStack tester.
 */
public class MyStackTest {
    private MyStack<Integer> testStack;

    /**
     * Setting the value of testStack.
     */
    @BeforeEach
    public void setUp() {
        Integer[] array = {1, 2, 3, 4};
        testStack = new MyStack<>(array);
    }

    @Test
    public void pushTest() {
        Integer[] testArray = {1, 2, 3, 4, 5};
        testStack.push(5);
        assertArrayEquals(testArray, testStack.toArray());
    }

    @Test
    public void popTest() {
        Integer[] testArray = {1, 2, 3};
        testStack.pop();
        assertArrayEquals(testArray, testStack.toArray());
    }

    @Test
    public void peek() {
        assertEquals(4, testStack.peek());
    }
}
