package com.example.ch4.functions.hw;

import java.util.function.Consumer;

public class ConsumerMailLog {
    public static void main(String[] args) {

        String mailResult = "메일 전송 완료: user@example.com";

        Consumer<String> logConsumer = msg -> System.out.print("[LOG] " + msg);

        Consumer<String> lengthConsumer = msg ->
                System.out.println(" 메세지 길이는 " + msg.length() + " 입니다.");

        Consumer<String> finalConsumer = logConsumer.andThen(lengthConsumer);

        finalConsumer.accept(mailResult);

        // "[LOG] 메일 전송 완료: user@example.com 메세지 길이는 26 입니다."
    }
}
