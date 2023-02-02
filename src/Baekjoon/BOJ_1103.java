package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1103 {
    static int n, m, maxCount = 0;
    static boolean cycled = false;
    static char[][] board;
    static int[][] dp;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            board[i] = s.toCharArray();
        }

        visited[0][0] = true;

        dfs(0, 0, 1);
        if (cycled) maxCount = -1;
        System.out.println(maxCount);

    }

    private static void dfs(int x, int y, int count) {
        int move = board[x][y] - '0';
        dp[x][y] = count;

        maxCount = Math.max(maxCount, count);

        for (int i = 0; i < 4; i++) {
            int nextX = x + (move * dx[i]);
            int nextY = y + (move * dy[i]);

            if (!isInRange(nextX, nextY) || board[nextX][nextY] == 'H' || dp[nextX][nextY] > count) continue;

            if (visited[nextX][nextY]) {
                cycled = true;
                return;
            }

            visited[nextX][nextY] = true;
            dfs(nextX, nextY, count + 1);
            visited[nextX][nextY] = false;
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }


}
