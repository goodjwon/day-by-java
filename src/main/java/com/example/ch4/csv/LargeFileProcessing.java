package com.example.ch4.csv;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class LargeFileProcessing {
    private static final String FILE_NAME = "sample_data.csv";

    public static void main(String[] args) {
        processFile(FILE_NAME);
    }

    private static void processFile(String fileName) {
        try (InputStream inputStream = LargeFileProcessing.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            br.readLine();
            analyzeData(br);
        } catch (IOException e) {
            System.out.println("파일 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void analyzeData(BufferedReader br) throws  IOException {
        int[] ageStats = calculateAverageAge(br);
        int scoreAbove90Count = countHighScores(br, 90);
        int kimNameCount = countNameOccurrences(br, "김");

        System.out.println("평균 나이: " + ageStats[0]);
        System.out.println("최연소 사용자 나이: " + ageStats[1]);
        System.out.println("최고령 사용자 나이: " + ageStats[2]);
        System.out.println("90점 이상 사용자 수: " + scoreAbove90Count);
        System.out.println("'김'으로 시작하는 사용자 수: " + kimNameCount);
    }

    private static int[] calculateAverageAge(BufferedReader br) throws IOException {
        String line;
        int totalAge = 0, count = 0;
        int minAge = Integer.MAX_VALUE;
        int maxAge = Integer.MIN_VALUE;

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            int age = Integer.parseInt(values[2]);
            totalAge += age;
            count++;
            minAge = Math.min(minAge, age);
            maxAge = Math.max(maxAge, age);
        }
        return new int[] {totalAge / count, minAge, maxAge};
    }

    private static int countHighScores(BufferedReader br, int threshold) throws IOException {
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) { //?
            String[] values = line.split(",");
            double score = Double.parseDouble(values[3]);
            if (score >= threshold) {
                count++;
            }
        }
        return count;
    }

    private static int countNameOccurrences(BufferedReader br, String prefix) throws IOException {
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values[1].startsWith(prefix)) {
                count++;
            }
        }
        return count;
    }
}