package com.example.ch4.functions;

@FunctionalInterface
interface Greetings {
    void sayHello(String name); //단 하나의 추상 메서드
}

public class LambdaGreetingExample {
    public static void main(String[] args) {
        //람다식을 사용하여 인터페이스 구현
        Greetings greeting = (name) -> System.out.println("Hello, " + name);

        greeting.sayHello("Wonsik");
    }
}
