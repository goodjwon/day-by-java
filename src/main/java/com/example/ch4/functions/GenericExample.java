package com.example.ch4.functions;

class Box<T> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

public class GenericExample {
    public static void main(String[] args) {
        //문자열을 저장하는 Box 객체 생성
        Box<String> stringBox = new Box<>();
        stringBox.setValue("Hello, Generics!");
        System.out.println(stringBox.getValue());

        Box<Integer> intBox = new Box<>();
        intBox.setValue(1234);
        System.out.println(intBox.getValue());
    }
}
