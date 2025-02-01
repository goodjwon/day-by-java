package com.example.ch4.files.nio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.ch4.xls.ExcelProcessor.getResourceFilePath;

public class FileReaderTxtExample {
    public static void main(String[] args) {
        String filePath = getResourceFilePath("output.txt");
        String content = "NIO를 사용한 문자열 파일 작성";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            bw.write(content);
            System.out.println("문자열 데이터 파일 작성 완료");
        } catch (IOException e) {
            System.out.println("파일 작성 중 오류 발생: " + e.getMessage());
        }
    }
}
