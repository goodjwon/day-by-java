package com.example.ch4.files.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class LargeFileReaderExample {
    public static void main(String[] args) {
        String filename = "largeFile.txt"; // 읽을 파일 경로
        File file = new File(filename);

        // 파일 존재 여부 및 권한 확인
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다: " + filename);
            return;
        }
        if (!file.canRead()) {
            System.out.println("파일 읽기 권한이 없습니다: " + filename);
            return;
        }

        // UTF-8 인코딩으로 대용량 파일 읽기
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            long lineCount = 0;

            while ((line = br.readLine()) != null) {
                lineCount++;
                // 처리할 로직 작성 (아래는 읽은 내용의 일부 출력)
                if (lineCount <= 5) { // 상위 5줄만 출력
                    System.out.println("읽은 내용: " + line);
                }
            }
            System.out.println("총 읽은 줄 수: " + lineCount);
        } catch (IOException e) {
            System.err.println("파일 읽기 중 오류 발생: " + e.getMessage());
        }
    }
}
