package com.example.ch3.homework.alog;

//레벨업 가능: 1, 3,

import java.util.ArrayList;
import java.util.List;

public class LevelUp {

    public static void main(String[] args) {
        int[][] userStats = {
                {1, 1500},  // 유저 ID 1, 경험치 1500
                {2, 800},   // 유저 ID 2, 경험치 800
                {3, 2300}   // 유저 ID 3, 경험치 2300
        };
        int levelUpThreshold = 1000;
        List<Integer> userStatsList = levelUp(userStats, levelUpThreshold);
        System.out.println("레벨업 가능: " + userStatsList);
    }

    public static  List<Integer> levelUp(int[][] userStats, int levelUpThreshold) {
        List<Integer> userStatsList = new ArrayList<>();
        for (int[] exp : userStats) {
            if (exp[1] > levelUpThreshold) {
                userStatsList.add(exp[0]);
            }
        }
        return userStatsList;
    }
}
