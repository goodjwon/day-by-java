package com.example.ch3.game.ex4;

public class Player {
    private int experience;
    private int level;

    public Player(int experience, int level) {
        this.experience = experience;
        this.level = level;
    }

    public void gainExperience(int amount) {
        experience += amount;
    }
}
