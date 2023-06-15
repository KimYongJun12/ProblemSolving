package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_2210 {
    static int[][] board = new int[5][5];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] num = new int[6];
    static boolean[][] isFirst = new boolean[5][5];
    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < 5; i += 2) {
            for (int j = 0; j < 5; j++) {
                dfs(0, i, j);
            }
        }
        System.out.println(set.size());
    }

    private static void dfs(int depth, int x, int y) {
        if (depth == 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(num[i]);
            }

            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (!isInRange(nextX, nextY)) continue;
            if (depth == 0) {
                if (isFirst[nextX][nextY]) continue;
                else isFirst[nextX][nextY] = true;
            }
            num[depth] = board[nextX][nextY];
            dfs(depth + 1, nextX, nextY);
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }
}
