package com.example.ch2.game.ex6;

public interface Movable {
    public void move(int x, int y);
}

class Player implements Movable {
    public int x;
    public int y;
    public String name;
    public int step;

    public Player(int x, int y, String name, int step) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.step = step;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
        System.out.println(name + "이(가) 위치 (" + x + ", " + y + ")로 이동했습니다.");
    }

    public void move2() {
        x += step;
        y += step;
        System.out.println(name + "이(가) 위치 (" + x + ", " + y + ")로 이동했습니다.");
    }
}

class NPC implements Movable {
    public int x;
    public int y;
    public String name;
    public int step;

    public NPC(int x, int y, String name, int step) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.step = step;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
        System.out.println(name + " 상인이(가) 위치 (" + x + ", " + y + ")로 이동했습니다.");
    }

    public void move2() {
        x += step;
        y += step;
        System.out.println(name + " 상인이(가) 위치 (" + x + ", " + y + ")로 이동했습니다.");
    }
}

class Main {
    public static void main(String[] args) {
        Player player = new Player(0,0,"용사",20);
        NPC npc = new NPC(0,0,"NPC",20);
        player.move(10,20);
        npc.move(15,25);
        player.move2();
        npc.move2();
    }
}
