package com.mironova.task2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * MyList tester.
 */
public class MyListTest {
    private MyList<Integer> testList;
    private int testSize;

    /**
     * Setting the value of testList and its size.
     */
    @BeforeEach
    public void setUp() {
        Integer[] array = {1, 2, 3, 4};
        testList = new MyList<>(array);
        testSize = 4;
    }

    @Test
    public void addTest() {
        Integer[] testArray = {1, 2, 3, 4, 5};
        testList.add(5);
        assertArrayEquals(testArray, testList.toArray());
    }

    @Test
    public void addWithIndexTest() {
        Integer[] testArray = {1, 2, 3, 6, 4};
        testList.add(3, 6);
        assertArrayEquals(testArray, testList.toArray());
    }

    @Test
    public void removeTest() {
        Integer[] testArray = {1, 2, 4};
        testList.remove(2);
        assertArrayEquals(testArray, testList.toArray());
        assertThrows(UnsupportedOperationException.class, () ->
                testList.remove(10));
    }

    @Test
    public void indexOfTest() {
        int index = 1;
        int notIndex = -1;
        assertEquals(index, testList.indexOf(2));
        assertEquals(notIndex, testList.indexOf(10));
    }

    @Test
    public void getTest() {
        int element = 2;
        assertEquals(element, testList.get(1));
        assertThrows(UnsupportedOperationException.class, () ->
                testList.get(10));
    }

    @Test
    public void isEmptyTest() {
        MyList<Integer> emptyList = new MyList<>();
        assertTrue(emptyList.isEmpty());
        assertFalse(testList.isEmpty());
    }

    @Test
    public void sizeTest() {
        assertEquals(testSize, testList.size());
    }

    @Test
    public void toArrayTest() {
        Integer[] testArray = {1, 2, 3, 4};
        assertArrayEquals(testArray, testList.toArray());
    }
}
