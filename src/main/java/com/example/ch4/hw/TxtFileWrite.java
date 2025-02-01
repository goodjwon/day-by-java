package com.example.ch4.hw;

import java.io.*;

import static com.example.ch4.xls.ExcelProcessor.getResourceFilePath;

public class TxtFileWrite {
    public static void main(String[] args) {
        String filename = getResourceFilePath("result.txt");

        try (FileWriter fw = new FileWriter(filename)) {
            for (int i = 1; i <= 100; i++) {
                fw.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
