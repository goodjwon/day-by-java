package com.example.ch4.toy;

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

        private static String getCurrentDateTime(){
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        //수정기능 -3 => 기존에 생성된 메모를 수정.
        public void rewrite() {
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


    private static void addMemo(Scanner scanner){
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.println("Enter content: ");
        String contents = scanner.nextLine();

        Memo meno = new Memo(title, contents);
        memoList.add(meno);
    }

    private static void listMemos() {

    }

    //검색기능 -2 => 검색은 생성된 메모들 중에 메모 찾는거.
    private static void searchMemo(){

    }

    private static void editMemo(){

    }

    //삭제기능 -4 => 생성된 메모를 삭제하는거.
    private static void deleteMemo(){

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        addMemo(scanner);

        for(Memo memo:memoList){
            System.out.println(memo);
        }
    }
}
