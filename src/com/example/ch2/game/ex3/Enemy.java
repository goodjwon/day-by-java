package com.example.ch2.game.ex3;

public class Enemy {
    public String name;

    public Enemy(String name) {
        this.name = name;
    }

    public void attack() {
        System.out.println(name + "이 무기로 공격합니다!");
    }
}

class Goblin extends Enemy {
    public Goblin(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println(name + "이 단검으로 찌릅니다!");
    }
}

class Troll extends Enemy {
    public Troll(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println(name + "이 몽둥이로 내리칩니다!");
    }
}

class Dragon extends Enemy {
    public Dragon(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println(name + "이 불을 뿜습니다!");
    }
}

class Main {
    public static void main(String[] args) {
        Enemy goblin = new Goblin("고블린");
        Enemy troll = new Troll("트롤");
        Enemy dragon = new Dragon("드래곤");
        goblin.attack();
        troll.attack();
        dragon.attack();
    }
}
