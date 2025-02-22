package com.example.ch4.network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 이론적 한도: 65,536개 (포트 번호 범위: 0~65,535)
 * Well-known 포트: 0~1023 (시스템/표준 서비스용, 예: HTTP 80, HTTPS 443)
 * 등록된 포트: 1024~49151 (애플리케이션용).
 * 동적 포트: 49152~65535 (임시 클라이언트 연결용).
 */

public class SimpleTCPClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";//서버 IP
        int port = 8888; //서버 포트

        Socket socket = new Socket(serverAddress, port);
        System.out.println("서버에 연결됨");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("입력: ");
            String userInput = scanner.nextLine();
            if ("exit".equalsIgnoreCase(userInput)) break;

            out.println(userInput);
            System.out.println("서버 응답: " + in.readLine());
        }

        scanner.close();
        socket.close();
    }
}
