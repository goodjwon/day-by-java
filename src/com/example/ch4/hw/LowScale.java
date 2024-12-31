package com.example.ch4.hw;

import java.io.File;

public class LowScale {

    public static void main(String[] args) {
        String directoryPath = "C:\\example\\aa";

        File directory = new File(directoryPath);

        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    String newName = file.getName().toUpperCase();
                    File renamedFile = new File(directory, newName);
                    file.renameTo(renamedFile);
                }
            }
            System.out.println("파일 이름이 변경되었습니다.");
        } else {
            System.out.println("유효하지 않은 디렉토리입니다.");
        }
    }
}
