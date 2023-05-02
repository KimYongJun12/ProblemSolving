package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Node> water = new LinkedList<>();

    static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        Node hedgeHog = null;

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '*') water.offer(new Node(i, j, 0));
                else if (map[i][j] == 'S') {
                    hedgeHog = new Node(i, j, 0);
                    map[i][j] = '.';
                }
            }
        }

        System.out.println(simulation(hedgeHog));
    }

    private static String simulation(Node hedgeHog) {
        Queue<Node> hedge = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        visited[hedgeHog.x][hedgeHog.y] = true;
        hedge.offer(hedgeHog);

        while (!hedge.isEmpty()) {
            Node now = hedge.poll();
            if (map[now.x][now.y] == 'D') return String.valueOf(now.time);
            waterFlow(now.time);

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || visited[nextX][nextY] || map[nextX][nextY] == '*' || map[nextX][nextY] == 'X') continue;
                hedge.offer(new Node(nextX, nextY, now.time + 1));
                visited[nextX][nextY] = true;
            }
        }
        return "KAKTUS";
    }

    private static void waterFlow(int time) {
        while (!water.isEmpty() && water.peek().time == time) {
            Node nowWater = water.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = nowWater.x + dx[i];
                int nextY = nowWater.y + dy[i];

                if (!isInRange(nextX, nextY) || map[nextX][nextY] != '.') continue;
                water.offer(new Node(nextX, nextY, nowWater.time + 1));
                map[nextX][nextY] = '*';
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
