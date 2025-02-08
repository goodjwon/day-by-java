package com.example.ch4.functions;

@FunctionalInterface
interface MyFunction {
    //단 하나의 추상 메서드 적용
    void apply();
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        MyFunction function = () -> System.out.println("Applying function!");
        function.apply();
    }
}
