package com.example.ch4.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SimpleUDPServer {
    public static void main(String[] args) throws Exception {
        int port = 12345;
        DatagramSocket serverSocket = new DatagramSocket(port);
        byte[] buffer = new byte[1024];

        System.out.println("UDP 서버 시작. 클라이언트 대기 중...");

        while (true) {
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivedPacket);;

            String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            System.out.println("클라이언트 메시지: " + message);

            InetAddress clientAddress = receivedPacket.getAddress();
            int clientPort = receivedPacket.getPort();
            String response = "서버 응답: " + message;
            byte[] responseBytes = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);
            serverSocket.send(responsePacket);

            // 패킷 정보 출력
            System.out.println("📥 받은 패킷:");
            System.out.println("  출발지 IP: " + responsePacket.getAddress());
            System.out.println("  출발지 포트: " + responsePacket.getPort());
            System.out.println("  데이터 크기: " + responsePacket.getLength() + " bytes");
            System.out.println("  데이터 내용: " + message);
        }
    }
}
