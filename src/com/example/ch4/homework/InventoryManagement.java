package com.example.ch4.homework;

//부족한 재고: 101, 103,

public class InventoryManagement {
    private int[] id;
    private int[] stock;

    public InventoryManagement(int[] id, int[] stock) {
        this.id = id;
        this.stock = stock;
    }

    public int[] getId() {
        return id;
    }

    public int[] getStock() {
        return stock;
    }

    public void display() {
        int[] ID = getId();
        int[] STOCK = getStock();
        int[][] inventory = {ID, STOCK};
        System.out.print("부족한 재고: ");
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j] < 10) {
                    System.out.print(inventory[0][j] + ", ");
                }
            }
        }
    }

    public static void main(String[] args) {
        InventoryManagement inventoryManagement = new InventoryManagement(new int[]{101, 102, 103}, new int[]{5, 12, 8});
        inventoryManagement.display();
    }
}
