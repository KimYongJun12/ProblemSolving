package Baekjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485 {
    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node implements Comparable<Node> {
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String input = "";
        int num = 1;
        while (!(input = br.readLine()).equals("0")) {
            N = Integer.parseInt(input);

            int[][] cave = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem ").append(num++).append(": ").append(dijkstra(cave)).append('\n');
        }

        System.out.println(sb);
    }

    private static int dijkstra(int[][] cave) {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], 987654321);
        dist[0][0] = cave[0][0];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!IsInRange(nextX, nextY)) continue;
                int nowCost = dist[now.x][now.y] + cave[nextX][nextY];
                if (dist[nextX][nextY] > nowCost) {
                    dist[nextX][nextY] = nowCost;
                    pq.offer(new Node(nextX, nextY, nowCost));
                }
            }
        }

        return dist[N - 1][N - 1];
    }

    private static boolean IsInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
