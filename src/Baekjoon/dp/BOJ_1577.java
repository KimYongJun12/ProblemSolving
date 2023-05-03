package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1577 {
    static class Road {
        int x, y;

        public Road(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Road>[][] city = new ArrayList[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                city[i][j] = new ArrayList<>();
                if (i != N) city[i][j].add(new Road(i + 1, j));
                if (j != M) city[i][j].add(new Road(i, j + 1));
            }
        }

        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int startX = (a < c || b < d) ? a : c;
            int startY = (a < c || b < d) ? b : d;
            int endX = (a < c || b < d) ? c : a;
            int endY = (a < c || b < d) ? d : b;

            for (Road now : city[startX][startY]) {
                if (now.x == endX && now.y == endY) {
                    city[startX][startY].remove(now);
                    break;
                }
            }
        }

        long[][] dp = new long[N + 1][M + 1];
        dp[0][0] = 1;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                for (Road r : city[i][j]) {
                    dp[r.x][r.y] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}
