package com.example.ch5.localdate;

import java.time.LocalDate;

public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2025, 5, 10);

        System.out.println("Year: " + date.getYear());
        System.out.println("Month: " + date.getMonthValue());
        System.out.println("Day: " + date.getDayOfMonth());
    }
}
