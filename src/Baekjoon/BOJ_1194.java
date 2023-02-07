package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194 {
    static int h, w;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x, y, keys, move;

        public Node(int x, int y, int keys, int move) {
            this.x = x;
            this.y = y;
            this.keys = keys;
            this.move = move;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new char[h][w];

        int startX = 0, startY = 0;

        for (int i = 0; i < h; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                if (map[i][j] == '0') {
                    startX = i;
                    startY = j;
                }
            }
        }

        System.out.println(bfs(startX, startY));
    }

    private static int bfs(int startX, int startY) {
        boolean[][][] visited = new boolean[h][w][1 << 6];
        visited[startX][startY][0] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startX, startY, 0, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();


            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                int nextKeys = now.keys;

                if (!isInRange(nextX, nextY) || map[nextX][nextY] == '#' || visited[nextX][nextY][now.keys]) continue;
                if (map[nextX][nextY] == '1') return now.move + 1;
                if (map[nextX][nextY] >= 'A' && map[nextX][nextY] <= 'F') {
                    if ((now.keys & (1 << (map[nextX][nextY] - 'A'))) == 0) continue;
                }

                if (map[nextX][nextY] >= 'a') {
                    nextKeys = nextKeys | (1 << (map[nextX][nextY] - 'a'));
                    visited[nextX][nextY][now.keys] = true;
                }

                visited[nextX][nextY][nextKeys] = true;
                q.offer(new Node(nextX, nextY, nextKeys, now.move + 1));
            }

        }

        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }

}
