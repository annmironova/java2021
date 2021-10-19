package com.mironova.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * FileListing tester.
 */
public class FileListingTest {

    @Test
    public void testGetFileListing() {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("resources" + File.separator + "doc.txt");
        expectedResult.add("resources" + File.separator + "folder1" + File.separator + "doc1.txt");
        expectedResult.add("resources" + File.separator + "folder1" + File.separator + "doc2.txt");
        expectedResult.add("resources" + File.separator + "folder1" + File.separator + "doc3.txt");
        expectedResult.add("resources" + File.separator + "folder1" + File.separator + "folder2"
                + File.separator + "doc4.txt");
        List<String> result = FileListing.getFileListing(new File("resources"));
        assertEquals(expectedResult, result);
    }

    @Test
    public void testWriteToFile() throws IOException {
        List<String> testList = new ArrayList<>();
        testList.add("test");
        testList.add("string");
        FileListing.writeToFile("test.txt", testList);
        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        while ((s = br.readLine()) != null) {
            String str = s + System.lineSeparator();
            stringBuilder.append(str);
        }
        String resultStr = stringBuilder.toString();
        br.close();
        assertEquals("test" + System.lineSeparator() + "string"
                + System.lineSeparator(), resultStr);
    }

    @Test
    public void testVerifyDirectory1() {
        assertThrows(Exception.class, () ->
                FileListing.verifyDirectory(new File("")));
    }

    @Test
    public void testVerifyDirectory2() {
        assertThrows(Exception.class, () ->
                FileListing.verifyDirectory(new File("test.txt")));
    }

    @Test
    public void testVerifyFile1() {
        assertThrows(Exception.class, () ->
                FileListing.verifyFile("test.txt"));
    }
}