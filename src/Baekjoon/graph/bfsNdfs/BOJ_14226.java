package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_14226 {
    static class Node implements Comparable<Node> {
        int value, clipBoard, time;

        public Node(int value, int clipBoard, int time) {
            this.value = value;
            this.clipBoard = clipBoard;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());

        System.out.println(bfs(S));
    }

    private static int bfs(int s) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[2001][2001];
        visited[1][0] = true;
        q.offer(new Node(1,  0, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.value == s) {
                return now.time;
            }

            if (now.value > 0) {
                if (!visited[now.value][now.value]) {
                    q.offer(new Node(now.value, now.value, now.time + 1));
                    visited[now.value][now.value] = true;
                }

                int nextValue = now.value - 1;
                if (!visited[nextValue][now.clipBoard]) {
                    q.offer(new Node(nextValue, now.clipBoard, now.time + 1));
                    visited[nextValue][now.clipBoard] = true;
                }
            }

            if (now.clipBoard > 0 && now.value + now.clipBoard < 2000) {
                int nextValue = now.value + now.clipBoard;
                if (!visited[nextValue][now.clipBoard]) {
                    q.offer(new Node(nextValue, now.clipBoard, now.time + 1));
                    visited[nextValue][now.clipBoard] = true;
                }
            }
        }

        return 0;
    }
}
