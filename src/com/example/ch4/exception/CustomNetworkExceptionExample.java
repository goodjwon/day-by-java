package com.example.ch4.exception;

class NetworkTimeoutException extends Exception {
    NetworkTimeoutException(String msg) {
        super(msg);
    }
}

class NetworkDisconnectedException extends Exception {
    NetworkDisconnectedException(String msg) {
        super(msg);
    }
}

public class CustomNetworkExceptionExample {
    public static void main(String[] args) {
        try {
            fetchDataFromServer();
        } catch (NetworkTimeoutException e) {
            System.out.println("네트워크 타임 아웃 예외: " + e.getMessage());
        } catch (NetworkDisconnectedException e) {
            System.out.println("네트워크 연결 끊김 예외: " + e.getMessage());
        }
    }

    static void fetchDataFromServer() throws NetworkTimeoutException, NetworkDisconnectedException {
        boolean timeout = true; //가정: 네트워크 요청 타임아웃 발생 상황

        boolean disconnected = true; //가정: 네트워크 연결 끊김 여부

        if (timeout) {
            throw new NetworkTimeoutException("서버 응답 지연으로 타임아웃 발생");
        } else if (disconnected) {
            throw new NetworkDisconnectedException("네트워크 연결 끊김");
        }

        //정상적 데이터 수신 로직 가정
    }
}
