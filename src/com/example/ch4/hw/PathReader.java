package com.example.ch4.hw;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class PathReader {

    public static void main(String[] args) {
        String directoryPath = "C:\\example\\aa";

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));

            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getAbsolutePath());

                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.err.println("파일을 읽을 수 없습니다: " + file.getAbsolutePath());
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("디렉토리에서 파일을 찾을 수 없습니다.");
            }
        } else {
            System.err.println("유효한 디렉토리가 아닙니다.");
        }
    }
}
