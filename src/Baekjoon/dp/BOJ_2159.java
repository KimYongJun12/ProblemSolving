package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2159 {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Point[] customers = new Point[n + 1];
        for (int i = 0; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            customers[i] = new Point(x, y);
        }

        int[] dx = {0, -1, 1, 0, 0};
        int[] dy = {0, 0, 0, -1, 1};
        long[][] dp = new long[5][n + 1];
        dp[1][0] = dp[2][0] = dp[3][0] = dp[4][0] = 1;

        for (int i = 1; i <= n; i++) {
            Point prev = customers[i - 1];
            Point now = customers[i];

            for (int j = 0; j < 5; j++) {
                int nowX = now.x + dx[j];
                int nowY = now.y + dy[j];
                dp[j][i] = Long.MAX_VALUE;
                for (int k = 0; k < 5; k++) {
                    int prevX = prev.x + dx[k];
                    int prevY = prev.y + dy[k];
                    long dist = Math.abs(nowX - prevX) + Math.abs(nowY - prevY);
                    dp[j][i] = Math.min(dp[j][i], dp[k][i - 1] + dist);
                }
            }
        }

        long min = Long.MAX_VALUE;
        for (int i = 0; i < 5; i++) min = Math.min(min, dp[i][n]);
        System.out.println(min);

    }

}