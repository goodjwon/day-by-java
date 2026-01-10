package com.example.ch5.http.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        // 8080 포트로 서버 생성
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // '/data' 경로로 오는 요청을 처리하는 핸들러 등록
        // MyHandler 는 static class 인데 왜 new 를 썼나?
        server.createContext("/data", new MyHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("Server is running on port 8080...");
    }


    // static class 장점은?
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            System.out.println("클라이언트 요청 도착: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());

            String responseBody = "{\"message\":\"Hello from Java Server!\", \"status\":\"success\"}";

            // 1. 메타정보 설정 (Content-Type)
            // 클라이언트에게 이 데이터가 JSON이며 UTF-8로 인코딩되었음을 알림
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");

            // 2. 캐싱 지원 설정 (Cache-Control)
            // max-age=60: 60초 동안은 다시 요청하지 않고 캐시된 데이터를 써도 된다고 알림
            exchange.getResponseHeaders().set("Cache-Control", "max-age=60");

            // 3. 상태 코드 및 데이터 길이 설정 (200 OK)
            exchange.sendResponseHeaders(200, responseBody.getBytes(StandardCharsets.UTF_8).length);

            // 4. 바디(Body) 전송
            OutputStream os  = exchange.getResponseBody();
            os.write(responseBody.getBytes(StandardCharsets.UTF_8));
            os.close();

            // 각 요청은 독립적으로 처리되므로 서버는 이 요청 후 상태를 저장하지 않음 (Stateless)
        }
    }
}
