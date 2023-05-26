package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int mod = 9901;

        int[][] dp = new int[N + 1][3]; // dp[i번째 줄][0 : 사자X, 1 : 1번 칸 사자O, 2 : 2번 칸 사자O]
        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = dp[i - 1][0];
                if (j != 1) dp[i][j] += dp[i - 1][1];
                if (j != 2) dp[i][j] += dp[i - 1][2];
                dp[i][j] %= mod;
            }
        }

        int ans = (dp[N][0] + dp[N][1] + dp[N][2]) % mod;
        System.out.println(ans);
    }
}
