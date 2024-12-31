package com.example.ch4.hw;

import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryFileWrite {
    public static void main(String[] args) {
        String filename = "binary_output.bat";
        byte[] data = {10, 20, 30, 40};

        try (FileOutputStream fos = new FileOutputStream(filename)){
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
