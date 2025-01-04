package com.example.ch4.files.io;

import java.io.File;


public class FileDeleteExample {
    public static void main(String[] args) {
        String filename = "C:\\example\\aa";
        File file = new File(filename);



        if (file.delete()) {
            System.out.println("파일 삭제 성공: " + filename);
        } else {
            System.out.println("파일 삭제 실패: " + filename);
        }
    }
}
