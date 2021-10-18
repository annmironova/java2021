package com.mironova.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileListing class implements writing the list of files in directory to file.
 */
public class FileListing {

    /**
     * This is the main method which makes use of getFileListing,
     * writeToFile and verifying methods.
     */
    public static void main(String[] args) throws Exception {
        File startFolder = new File(args[0]);
        String resultFile = new File(args[1]).getAbsolutePath();
        verifyDirectory(startFolder);
        verifyFile(resultFile);
        List<String> result = getFileListing(startFolder);
        writeToFile(resultFile, result);
    }

    /**
     * GetFileListing method implements getting of the list of files in directory.
     *
     * @param startFolder First folder.
     * @return result List of files.
     */
    public static List<String> getFileListing(File startFolder) {
        List<String> result = new ArrayList<>();
        File[] files = startFolder.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                result.addAll(getFileListing(f));
            }
            if (f.isFile()) {
                result.add(f.getPath());
            }
        }
        return result;
    }

    /**
     * WriteToFile method implements writing the result list to file.
     *
     * @param file File to write a result.
     *
     */
    public static void writeToFile(String file, List<String> result) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String s : result) {
                fileWriter.write(s + System.lineSeparator());
            }
        }
    }


    /**
     * VerifyFile method implements verifying of directory correctness.
     *
     * @param directory Directory for verifying.
     *
     */
    public static void verifyDirectory(File directory) throws FileNotFoundException {
        if (!directory.exists()) {
            throw new FileNotFoundException("Directory does not exist: " + directory);
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Is not a directory: " + directory);
        }
    }

    /**
     * VerifyFile method implements verifying of file name correctness.
     *
     * @param resultFile File for verifying.
     *
     */
    public static void verifyFile(String resultFile) throws Exception {
        File file = new File(resultFile);
        if (file.exists()) {
            throw new Exception("File exists! Can not be rewritten: " + file);
        }
        if (file.createNewFile()) {
            System.out.println("File for result created: " + file);
        }
    }
}