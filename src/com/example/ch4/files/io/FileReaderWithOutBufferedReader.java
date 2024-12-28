package com.example.ch4.files.io;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderWithOutBufferedReader {
    public static void main(String[] args) {
        String filename = "output.txt";

        try (FileReader fr = new FileReader(filename)) {
            int character;
            while ((character = fr.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
