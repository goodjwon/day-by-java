package com.example.ch4.files.io;

import java.io.File;

public class RecursiveDeleteExample {
    public static void main(String[] args) {
        String dirPath = "C:\\example\\subDir";
        File dir = new File(dirPath);

        if (deleteRecursively(dir)) {
            System.out.println("디렉토리와 파일 모두 삭제 성공" + dirPath);
        } else {
            System.out.println("삭제 실패 또는 일부 항목 남아 있음: " + dirPath);
        }
    }

    public static boolean deleteRecursively(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    deleteRecursively(subFile);
                }
            }
        }
        return file.delete();
    }
}
