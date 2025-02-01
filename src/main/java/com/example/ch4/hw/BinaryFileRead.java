package com.example.ch4.hw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.ch4.xls.ExcelProcessor.getResourceFilePath;

public class BinaryFileRead {

    public static void main(String[] args) {
        String filePath = getResourceFilePath("binary_output.dat");
        byte[] data = {10, 20};

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
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
