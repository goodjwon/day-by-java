package com.example.ch4.files.nio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileOutputBinExample {
    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("output.bin");
        byte[] data = {10, 20, 30, 40};

        try (BufferedWriter br = new BufferedWriter(new FileWriter(inputFilePath))){
            br.write(Arrays.toString(data));
            System.out.println("바이트 데이터 파일 작성 완료");
        } catch (IOException e) {
            System.out.println("파일 작성 중 오류 발생: " + e.getMessage());
        }
    }

    private static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = FileOutputBinExample.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }
}
