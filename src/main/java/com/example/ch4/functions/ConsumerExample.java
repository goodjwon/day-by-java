package com.example.ch4.functions;

import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        Consumer<String> print = s -> System.out.println(s);

        print.accept("Hello, Consumer!");
    }
}
