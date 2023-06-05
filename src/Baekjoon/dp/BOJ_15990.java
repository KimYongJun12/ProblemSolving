package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15990 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long mod = 1_000_000_009;
        long[][] dp = new long[100001][4];
        dp[1][1] = dp[2][2] = dp[3][3] = 1L;
        dp[3][1] = dp[2][2] + dp[2][3];
        dp[3][2] = dp[1][1] + dp[1][3];

        for (int i = 4; i <= 100000; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    if (j == k) continue;
                    dp[i][j] += dp[i - j][k];
                }
                dp[i][j] %= mod;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int now = Integer.parseInt(br.readLine());
            sb.append(((dp[now][1] % mod)+ (dp[now][2] % mod) + (dp[now][3] % mod)) % mod).append('\n');
        }

        System.out.println(sb);
    }
}
