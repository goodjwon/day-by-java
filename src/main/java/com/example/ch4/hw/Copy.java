package com.example.ch4.hw;

/**
 * Destination
 * create : 2024.12.31
 * 작성자 : ~
 * 내용 : "source.txt" 파일의 내용을 "destination.txt" 파일로 복사하는 프로그램 작성.
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static com.example.ch4.xls.ExcelProcessor.getResourceFilePath;

public class Copy {
    public static void main(String[] args) {
        String source = getResourceFilePath("source.txt");
        String target = getResourceFilePath("destination.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(source));
             BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {
            bw.write(br.readLine());
            System.out.println("완료되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static String getResourceFilePath(String fileName) {
//        ClassLoader classLoader = Copy.class.getClassLoader();
//        if (classLoader.getResource(fileName) != null) {
//            return classLoader.getResource(fileName).getPath();
//        }
//        return null;
//    }
}
