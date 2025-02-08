package com.example.ch4.functions;

import java.util.function.Consumer;

public class ConsumerAndThenExample {
    public static void main(String[] args) {
        //첫 번째 Consumer: 문자열 출력
        Consumer<String> print = s -> System.out.println("First: " + s);

        //두 번째 Consumer: 문자열 길이 출력
        Consumer<String> printLength = s -> System.out.println("Length: " + s.length());

        //두 Consumer를 연결
        Consumer<String> combinedConsumer = print.andThen(printLength);

        //연결된 Consumer 실행
        combinedConsumer.accept("Hello, Consumer!");
    }
}
