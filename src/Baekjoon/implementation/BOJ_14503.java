package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {
    static int N, M;
    static boolean[][] wall;
    static boolean[][] trash;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        wall = new boolean[N][M];
        trash = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (st.nextToken().equals("1")) {
                    wall[i][j] = true;
                } else {
                    trash[i][j] = true;
                }
            }
        }

        System.out.println(simulation(x, y, dir));
    }

    private static int simulation(int x, int y, int dir) {
        int cnt = 0;

        while (true) {
            if (trash[x][y]) {
                clean(x, y);
                cnt++;
            }

            int nextDir = findNearTrash(x, y, dir);

            if (nextDir == 5) {
                x -= dx[dir];
                y -= dy[dir];
                if (!isInRange(x, y) || wall[x][y]) break;
            } else {
                dir = nextDir;
                x += dx[dir];
                y += dy[dir];
            }
        }

        return cnt;
    }

    private static int findNearTrash(int x, int y, int dir) {
        for (int i = 3; i >= 0; i--) {
            int nextDir = (dir + i) % 4;
            int nextX = x + dx[nextDir];
            int nextY = y + dy[nextDir];

            if (!isInRange(nextX, nextY) || wall[nextX][nextY] || !trash[nextX][nextY]) continue;

            return nextDir;
        }

        return 5;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static void clean(int x, int y) {
        trash[x][y] = false;
    }
}
