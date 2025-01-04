package com.example.ch4.files.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileContentSearch {
    public static void main(String[] args) {
        String directoryPath = "C:\\example"; // 검색할 디렉토리 경로
        String keyword = "2025"; // 찾을 키워드

        File directory = new File(directoryPath);

        // 디렉토리 유효성 확인
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리 경로입니다: " + directoryPath);
            return;
        }

        // 파일 내용 검색
        searchFileByContent(directory, keyword);
    }

    public static void searchFileByContent(File directory, String keyword) {
        File[] files = directory.listFiles();

        if (files == null) {
            System.out.println("디렉토리 내용을 읽을 수 없습니다: " + directory.getAbsolutePath());
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                // 하위 디렉토리 검색
                searchFileByContent(file, keyword);
            } else {
                // 텍스트 파일만 검색
                if (file.getName().endsWith(".txt")) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        int lineNumber = 0;

                        while ((line = br.readLine()) != null) {
                            lineNumber++;
                            if (line.contains(keyword)) {
                                System.out.printf("키워드 '%s' 발견: %s (줄 번호: %d)%n", keyword, file.getAbsolutePath(), lineNumber);
                                System.out.println("내용: " + line);
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("파일 읽기 오류: " + file.getAbsolutePath() + " - " + e.getMessage());
                    }
                }
            }
        }
    }
}
