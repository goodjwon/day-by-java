package com.example.ch4.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleTCPServer {
    public static void main(String[] args) throws IOException {
        int port = 8888;//포트 번호
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("서버 시작. 클라이언트 대기 중...");

        Socket clientSocket = serverSocket.accept();
        //클라이언트 연결 대기
        System.out.println("클라이언트 연결됨: " + clientSocket.getInetAddress());

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String receivedMessage;
        while ((receivedMessage = in.readLine()) != null) {
            System.out.println("클라이언트 메시지: " + receivedMessage);
            out.println("서버 응답: " + receivedMessage);

            System.out.println("📥 받은 패킷:");
            System.out.println("  출발지 IP: " + clientSocket.getInetAddress());
            System.out.println("  출발지 포트: " + clientSocket.getPort());
            System.out.println("  데이터 내용: " + receivedMessage);
        }


        clientSocket.close();
        serverSocket.close();
    }
}
