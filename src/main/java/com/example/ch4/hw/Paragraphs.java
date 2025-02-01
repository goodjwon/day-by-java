package com.example.ch4.hw;

/**
 * Paragraphs
 * create : 2024.12.31
 * 작성자 : ~
 * 내용 : "paragraphs.txt" 파일에서 줄의 개수를 세는 프로그램 작성.
 */

import java.io.*;
import java.util.*;

import static com.example.ch4.xls.ExcelProcessor.getResourceFilePath;

public class Paragraphs {
    public static void main(String[] args) {
        String filePath = getResourceFilePath("paragraphs.txt");
        List<String> list = new ArrayList<>();
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            for (int i = 1; i < list.size(); i++) {
                count = i;
            }
            list.add(br.readLine());
            list.forEach(System.out::print);
            System.out.println("해당 파일의 줄 수: " + count);
        } catch (IOException e) {
            System.out.println("파일 읽기 중 오류 발생: " + e.getMessage());
        }
    }
}
