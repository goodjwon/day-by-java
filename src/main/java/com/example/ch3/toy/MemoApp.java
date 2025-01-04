package com.example.ch3.toy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemoApp {

    static class Memo {
        private static int idCounter = 1;
        private int id;
        private String title;
        private String content;
        private String createDate;
        private String modifiedDate;

        //작성기능 -1 (요구사항 id) - 메모객체를 생성하는 행위. => ??
        public Memo(String title, String content) {
            this.id = idCounter++;
            this.title = title;
            this.content = content;
            this.createDate = getCurrentDateTime();
            this.modifiedDate = getCurrentDateTime();
        }

        private static String getCurrentDateTime() {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        //수정기능 -3 => 기존에 생성된 메모를 수정.
        public void updateContent(String title, String content) {
            this.title = title;
            this.content = content;
            this.modifiedDate = getCurrentDateTime();
        }

        public String getTitle() {
            return title;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Memo{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", createDate='" + createDate + '\'' +
                    ", modifiedDate='" + modifiedDate + '\'' +
                    '}';
        }
    }

    private static List<Memo> memoList = new ArrayList<>();


    private static void addMemo(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.println("Enter content: ");
        String contents = scanner.nextLine();

        Memo meno = new Memo(title, contents);
        memoList.add(meno);
        System.out.println("Memo added successfully!");
    }

    private static void listMemos() {

        if (memoList.isEmpty()) {
            System.out.println("No memos found");
            return;
        }

        for (Memo memo : memoList) {
            System.out.println(memo);
        }

    }

    //검색기능 -2 => 검색은 생성된 메모들 중에 메모 찾는거.
    private static void searchMemo(Scanner scanner) {
        System.out.println("Enter keyword to search: ");
        String keyword = scanner.nextLine();

        for (Memo memo : memoList) {
            if (memo.getTitle().contains(keyword)) {
                System.out.println(memo);
            }
        }
    }

    private static void editMemo(Scanner scanner) {
        // 1. 대상을 찾는다.
        System.out.print("Enter the ID of the memo to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // 2. memoList 에서 대창을 특정하는데... 대상의 id와 입력된 id 같은 녀석.
        for (Memo memo : memoList) {
            if (memo.getId() == id) {
                System.out.println("Enter new title: ");
                String newTitle = scanner.nextLine();
                System.out.println("Enter new content: ");
                String newContent = scanner.nextLine();
                // 3. 입력받은 제목, 내용을 갱신.  updateContent(title, content)
                memo.updateContent(newTitle, newContent);
                System.out.println("Memo updated successfully!");
                return;
            }
        }
        System.out.println("Memo with the given ID not found.");
    }

    //삭제기능 -4 => 생성된 메모를 삭제하는거.
    private static void deleteMemo(Scanner scanner) {
        System.out.print("Enter the ID of the memo to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Memo memo : memoList) {
            if (memo.getId() == id) {
                memoList.remove(memo);
                System.out.println("Memo deleted successfully!");
                return;
            }
        }
        System.out.println("Memo with the given ID not found.");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Memo Application ===");
            System.out.println("1. Add Memo");
            System.out.println("2. List Memos");
            System.out.println("3. Search Memo");
            System.out.println("4. Edit Memo");
            System.out.println("5. Delete Memo");
            System.out.println("6. Exit");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMemo(scanner);
                    break;
                case 2:
                    listMemos();
                    break;
                case 3:
                    searchMemo(scanner);
                    break;
                case 4:
                    editMemo(scanner);
                    break;
                case 5:
                    deleteMemo(scanner);
                    break;
                case 6:
                    System.out.println("Thank you for using our system");
                    return;
                default:
                    System.out.println("Invalid option please try again");
                    break;
            }

        }
    }
}
