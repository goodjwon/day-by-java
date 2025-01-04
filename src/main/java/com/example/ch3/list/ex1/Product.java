package com.example.ch3.list.ex1;

class Product {
    private String name;
    private int price; // 가격 (원)

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // toString 메서드 오버라이드
    @Override
    public String toString() {
        return name + " - " + price + "원";
    }
}