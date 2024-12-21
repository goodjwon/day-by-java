package com.example.ch2.game.ex9;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T> {
    public String category;
    public List<T> items;

    public Inventory(String category) {
        this.category = category;
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
        System.out.println(category + ": " + item + "이(가) 인벤토리에 추가되었습니다.");
    }


    public static void main(String[] args) {
        Inventory<String> weaponInventory = new Inventory<>("무기");
        weaponInventory.addItem("전설의 검");
        Inventory<String> potionInventory = new Inventory<>("물약");
        potionInventory.addItem("체력 회복 물약");
    }
}
