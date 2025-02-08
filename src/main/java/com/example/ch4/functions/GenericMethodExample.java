package com.example.ch4.functions;

public class GenericMethodExample {
    public static <T> void printValue(T value) {
        //전달된 타입에 구애받지 않고 값 출력
        System.out.println("Value: " + value);
    }

    public static void main(String[] args) {
        printValue("Hello");
        printValue(123);
    }
}
