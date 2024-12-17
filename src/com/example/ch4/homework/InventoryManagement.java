package com.example.ch4.homework;

import java.util.*;

public class InventoryManagement {
    static class Inventory {
        private static int idCounter = 101;
        private int inventoryId;
        private int inventory;

        public Inventory(int inventory) {
            this.inventoryId = idCounter++;
            this.inventory = inventory;
        }

        public int getInventory() {
            return inventory;
        }

        public int getInventoryId() {
            return inventoryId;
        }

        @Override
        public String toString() {
            return "Inventory [inventoryId=" + inventoryId + ", inventory=" + inventory + "]";
        }
    }

    public static List<Inventory> inventoryList = new ArrayList<>();

    //상품을 추가하는 기능, 상품에 ID 부여
    //상품 재고 추가, 리스트에 상품 ID와 재고 추가
    public static void addInventory(Scanner scanner) {
        System.out.println("Enter the number of inventory: ");
        int inventory = scanner.nextInt();
        inventoryList.add(new Inventory(inventory));
        System.out.println("Inventory added successfully!");
    }

    public static void ListInventory() {
        if (inventoryList.isEmpty()) {
            System.out.println("No Data");
            return;
        }

        for (Inventory inventory : inventoryList) {
            System.out.println(inventory);
        }
    }

    //재고가 10개 미만 일때 ID만 출력
    public static void displayInventory(Scanner scanner) {
        System.out.println("부족한 재고: ");
        for (Inventory inventory : inventoryList) {
            if (inventory.getInventory() < 10) {
                System.out.println(inventory.getInventoryId());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Inventory");
            System.out.println("2. List Inventory");
            System.out.println("3. Display Inventory");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addInventory(scanner);
                    break;
                case 2:
                    ListInventory();
                    break;
                case 3:
                    displayInventory(scanner);
                    break;
                case 4:
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
