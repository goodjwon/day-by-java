package com.example.ch5.http.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientTest {
    public static void main(String[] args) {
        // 1. 클라이언트 생성 (독립적인 시스템 구축 가능)
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // 2. 요청 메시지 구성 (Request)
        // 메서드(GET), URI, 헤더(Accept) 등을 설정합니다.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            System.out.println(">>> 서버로 요청을 보냅니다...");

            // 3. 응답 수신 (Response)
            HttpResponse<String> response = client.send(request
                    , HttpResponse.BodyHandlers.ofString());
            // 4. 결과 확인
            System.out.println("=== 응답 상태 코드 (Status Code) ===");
            System.out.println(response.statusCode()); // 200 OK 등 확인

            System.out.println("\n=== 응답 헤더 (Meta Info) ===");
            // Content-Type 등을 통해 데이터 해석 방법을 알 수 있음
            response.headers().map().forEach((k, v) -> System.out.println(k + ": " + v));

            System.out.println("\n=== 응답 본문 (Body) ===");
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
