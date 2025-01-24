package com.example.ch4.hw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LargeFileProcessing {
    static class Data {
        int id;
        String name;
        int age;
        double score;

        public Data(int id, String name, int age, double score) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.score = score;
        }
    }

    static class Option {
        double averageAge;
        double averageScore;
        int Upper90;
        int StartNameKim;
        int MaxAge;
        int MinAge;

        public Option(double averageAge, double averageScore, int upper90, int startNameKim, int maxAge, int minAge) {
            this.averageAge = averageAge;
            this.averageScore = averageScore;
            Upper90 = upper90;
            StartNameKim = startNameKim;
            MaxAge = maxAge;
            MinAge = minAge;
        }
    }

    public static Option processFile(String filePath) {
        List<Data> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length == 4) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    double score = Double.parseDouble(data[3]);

                    dataList.add(new Data(id, name, age, score));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        double age = 0;
        double score = 0;
        int upper90 = 0;
        int startKim = 0;
        int MaxAge = Integer.MAX_VALUE;
        int MinAge = Integer.MIN_VALUE;

        for (Data data : dataList) {
            age += data.age;
            score += data.score;

            if (data.score >= 90) {
                upper90++;
            }

            if (data.name.startsWith("김")) {
                startKim++;
            }

            if (data.age < MinAge) {
                MinAge = data.age;
            }

            if (data.age > MaxAge) {
                MaxAge = data.age;
            }
        }
        double averageAge = age / dataList.size();
        double averageScore = score / dataList.size();
        return new Option(averageAge, averageScore, upper90, startKim, MinAge, MaxAge);
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = LargeFileProcessing.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("sample_data.csv");

        if (inputFilePath == null) {
            System.out.println("파일 경로를 찾을 수 없습니다.");
            return;
        }

        Option result = processFile(inputFilePath);
        if (result != null) {
            // 결과 출력
            System.out.printf("평균 나이: %.1f\n", result.averageAge);
            System.out.printf("평균 점수: %.1f\n", result.averageScore);
            System.out.println("90점 이상 사용자 수: " + result.Upper90);
            System.out.println("'김'으로 시작하는 사용자 수: " + result.StartNameKim);
            System.out.println("최연소 사용자 나이: " + result.MinAge);
            System.out.println("최고령 사용자 나이: " + result.MaxAge);
        }
    }
}
