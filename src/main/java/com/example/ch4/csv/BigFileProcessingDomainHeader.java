package com.example.ch4.csv;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 대용량 CSV 파일을 '스트리밍' 방식으로 처리하는 예시 코드
 * 파일명: BigFileProcessingDomain.java
 *
 * 헤더(첫 줄)에서 원하는 컬럼명을 찾아 인덱스 매핑 후, 해당 인덱스를 사용.
 */
public class BigFileProcessingDomainHeader {

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
         * @param inputStream     CSV 파일의 입력 스트림
         * @param idColumnName    CSV 헤더에서 "ID" 컬럼명 (예: "id")
         * @param nameColumnName  CSV 헤더에서 "이름" 컬럼명 (예: "name")
         * @param ageColumnName   CSV 헤더에서 "나이" 컬럼명 (예: "age")
         * @param scoreColumnName CSV 헤더에서 "점수" 컬럼명 (예: "score")
         * @param consumer        User 객체를 받아서 처리할 로직(콜백)
         * @throws IOException 파일 IO 예외
         */
        public void streamAllUsersByHeader(
                InputStream inputStream,
                String idColumnName,
                String nameColumnName,
                String ageColumnName,
                String scoreColumnName,
                UserConsumer consumer
        ) throws IOException {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                // 1) CSV 헤더 파싱
                String headerLine = br.readLine();
                if (headerLine == null) {
                    return; // 파일이 비었거나 잘못된 경우
                }

                // 헤더 컬럼들 추출
                // 예: header=["id","name","age","score"]
                String[] headers = headerLine.split(",");

                // 필요 컬럼명 인덱스 찾기
                int idIndex = findColumnIndex(headers, idColumnName);
                int nameIndex = findColumnIndex(headers, nameColumnName);
                int ageIndex = findColumnIndex(headers, ageColumnName);
                int scoreIndex = findColumnIndex(headers, scoreColumnName);

                if (idIndex < 0 || nameIndex < 0 || ageIndex < 0 || scoreIndex < 0) {
                    // 원하는 컬럼명을 찾지 못하면 처리 중단 또는 예외
                    throw new IOException("헤더에 필요한 컬럼이 없습니다: "
                            + Arrays.toString(headers));
                }

                // 2) CSV 본문을 한 줄씩 읽으면서 User로 변환 후 consumer에 전달
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    // 인덱스 범위를 벗어나면 스킵(혹은 예외 처리)
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

        /**
         * 헤더 배열에서 특정 컬럼명을 찾아 인덱스를 반환.
         * 일치하는 컬럼이 없다면 -1 반환.
         */
        private int findColumnIndex(String[] headers, String columnName) {
            for (int i = 0; i < headers.length; i++) {
                // 대소문자 구분이 필요한지 여부는 상황에 맞게 처리
                if (headers[i].trim().equalsIgnoreCase(columnName)) {
                    return i;
                }
            }
            return -1;
        }
    }

    /**
     * User 객체를 처리하는 함수형 인터페이스 (Java 8+에서는 Consumer<User> 등으로 대체 가능)
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

        // 통계를 누적할 내부 상태
        private int totalAge;
        private int count;
        private int minAge = Integer.MAX_VALUE;
        private int maxAge = Integer.MIN_VALUE;
        private int highScoreCount;
        private int namePrefixCount;

        /**
         * @param scoreThreshold 예: 점수가 90 이상인지 확인
         * @param namePrefix     예: "김"으로 시작하는 사용자
         */
        public UserAnalyticsService(int scoreThreshold, String namePrefix) {
            this.scoreThreshold = scoreThreshold;
            this.namePrefix = namePrefix;
        }

        /**
         * 한 명의 유저에 대해 통계를 누적한다.
         */
        public void aggregate(User user) {
            // 나이 관련 통계
            int age = user.getAge();
            totalAge += age;
            count++;
            minAge = Math.min(minAge, age);
            maxAge = Math.max(maxAge, age);

            // 점수 통계
            if (user.getScore() >= scoreThreshold) {
                highScoreCount++;
            }

            // 이름 통계
            if (user.getName().startsWith(namePrefix)) {
                namePrefixCount++;
            }
        }

        /**
         * 최종 통계를 UserStats로 만들어 반환한다.
         */
        public UserStats getStats() {
            int averageAge = (count > 0) ? totalAge / count : 0;
            int finalMinAge = (count > 0) ? minAge : 0;
            int finalMaxAge = (count > 0) ? maxAge : 0;

            return new UserStats(
                    averageAge,
                    finalMinAge,
                    finalMaxAge,
                    highScoreCount,
                    namePrefixCount
            );
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

        public UserStats(int averageAge, int minAge, int maxAge,
                         int highScoreCount, int namePrefixCount) {
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
        try (InputStream inputStream = BigFileProcessingDomainHeader.class
                .getClassLoader()
                .getResourceAsStream(FILE_NAME)) {

            if (inputStream == null) {
                System.err.println("리소스 파일을 찾을 수 없습니다: " + FILE_NAME);
                return;
            }

            // 예) CSV 헤더가 [id,이름,나이",점수] 라고 가정
            String idCol = "ID";
            String nameCol = "이름";
            String ageCol = "나이";
            String scoreCol = "점수";

            // 1) CSV 리포지토리 준비
            CsvUserRepository repository = new CsvUserRepository();

            // 2) 통계 서비스 준비
            // 예: 점수 90 이상, 이름이 "김"으로 시작하는 사용자
            UserAnalyticsService analyticsService = new UserAnalyticsService(90, "김");

            // 3) CSV 스트리밍: 헤더에서 해당 컬럼명을 찾아 인덱스 매핑 후 사용자 한 명씩 읽어 aggregate
            repository.streamAllUsersByHeader(inputStream, idCol, nameCol, ageCol, scoreCol, analyticsService::aggregate);

            // 4) 최종 통계 결과 출력
            UserStats stats = analyticsService.getStats();

            System.out.println("[대용량 CSV 통계 결과]");
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
