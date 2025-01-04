package com.example.ch2.game.ex8;

public class Spell {
    public void cast() {
        System.out.println("마법을 시전합니다.");
    }
}

class Fire extends Spell {
    @Override
    public void cast() {
        System.out.println("파이어볼 마법을 시전합니다.");
    }
}

class Ice extends Spell {
    public int power;

    public Ice(int power) {
        this.power = power;
    }

    @Override
    public void cast() {
        System.out.println("아이스 스피어 마법을 위력 " + power + "로 시전합니다.");
    }
}

class Thunder extends Spell {
    public int power;

    public Thunder(int power) {
        this.power = power;
    }

    @Override
    public void cast() {
        System.out.println("고블린에게 썬더 스트라이크 마법을 위력 " + power + "로 시전합니다.");
    }
}

class Main {
    public static void main(String[] args) {
        Fire fire = new Fire();
        Ice ice = new Ice(5);
        Thunder thunder = new Thunder(10);
        fire.cast();
        ice.cast();
        thunder.cast();
    }
}
