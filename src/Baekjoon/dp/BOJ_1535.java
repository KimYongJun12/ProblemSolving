package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1535 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] hpLoss = new int[N + 1];
        int[] happiness = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            hpLoss[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            happiness[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][100];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < 100; j++) {
                if (j < hpLoss[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - hpLoss[i]] + happiness[i]);
                }
            }
        }

        System.out.println(dp[N][99]);
    }
}
