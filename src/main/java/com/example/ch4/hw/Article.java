package com.example.ch4.hw;

/**
 * Article
 * create : 2024.12.31
 * 작성자 : ~
 * 내용 : "article.txt" 파일에서 "Java"라는 단어가 등장한 횟수를 계산하는 프로그램 작성.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Article {
    public static int countWordOption(String filePath, String word) {
        int wordCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordCount += countWord(line.toLowerCase(), word.toLowerCase()); //충돌을 피하기 위해 소문자로 변환
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return wordCount;
    }

    //주어진 단어가 몇 번 등장하는지 계산
    public static int countWord(String line, String word) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }

    //경로 탐색
    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = Article.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        String filePath = getResourceFilePath("article.txt");
        String word = "Java"; //대상

        // "Java" 단어가 등장한 횟수 계산
        int occurrences = countWordOption(filePath, word);

        // 결과
        System.out.println("Java가 " + occurrences + "번 등장했습니다.");
    }
}
