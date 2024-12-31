package com.example.ch4.hw;

/**
 * Paragraphs
 * create : 2024.12.31
 * 작성자 : ~
 * 내용 : "paragraphs.txt" 파일에서 줄의 개수를 세는 프로그램 작성.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Paragraphs {
    public static void main(String[] args) {
        Path filePath = Paths.get("paragraphs.txt");
        List<String> list = new ArrayList<>();
        int count = 0;

        try {
            Files.lines(filePath).forEach(list::add);
            for (int i = 1; i < list.size(); i++) {
                count = i;
            }
            System.out.println("해당 파일의 줄 수: " + count);
        } catch (IOException e) {
            System.out.println("파일 읽기 중 오류 발생: " + e.getMessage());
        }
    }
}
