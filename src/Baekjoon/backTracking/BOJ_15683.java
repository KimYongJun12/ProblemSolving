package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15683 {
    static int N, M, min, cnt;
    static int[][] map;
    static ArrayList<CCTV> cameras = new ArrayList<>();
    static int[] dx = {0, -1, 0, 1, 0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0, -1, 0, 1, 0};

    static class CCTV {
        int x, y, model, dir;

        public CCTV(int x, int y, int model) {
            this.x = x;
            this.y = y;
            this.model = model;
            this.dir = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cameras.add(new CCTV(i, j, map[i][j]));
                }

                if (map[i][j] != 6) cnt++;
            }
        }

        min = cnt;
        simulation(0);
        System.out.println(min);
    }

    private static void simulation(int depth) {
        if (depth == cameras.size()) {
            int blindSpot = getBlind();
            min = Math.min(min, blindSpot);
            return;
        }

        CCTV now = cameras.get(depth);
        if (now.model == 2) {
            for (int i = 0; i < 2; i++) {
                now.dir = i;
                simulation(depth + 1);
            }
        } else if (now.model == 5) {
            now.dir = 0;
            simulation(depth + 1);
        } else {
            for (int i = 0; i < 4; i++) {
                now.dir = i;
                simulation(depth + 1);
            }
        }
    }

    private static int getBlind() {
        int[][] copyMap = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        int nowCnt = cnt;

        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }

        for (CCTV now : cameras) {
            int idx = now.dir;
            nowCnt = eraseZero(copyMap, visited, now, nowCnt, idx++);
            if (now.model == 1) continue;
            if (now.model == 2) {
                nowCnt = eraseZero(copyMap, visited, now, nowCnt, idx + 1);
                continue;
            }
            nowCnt = eraseZero(copyMap, visited, now, nowCnt, idx++);
            if (now.model == 3) continue;
            nowCnt = eraseZero(copyMap, visited, now, nowCnt, idx++);
            if (now.model == 4) continue;
            nowCnt = eraseZero(copyMap, visited, now, nowCnt, idx);
        }

        return nowCnt;
    }

    private static int eraseZero(int[][] copyMap, boolean[][] visited, CCTV now, int nowCnt, int idx) {
        int nextX = now.x;
        int nextY = now.y;

        while (isInRange(nextX, nextY) && copyMap[nextX][nextY] != 6) {
            if (!visited[nextX][nextY]) {
                nowCnt--;
                visited[nextX][nextY] = true;
            }

            nextX += dx[idx];
            nextY += dy[idx];
        }

        return nowCnt;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
