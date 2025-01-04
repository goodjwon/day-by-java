package com.example.ch3.homework.alog;

/**
 * LeaderboardUpdater
 * create : 2025.01.01
 * 작성자 : ~
 * 내용 : 리스트를 받아 상위 N 명을 내림차순으로 정렬한 값을 출력
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeaderboardUpdater {
    public static void main(String[] args) {
        int[][] scores = {
                {1, 500},
                {2, 1200},
                {3, 800},
                {4, 600}
        };
        int topN = 3;
        updateLeaderboard(scores, topN);
    }

    public static void updateLeaderboard(int[][] scores, int topN) {
        List<int[]> userDate = new ArrayList<>(Arrays.asList(scores));

        userDate.sort((a, b) -> Integer.compare(b[1], a[1]));

        System.out.println("리더보드: ");
        for (int i = 0; i < topN; i++) {
            int userId = userDate.get(i)[0];
            int score = userDate.get(i)[1];
            System.out.println(userId + ": " + score);
        }
    }
}
