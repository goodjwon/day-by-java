package com.example.ch3.homework.alog;

//부족한 재고: 101, 103,

import java.util.ArrayList;
import java.util.List;

public class InventoryManagement {

//    public void display() {
//        int[] ID = getId();
//        int[] STOCK = getStock();
//        int[][] inventory = {ID, STOCK};
//
//        System.out.print("부족한 재고: ");
//        for (int i = 0; i < inventory.length; i++) {
//            extracted(inventory, i);
//        }
//    }
//

    public static void main(String[] args) {
        int[][] inventory = {
                {101, 5},  // 상품 ID 101, 재고 5
                {102, 12}, // 상품 ID 102, 재고 12
                {103, 8}   // 상품 ID 103, 재고 8
        };
        List<Integer> lowStockItems = findLowStockItems(inventory, 10);
        System.out.println("부족한 재고 :" + lowStockItems);
    }

    public static List<Integer> findLowStockItems(int[][] inventory, int threshold ){
        List<Integer> lowStockItems = new ArrayList<>();
        for (int[] item : inventory) {
            if(item[1]  < threshold){
                lowStockItems.add(item[0]);
            }
        }
        return lowStockItems;
    }
}
