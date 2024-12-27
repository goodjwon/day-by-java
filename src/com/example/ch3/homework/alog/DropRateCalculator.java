package com.example.ch3.homework.alog;

public class DropRateCalculator {
    public static void main(String[] args) {
        int totalKills = 120;
        int totalDrops = 15;

        double dropRate = ((double) totalDrops / totalKills) * 100;

        System.out.printf("드랍 확률: %.2f%%\n", dropRate);
    }
}
