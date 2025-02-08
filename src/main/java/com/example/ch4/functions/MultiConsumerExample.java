package com.example.ch4.functions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MultiConsumerExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        // 첫 번째 Consumer: 원본 문자열 출력
        Consumer<String> printOriginal = s -> System.out.println("Original: " + s);

        // 두 번째 Consumer: 대문자로 변환 후 출력
        Consumer<String> toUpperCase = s -> System.out.println("Uppercase: " + s.toUpperCase());

        // 세 번째 Consumer: 문자열 길이 출력
        Consumer<String> printLength = s -> System.out.println("Length: " + s.length());

        // 네 번째 Consumer: "!!!" 추가 후 출력
        Consumer<String> addExclamation = s -> System.out.println("With Exclamation: " + s + "!!!");

        // 4개의 Consumer를 연결
        Consumer<String> combined = printOriginal
                .andThen(toUpperCase)
                .andThen(printLength)
                .andThen(addExclamation);

        // 연결된 Consumer 실행
        combined.accept("Hello, Consumer!");
        names.forEach(combined);

//        System.out.println("============for문 함수.============");
//        names.forEach(s->{
//            System.out.println("Name: " + s);
//            System.out.println("Length: " + s.length());
//        });
    }
}
