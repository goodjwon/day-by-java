package com.example.ch4.functions.hw;

import java.util.function.Supplier;

public class SupplierTime {
    public static void main(String[] args) {

        Supplier<Long> time = System::currentTimeMillis;

        System.out.println(time.get());
    }
}
