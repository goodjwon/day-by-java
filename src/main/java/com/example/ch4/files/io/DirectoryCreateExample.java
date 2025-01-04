package com.example.ch4.files.io;

import java.io.File;

public class DirectoryCreateExample {
    public static void main(String[] args) {
        String dirPath = "C:\\example\\202412";
        File dir = new File(dirPath);
        // C:\example\

        if (dir.mkdirs()) {
            System.out.println("디렉토리 생성 성공: " + dirPath);
        } else {
            System.out.println("디렉토리 생성 실패: " + dirPath);
        }
    }
}
