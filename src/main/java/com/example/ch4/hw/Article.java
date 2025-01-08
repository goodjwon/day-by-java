package main.java.com.example.ch4.hw;

/**
 * Article
 * create : 2024.12.31
 * 작성자 : ~
 * 내용 : "article.txt" 파일에서 "Java"라는 단어가 등장한 횟수를 계산하는 프로그램 작성.
 */

import java.io.*;

public class Article {
    public static void main(String[] args) {
        String filePath = "D:\\samuel\\java\\과외 내용\\day-by-java\\article.txt";
        String keyword = "Java";

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            System.out.println("유효하지 않은 파일 경로입니다: " + filePath);
            return;
        }

        int wordCount = countWord(file, keyword);

        System.out.println("파일에 등장한 Java의 횟수: " + wordCount);
    }

    public static int countWord(File file, String keyword) {
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                count += countInLine(line, keyword);
            }
        } catch (IOException e) {
            System.err.println("파일 읽기 오류: " + file.getAbsolutePath() + " - " + e.getMessage());
        }

        return count;
    }

    public static int countInLine(String line, String keyword) {
        int count = 0;
        int index = 0;

        while ((index = line.indexOf(keyword, index)) != -1) {
            count++;
            index += keyword.length();
        }

        return count;
    }
}
