package com.example.ch4.files.io;

import java.io.File;

public class FileNameSearch {
    public static void main(String[] args) {
        String directoryPath = "C:\\example"; // 검색할 디렉토리 경로
        String targetFileName = "targetFile.txt"; // 찾을 파일명
        boolean caseSensitive = false; // 대소문자 구분 여부

        File directory = new File(directoryPath);

        // 디렉토리 유효성 확인
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리 경로입니다: " + directoryPath);
            return;
        }

        // 파일 검색
        boolean found = searchFileByName(directory, targetFileName, caseSensitive);
        if (!found) {
            System.out.printf("디렉토리 '%s'에서 파일 '%s'를 찾을 수 없습니다.%n", directoryPath, targetFileName);
        }
    }

    public static boolean searchFileByName(File directory, String targetFileName, boolean caseSensitive) {
        File[] files = directory.listFiles();

        if (files == null) {
            System.out.println("디렉토리 내용을 읽을 수 없습니다: " + directory.getAbsolutePath());
            return false;
        }

        boolean isFound = false;

        for (File file : files) {
            if (file.isDirectory()) {
                // 하위 디렉토리 검색
                isFound = searchFileByName(file, targetFileName, caseSensitive) || isFound;
            } else {
                // 파일명 비교
                if (caseSensitive) {
                    if (file.getName().equals(targetFileName)) {
                        System.out.println("파일 발견: " + file.getAbsolutePath());
                        isFound = true;
                    }
                } else {
                    if (file.getName().equalsIgnoreCase(targetFileName)) {
                        System.out.println("파일 발견: " + file.getAbsolutePath());
                        isFound = true;
                    }
                }
            }
        }
        return isFound;
    }
}