package com.example.ch4.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class LargeFileProcessingDomain {

    // -----------------------------------------------------
    // 1) 도메인 모델: User
    // -----------------------------------------------------
    public static class User {
        private final String id;
        private final String name;
        private final int age;
        private final double score;

        public User(String id, String name, int age, double score) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.score = score;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public double getScore() {
            return score;
        }
    }

    // -----------------------------------------------------
    // 2) 인프라스트럭처: CSV 파일을 읽어 User로 변환하는 Repository
    // -----------------------------------------------------
    public static class CsvUserRepository {

        /**
         * CSV 파일(헤더 포함)을 읽어 User 리스트를 반환한다.
         * 대용량 파일일 경우 전체를 List에 담기보다는 스트리밍 처리 고려 권장.
         */
        public List<User> findAllUsers(InputStream inputStream) throws IOException {
            List<User> userList = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String header = br.readLine(); // CSV 헤더 스킵
                if (header == null) {
                    // 파일이 비었거나 잘못된 경우 로직
                    return userList;
                }

                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length < 4) {
                        // CSV 포맷이 맞지 않으면 스킵하거나 예외 처리
                        continue;
                    }
                    String id = values[0].trim();
                    String name = values[1].trim();
                    int age = Integer.parseInt(values[2].trim());
                    double score = Double.parseDouble(values[3].trim());

                    User user = new User(id, name, age, score);
                    userList.add(user);
                }
            }

            return userList;
        }
    }

    // -----------------------------------------------------
    // 3) 도메인 서비스: UserAnalyticsService
    // -----------------------------------------------------
    public static class UserAnalyticsService {

        public UserStats analyze(List<User> users, int scoreThreshold, String namePrefix) {
            if (users.isEmpty()) {
                return new UserStats(0, 0, 0, 0, 0);
            }

            int totalAge = 0;
            int count = 0;
            int minAge = Integer.MAX_VALUE;
            int maxAge = Integer.MIN_VALUE;
            int countHighScore = 0;
            int countNamePrefix = 0;

            for (User user : users) {
                // 나이 통계
                int age = user.getAge();
                totalAge += age;
                count++;
                minAge = Math.min(minAge, age);
                maxAge = Math.max(maxAge, age);

                // 스코어 통계
                if (user.getScore() >= scoreThreshold) {
                    countHighScore++;
                }

                // 이름 통계
                if (user.getName().startsWith(namePrefix)) {
                    countNamePrefix++;
                }
            }

            int averageAge = (count > 0) ? totalAge / count : 0;

            return new UserStats(averageAge, minAge, maxAge, countHighScore, countNamePrefix);
        }

        // -----------------------------------------------------
        // 통계 결과를 담는 DTO(혹은 VO): UserStats
        // -----------------------------------------------------
        public static class UserStats {
            private final int averageAge;
            private final int minAge;
            private final int maxAge;
            private final int highScoreCount;
            private final int namePrefixCount;

            public UserStats(int averageAge, int minAge, int maxAge, int highScoreCount, int namePrefixCount) {
                this.averageAge = averageAge;
                this.minAge = minAge;
                this.maxAge = maxAge;
                this.highScoreCount = highScoreCount;
                this.namePrefixCount = namePrefixCount;
            }

            public int getAverageAge() {
                return averageAge;
            }

            public int getMinAge() {
                return minAge;
            }

            public int getMaxAge() {
                return maxAge;
            }

            public int getHighScoreCount() {
                return highScoreCount;
            }

            public int getNamePrefixCount() {
                return namePrefixCount;
            }
        }
    }

    // -----------------------------------------------------
    // 4) 애플리케이션 계층: 실행 진입점
    // -----------------------------------------------------
    private static final String FILE_NAME = "sample_data.csv";

    public static void main(String[] args) {
        try (InputStream inputStream = LargeFileProcessingDomain.class
                .getClassLoader()
                .getResourceAsStream(FILE_NAME)) {

            if (inputStream == null) {
                System.err.println("리소스 파일을 찾을 수 없습니다: " + FILE_NAME);
                return;
            }

            // 1) Repository를 통해 CSV 파일에서 User 리스트를 읽어온다.
            CsvUserRepository repository = new CsvUserRepository();
            List<User> userList = repository.findAllUsers(inputStream);

            // 2) 도메인 서비스를 통해 분석
            UserAnalyticsService analyticsService = new UserAnalyticsService();
            // 예: 스코어 90 이상, 이름이 "김"으로 시작
            UserAnalyticsService.UserStats stats = analyticsService.analyze(userList, 90, "김");

            // 3) 결과 출력
            System.out.println("평균 나이: " + stats.getAverageAge());
            System.out.println("최연소 사용자 나이: " + stats.getMinAge());
            System.out.println("최고령 사용자 나이: " + stats.getMaxAge());
            System.out.println("90점 이상 사용자 수: " + stats.getHighScoreCount());
            System.out.println("'김'으로 시작하는 사용자 수: " + stats.getNamePrefixCount());

        } catch (IOException e) {
            System.err.println("파일 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
