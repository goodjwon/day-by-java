package com.example.ch2.game.ex2;

public class Character {
    public String name;
    public int health;
    public int level;
    public int stamina;
    public int mana;
    public int range;

    public Character(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.level = level;
    }

    public void attack() {
        System.out.println(name + "이(가) 공격합니다.");
    }

    public void strongAttack() {
        int cost = 20;
        System.out.println(name + "이(가) 강력한 공격을 합니다! 남은 스태미나: " + (stamina - cost));
    }

    public void castSpell() {
        int cost = 20;
        System.out.println(name + "이(가) 마법을 시전합니다! 남은 마나: " + (mana - cost));
    }

    public void shootArrow() {
        int cost = 1;
        System.out.println(name + "이(가) 화살을 발사합니다! 남은 화살: " + (range - cost));
    }
}

class Warrior extends Character {
    public Warrior(String name, int health, int level, int stamina) {
        super(name, health, level);
        this.stamina = stamina;
    }
}

class Mage extends Character {
    public Mage(String name, int health, int level, int mana) {
        super(name, health, level);
        this.mana = mana;
    }
}

class Archer extends Character {
    public Archer(String name, int health, int level, int range) {
        super(name, health, level);
        this.range = range;
    }
}

class Main {
    public static void main(String[] args) {
        Character warrior = new Warrior("전사", 100, 10, 60);
        Character mage = new Mage("마법사", 100, 10, 100);
        Character archer = new Archer("궁수",100,10,30);
        warrior.attack();
        warrior.strongAttack();
        mage.attack();
        mage.castSpell();
        archer.attack();
        archer.shootArrow();
    }
}
