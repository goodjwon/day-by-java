package com.example.ch4.files.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOutputBinExample {
    public static void main(String[] args) {
        Path filePath = Paths.get("output.bin");
        byte[] data = {10, 20, 30, 40};

        try {
            Files.write(filePath, data);
            System.out.println("바이트 데이터 파일 작성 완료: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("파일 작성 중 오류 발생: " + e.getMessage());
        }
    }
}
