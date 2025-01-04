package com.example.ch3.alog;

import java.util.LinkedList;

public class Graph {
    private int numVertices;
    private LinkedList<Integer>[] adjLists;

    //생성자
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjLists = new LinkedList[numVertices];

        for (int i = 0; i < numVertices; i++) {
            adjLists[i] = new LinkedList<>();
        }
    }

    //간선 추가 (무방향 그래프)
    public void addEdge(int src, int dest) {
        adjLists[src].add(dest);
        adjLists[dest].add(src);
    }

    //DFS 탐색
    public void dfs(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        dfsRecursive(startVertex, visited);
    }

    private void dfsRecursive(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.println(vertex + " ");

        for (int adj : adjLists[vertex]) {
            if (!visited[adj]) {
                dfsRecursive(adj, visited);
            }
        }
    }

    //테스트
    public static void main(String[] args) {
        Graph g = new Graph(5);

        /* 그래프 구조
            0
            |
            1
           / \
          2   3
               \
                4
        */

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);

        System.out.println("DFS 탐색 결과: ");
        g.dfs(0);
    }
}
