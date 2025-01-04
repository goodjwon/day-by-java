package com.example.ch2.game.ex7;

public class Weapon {
    public void attack() {
        System.out.println("무기로 공격합니다.");
    }
}

class Sword extends Weapon {
    @Override
    public void attack() {
        System.out.println("검으로 베기 공격을 합니다!");
    }
}

class Bow extends Weapon {
    @Override
    public void attack() {
        System.out.println("화로 화살을 발사합니다!");
    }
}

class Staff extends Weapon {
    @Override
    public void attack() {
        System.out.println("지팡이로 마법 공격을 합니다!");
    }
}

class Main {
    public static void main(String[] args) {
        Weapon weapon = new Weapon();
        Sword sword = new Sword();
        Bow bow = new Bow();
        Staff staff = new Staff();
        weapon.attack();
        sword.attack();
        bow.attack();
        staff.attack();

        System.out.println(" ");
        System.out.println("무기 배열을 사용하여 공격합니다:");

        int[] numbers = new int[4];
        numbers[0] = 1;
        numbers[1] = 2;
        numbers[2] = 3;
        numbers[3] = 4;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 1) {
                weapon.attack();
            }
            else if (numbers[i] == 2) {
                sword.attack();
            }
            else if (numbers[i] == 3) {
                bow.attack();
            }
            else {
                staff.attack();
            }
        }
    }
}
