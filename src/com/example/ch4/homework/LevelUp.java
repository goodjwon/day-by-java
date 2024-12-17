package com.example.ch4.homework;


import java.util.*;

public class LevelUp {
    static class userStatus {
        private static int idCounter = 1;
        private int userId;
        private int experience;

        public userStatus(int experience) {
            this.userId = idCounter++;
            this.experience = experience;
        }

        public int getUserId() {
            return userId;
        }

        public int getExperience() {
            return experience;
        }

        @Override
        public String toString() {
            return "userStatus [userId=" + userId + ", experience=" + experience + "]";
        }
    }

    public static List<userStatus> Status = new ArrayList<>();

    //상품을 추가하는 기능, 상품에 ID 부여
    //상품 재고 추가, 리스트에 상품 ID와 재고 추가
    public static void addStatus(Scanner scanner) {
        System.out.println("Enter the number of level: ");
        int level = scanner.nextInt();
        Status.add(new userStatus(level));
        System.out.println("UserStatus added successfully!");
    }

    public static void ListOfStatus() {
        if (Status.isEmpty()) {
            System.out.println("No Data");
            return;
        }

        for (userStatus status : Status) {
            System.out.println(status);
        }
    }

    //재고가 10개 미만 일때 ID만 출력
    public static void displayStatus(Scanner scanner) {
        System.out.println("레벨업 가능: ");
        for (userStatus status : Status) {
            if (status.getExperience() > 1000) {
                System.out.println(status.getUserId());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Status");
            System.out.println("2. Display Status");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addStatus(scanner);
                    break;
                case 2:
                    ListOfStatus();
                    break;
                case 3:
                    displayStatus(scanner);
                    break;
                case 4:
                    System.exit(0);
                    return;
            }
        }
    }
}
