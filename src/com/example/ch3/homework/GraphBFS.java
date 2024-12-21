package com.example.ch3.homework;

import java.util.LinkedList;
import java.util.Queue;

class GraphBFS {
    private int numVertices;
    private LinkedList<Integer>[] adjList;

    //생산자
    public GraphBFS(int numVertices) {
        this.numVertices = numVertices;
        adjList = new LinkedList[numVertices];

        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    //간선 추가
    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
        //무방향 그래프인 경우 아래 줄 추가
        //adjList[dest].add(src);
    }

    //BFS 탐색
    public void bfs(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.println(vertex + " ");

            for (int adj : adjList[vertex]) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    queue.add(adj);
                }
            }
        }
    }

    //테스트
    public static void main(String[] args) {
        GraphBFS g = new GraphBFS(5);

        /* 그래프 구조
            0
            |
            1
           / \\
          2   3
               \\
                4
        */

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);

        System.out.println("BFS 탐색 결과: ");
        g.bfs(0); //출력: 0 1 2 3 4
    }
}
