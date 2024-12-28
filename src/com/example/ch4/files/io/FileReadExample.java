package com.example.ch4.files.io;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReadExample {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("output.bin")) {
            int value;
            while ((value = fis.read()) != -1) {
                System.out.println(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
