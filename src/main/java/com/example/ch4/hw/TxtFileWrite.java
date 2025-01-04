package com.example.ch4.hw;

import java.io.*;

public class TxtFileWrite {
    public static void main(String[] args) {
        String filename = "result.txt";

        try (FileWriter fw = new FileWriter(filename)) {
            for (int i = 1; i <= 100; i++) {
                fw.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
