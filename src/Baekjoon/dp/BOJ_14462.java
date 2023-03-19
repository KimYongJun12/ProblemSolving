package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14462 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] left = new int[n + 1];
        int[] right = new int[n + 1];

        for (int i = 1; i <= n; i++) left[i] = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) right[i] = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (Math.abs(left[i] - right[j]) <= 4) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        System.out.println(dp[n][n]);
    }
}
