package com.example.ch4.files.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputBinExample {
    public static void main(String[] args) {
        String filename = "output.bin";
        byte[] data = {10, 20, 30, 40};

        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//(
