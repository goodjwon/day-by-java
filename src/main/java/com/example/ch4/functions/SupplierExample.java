package com.example.ch4.functions;

import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Hello, Supplier!";
        System.out.println(supplier.get());
    }
}
