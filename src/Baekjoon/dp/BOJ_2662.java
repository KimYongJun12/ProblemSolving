package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2662 {
    static int[][] path;
    static int[] amount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] income = new int[N + 1][M + 1];
        path = new int[N + 1][M + 1];
        amount = new int[M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= M; j++) {
                income[money][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N + 1][M + 1];

        for (int j = 1; j <= M; j++) {
            for (int i = 0; i <= N; i++) {
                for (int k = N - i; k >= 0; k--) {
                    if (dp[i + k][j] < dp[k][j - 1] + income[i][j]) {
                        dp[i + k][j] = dp[k][j - 1] + income[i][j];
                        path[i + k][j] = i;
                    }
                }
            }
        }

        findPath(N, M);
        System.out.println(dp[N][M]);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) sb.append(amount[i]).append(' ');
        System.out.println(sb);
    }

    private static void findPath(int n, int m) {
        if (m == 0) return;
        amount[m] = path[n][m];
        findPath(n - path[n][m], m - 1);

    }
}
