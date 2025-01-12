package com.example.ch3.homework.alog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecommendationSorter {
    public static void main(String[] args) {
        int[][] recommendations = {
                {101, 85},
                {102, 95},
                {103, 70}
        };
        updateLeaderboard(recommendations);
    }

    public static void updateLeaderboard(int[][] recommendations) {
        List<int[]> Preference = new ArrayList<>(Arrays.asList(recommendations));

        Preference.sort((a, b) -> Integer.compare(b[1], a[1]));

        System.out.println("추천 상품: ");
        for (int i = 0; i < recommendations.length; i++) {
            int userId = Preference.get(i)[0];
            int score = Preference.get(i)[1];
            System.out.println(userId + ": " + score);
        }
    }
}