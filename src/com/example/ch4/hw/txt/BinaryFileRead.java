package com.example.ch4.hw.txt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryFileRead {
    public static void main(String[] args) {
        String filename = "image.bat";
        byte[] data = {10, 20};

        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(filename)) {
            float value;
            int size = "image.bat".length();
            while ((value = fis.read()) != -1) {
                System.out.print("data: " + value + ", " + "size: " + size + "\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
