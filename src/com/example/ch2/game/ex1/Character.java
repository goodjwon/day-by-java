package com.example.ch2.game.ex1;

public class Character {
    public String name;
    public int health;
    public int level;

    public Character(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.level = level;
    }

    public void attack() {
        System.out.println(name + "이(가) 공격합니다!");
    }

    public void defense() {
        System.out.println(name + "이(가) 방어합니다!");
    }
}

class Main {
    public static void main(String[] args) {
        Character character1 = new Character("용사",100,10);
        Character character2 = new Character("악당",100,10);
        character1.attack();
        character2.defense();
    }
}
