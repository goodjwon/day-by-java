package com.example.ch3.def;

import java.util.LinkedList;

public class LinkedListAddRemove {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.addFirst(10);
        list.addFirst(5);

        list.addLast(15);
        list.addLast(20);

        System.out.println("리스트: " + list);

        list.removeFirst();
        System.out.println("앞에서 삭제 후: " + list);

        list.removeLast();
        System.out.println("뒤에서 삭제 후: " + list);
    }
}
