package com.example.ch4.hw;

/**
 * Destination
 * create : 2024.12.31
 * 작성자 : ~
 * 내용 : "source.txt" 파일의 내용을 "destination.txt" 파일로 복사하는 프로그램 작성.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Copy {
    public static void main(String[] args) {
        Path source = Paths.get("D:\\samuel\\java\\workspase\\day-by-java\\source.txt");
        Path target = Paths.get("D:\\samuel\\java\\workspase\\day-by-java\\destination.txt");

        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
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
