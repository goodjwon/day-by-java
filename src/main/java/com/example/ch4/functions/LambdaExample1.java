package com.example.ch4.functions;

public class LambdaExample1 {
    public static void main(String[] args) {
        //Runnable 인터페이스를 람다식으로 간단하게 구현한 모습
        Runnable runnable = () -> System.out.println("Hello, Lambda!");
        System.out.println("Hello, Lambda!");

        //run() 메서드를 호출하여 실제로 출력
        runnable.run();
        runnable.run();
    }
}
