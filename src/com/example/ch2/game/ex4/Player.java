package com.example.ch2.game.ex4;

public class Player {
    private int experience;
    private int level;
    public String name;

    public Player(int experience, int level, String name) {
        this.experience = experience;
        this.level = level;
        this.name = name;
    }

    public void gainExperience(int amount) {
        experience += amount;
        System.out.println(name + "이(가) 경험치 " + amount + "점을 획득했습니다.");
    }

    public void levelUp() {
        for (int i = 3; i >= 0; i--) {
            int sum = i * 200;
            level = (experience - sum) / 200;
            System.out.println(name + "이(가) 레벨 " + level + "로 상승했습니다!");
        }
    }
}

class Item extends Player {

    public Item(int experience, int level, String name) {
        super(experience, level, name);
    }

    public void use(Player player) {
        System.out.println(name + "이(가) 체력을 회복했습니다.");
    }
}


class HealthPoint extends Item {
    public int health;

    public HealthPoint(int experience, int level, String name, int health) {
        super(experience, level, name);
        this.health = health;
    }

    @Override
    public void use(Player player) {
        System.out.println(name + "이(가) 체력를 " + health + "회복했습니다.");
    }
}

class ManaPoint extends Item {
    public int mana;

    public ManaPoint(int experience, int level, String name, int mana) {
        super(experience, level, name);
        this.mana = mana;
    }

    @Override
    public void use(Player player) {
        System.out.println(name + "이(가) 마나를 " + mana + "회복했습니다.");
    }
}

class Main {
    public static void main(String[] args) {
        Player player = new Player(0, 1, "Jwon");
        HealthPoint healthPoint = new HealthPoint(0, 1, "Jwon", 20);
        ManaPoint manaPoint = new ManaPoint(0, 1, "Jwon", 15);
        player.gainExperience(1000);
        player.levelUp();
        healthPoint.use(player);
        manaPoint.use(player);
    }
}
