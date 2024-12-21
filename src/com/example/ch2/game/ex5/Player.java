package com.example.ch2.game.ex5;

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
    public int damage;
    public int heal;

    public HealthPoint(int experience, int level, String name, int health, int damage, int heal) {
        super(experience, level, name);
        this.health = health;
        this.damage = damage;
        this.heal = heal;
    }

    @Override
    public void use(Player player) {
        health += heal;
        System.out.println(name + "이(가) 체력를 " + heal + "회복했습니다.");
    }

    public void damage() {
        health -= damage;
        System.out.println(name + "의 체력이 -" + damage + "만큼 변경되어 현재 체력: " + health);
    }

    public void Health() {
        System.out.println(name + "의 현재 체력: " + health);
    }
}

class ManaPoint extends Item {
    public int mana;
    public int cost;
    public int manaPotion;

    public ManaPoint(int experience, int level, String name, int mana, int cost, int manaPotion) {
        super(experience, level, name);
        this.mana = mana;
        this.cost = cost;
        this.manaPotion = manaPotion;
    }

    @Override
    public void use(Player player) {
        mana += manaPotion;
        System.out.println(name + "이(가) 마나를 " + manaPotion + "회복했습니다.");
    }

    public void cost() {
        mana -= cost;
        System.out.println(name + "의 마나가 -" + cost + "만큼 변경되어 현재 마나: " + mana);
    }

    public void Mana() {
        System.out.println(name + "의 현재 마나: " + mana);
    }
}

class Main {
    public static void main(String[] args) {
        Player player = new Player(0,1,"Jwon");
        HealthPoint healthPoint = new HealthPoint(0,1,"Jwon",100,30,20);
        ManaPoint manaPoint = new ManaPoint(0,1,"Jwon",50,20,15);
        player.gainExperience(1000);
        player.levelUp();
        healthPoint.damage();
        manaPoint.cost();
        healthPoint.Health();
        manaPoint.Mana();
        healthPoint.use(player);
        manaPoint.use(player);
        healthPoint.Health();
        manaPoint.Mana();
    }
}
