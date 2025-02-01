package com.example.ch4.hw;

import java.io.*;
import java.util.Arrays;


public class BinaryFileWrite {
    public static void main(String[] args) {
        String OutputFilePath = "binary_output.bat";
        byte[] data = {10, 20, 30, 40};

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OutputFilePath))){
            bw.write(Arrays.toString(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
