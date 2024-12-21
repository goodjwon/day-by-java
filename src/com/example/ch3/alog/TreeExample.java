package com.example.ch3.alog;

public class TreeExample {
    public static void main(String[] args) {
        //노드 생성
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        //트리 연결
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        // 이제 node1이 루트 노드이며, 주어진 구조로 트리가 구성됨
        // 예: 루트: 1
        //      /     \
        //     2       3
        //    / \       \
        //   4   5       6

        //트리 순회 예제 (전위 순회)
        preOrderTraversal(node1);
    }

    public static void preOrderTraversal(TreeNode node) {
        if (node == null) return;
        System.out.println(node.data + " ");
        preOrderTraversal(node.right);
        preOrderTraversal(node.left);
    }
}
