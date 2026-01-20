package com.example.ch5.http.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.logging.Handler;

public class JsonResponseServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        server.createContext("/api/member", new MemberHandler());
        server.start();
        System.out.println("JSON API Server running on port 8081...");
    }

    static class Member {
        int id = 1;
        String name = "Gemini";
    }

    static class MemberHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 2. 자바 객체 생성 (비즈니스 로직 결과물)
            Member member = new Member();

            // 3. 직렬화(Serialization): 객체를 JSON 문자열로 변환
            // 실제 스프링에서는 Jackson 라이브러리가 이 과정을 자동으로 수행합니다.
            String jsonResponse = String.format("{\"id\": %d, \"name\": \"%s\"}", member.id, member.name);

            // 4. 메타정보(Header) 설정: 응답 데이터의 타입을 JSON으로 명시
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonResponse.getBytes(StandardCharsets.UTF_8).length);

            // 5. 바디 전송
            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
            os.close();
        }
    }
}
