package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9205 {
    static int N;
    static Node home, goal;
    static Node[] store;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            home = new Node(x, y);

            store = new Node[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());

                store[i] = new Node(x, y);
            }

            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            goal = new Node(x, y);
            sb.append(bfs());
        }

        System.out.println(sb);
    }

    private static String bfs() {
        boolean[] visited = new boolean[N];
        Queue<Node> q = new LinkedList<>();
        q.offer(home);

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (Math.abs(now.x - goal.x) + Math.abs(now.y - goal.y) <= 1000) {
                return "happy\n";
            }

            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;
                int dist = Math.abs(now.x - store[i].x) + Math.abs(now.y - store[i].y);
                if (dist <= 1000) {
                    q.offer(store[i]);
                    visited[i] = true;
                }
            }
        }

        return "sad\n";
    }
}
