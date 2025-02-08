package com.example.ch4.functions;

interface Greeting {
    void sayHello(String name);
}

public class ClassicGreetingExample {
    public static void main(String[] args) {
        //익명 클래스를 사용해 구현
        Greeting greeting = new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("Hello, " + name);
            }
        };

        greeting.sayHello("Wonsik");
    }
}
