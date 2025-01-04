package com.example.ch4.files.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderTxtExample {
    public static void main(String[] args) {
        Path filePath = Paths.get("output.txt");
        String content = "NIO를 사용한 문자열 파일 작성";

        try {
            Files.writeString(filePath, content);
            System.out.println("문자열 데이터 파일 작성 완료: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("파일 작성 중 오류 발생: " + e.getMessage());
        }
    }
}
