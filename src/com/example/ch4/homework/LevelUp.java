package com.example.ch4.homework;

//레벨업 가능: 1, 3,

public class LevelUp {
    private int[] id;
    private int[] experience;

    public LevelUp(int[] id, int[] experience) {
        this.id = id;
        this.experience = experience;
    }

    public int[] getId() {
        return id;
    }

    public int[] getExperience() {
        return experience;
    }

    public void displayStatus() {
        int[] ID = getId();
        int[] EX = getExperience();
        System.out.print("레벨업 가능: ");
        int levelUpThreshold = 1000;
        int[][] userStats = {ID, EX};
        for (int i = 0; i < userStats.length; i++) {
            for (int j = 0; j < userStats[i].length; j++) {
                if (userStats[i][j] >= levelUpThreshold) {
                    System.out.print(userStats[0][j] + ", ");
                }
            }
        }
    }

    public static void main(String[] args) {
        LevelUp levelUp = new LevelUp(new int[]{1, 2, 3}, new int[]{1500, 800, 2300});
        levelUp.displayStatus();
    }
}
