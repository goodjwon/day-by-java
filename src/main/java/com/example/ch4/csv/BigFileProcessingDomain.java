package com.example.ch4.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 대용량 CSV 파일을 '스트리밍' 방식으로 처리하는 예시 코드
 * 파일명: BigFileProcessingDomain.java
 */
public class BigFileProcessingDomain {
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
    // 2) 인프라스트럭처: CSV 파일을 '스트리밍'으로 읽는 Repository
    // -----------------------------------------------------
    public static class CsvUserRepository {
        /**
         * CSV 파일(헤더 포함)을 '한 줄씩' 읽어 User 객체로 변환한 뒤,
         * 주어진 'UserConsumer'로 넘겨준다.
         *
         * @param inputStream CSV 파일의 입력 스트림
         * @param idIndex     CSV에서 ID가 위치한 컬럼 인덱스
         * @param nameIndex   CSV에서 NAME이 위치한 컬럼 인덱스
         * @param ageIndex    CSV에서 AGE가 위치한 컬럼 인덱스
         * @param scoreIndex  CSV에서 SCORE가 위치한 컬럼 인덱스
         * @param consumer    User 객체를 받아서 처리할 로직(콜백)
         * @throws IOException 파일 IO 예외
         */
        public void streamAllUsers(InputStream inputStream, int idIndex, int nameIndex, int ageIndex, int scoreIndex, UserConsumer consumer) throws IOException {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                //1) CSV 헤더 스킵
                String header = br.readLine();
                if (header == null) {
                    return;
                }

                //2) CSV 본문을 한 줄씩 읽으면서 User로 변환 후 consumer에 전달
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    //인덱스 범위를 벗어나면 스킵
                    if (values.length <= Math.max(Math.max(idIndex, nameIndex), Math.max(ageIndex, scoreIndex))) {
                        continue;
                    }

                    String id = values[idIndex].trim();
                    String name = values[nameIndex].trim();
                    int age = Integer.parseInt(values[ageIndex].trim());
                    double score = Double.parseDouble(values[scoreIndex].trim());

                    User user = new User(id, name, age, score);
                    consumer.accept(user);
                }
            }
        }
    }

    /**
     * User 객체를 처리하는 함수형 인터페이스 (Java 8 이상의 경우 Consumer<User>로 대체 가능)
     */
    @FunctionalInterface
    public interface UserConsumer {
        void accept(User user);
    }

    // -----------------------------------------------------
    // 3) 도메인 서비스: 통계를 계산하기 위한 UserAnalyticsService
    // -----------------------------------------------------
    public static class UserAnalyticsService {
        private final int scoreThreshold;
        private final String namePrefix;

        //통계를 누적할 내부 상태
        private int totalAge;
        private int count;
        private int minAge = Integer.MAX_VALUE;
        private int maxAge = Integer.MIN_VALUE;
        private int highScoreCount;
        private int namePrefixCount;

        /**
         * @param scoreThreshold 예: 점수가 90 이상인지 확인
         * @param namePrefix     예: '김'으로 시작하는 사용자
         */
        public UserAnalyticsService(int scoreThreshold, String namePrefix) {
            this.scoreThreshold = scoreThreshold;
            this.namePrefix = namePrefix;
        }

        /**
         * 한 명의 유저에 대해 통계를 누적한다.
         */
        public void aggregate(User user) {
            //나이 관련 통계
            int age = user.getAge();
            totalAge += age;
            count++;
            minAge = Math.min(minAge, age);
            maxAge = Math.max(maxAge, age);
            System.out.println(minAge);

            //점수 통계
            if (user.getScore() >= scoreThreshold) {
                highScoreCount++;
            }

            //이름 통계
            if (user.getName().startsWith(namePrefix)) {
                namePrefixCount++;
            }
        }

        /**
         * 최종 통계를 UserStats로 만들어 반환한다.
         */
        public UserStats getStats() {
            int averageAge = (count > 0) ? totalAge / count : 0;
            //count가 0이면 min/max가 초기값 그대로이므로 별도 처리 필요할 수 있음
            int finalMinAge = (count > 0) ? minAge : 0;
            int finalMaxAge = (count > 0) ? maxAge : 0;

            return new UserStats(averageAge, finalMinAge, finalMaxAge, highScoreCount, namePrefixCount);
        }
    }

    // -----------------------------------------------------
    // 4) 통계 결과 VO (UserStats)
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

    // -----------------------------------------------------
    // 5) 애플리케이션 계층: 실행 진입점
    // -----------------------------------------------------
    private static final String FILE_NAME = "sample_data.csv";

    public static void main(String[] args) {
        try (InputStream inputStream = BigFileProcessingDomain.class.getClassLoader()
            .getResourceAsStream(FILE_NAME)) {
            if (inputStream == null) {
                System.out.println("리소스 파일을 찾을 수 없습니다: " + FILE_NAME);
                return;
            }

            //1) CSV 리포지토리 준비
            CsvUserRepository repository = new CsvUserRepository();

            //2) 통계 서비스 준비
            //예: 점수 90 이상, 이름이 "김"으로 시작하는 사용자
            UserAnalyticsService analyticsService = new UserAnalyticsService(90, "김");

            //3) CSV 스트리밍: 사용자 한명씩 읽어 analyticsService에 전달
            //    (idIndex=0, nameIndex=1, ageIndex=2, scoreIndex=3)
            repository.streamAllUsers(inputStream, 0, 1, 2, 3,analyticsService::aggregate);

            UserStats stats = analyticsService.getStats();

            //4)최종 통계 결과 출력
            System.out.println("[대용량 csv 통계 결과]");
            System.out.println("평균 나이: " + stats.getAverageAge());
            System.out.println("최연소 사용자 나이: " + stats.getMinAge());
            System.out.println("최고령 사용자 나이: " + stats.getMaxAge());
            System.out.println("90점 이상 사용자 수: " + stats.getHighScoreCount());
            System.out.println("'김'으로 시작하는 사용자 수: " + stats.getNamePrefixCount());
        } catch (IOException e) {
            System.out.println("파일 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}