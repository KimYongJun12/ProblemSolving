package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1563 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int mod = 1_000_000;

        // dp[날짜][지각 횟수][연속 결석 횟수]
        // 지각 횟수 + 연속 결석 횟수 <= 날짜여야 함
        // 오늘이 출석이라면 dp[n][m][0] = dp[n - 1][m][0 ~ 2]
        // 오늘이 지각이라면 dp[n][m][0] = dp[n - 1][m - 1][0 ~ 2]
        // 오늘이 결석이라면 dp[n][m][o] = dp[n - 1][m][o - 1] (o = 1 ~ 2)
        // dp[n][m][0] = dp[n - 1][m][0 ~ 2] + dp[n - 1][m - 1][0 ~ 2](m == 1인 경우)
        // dp[n][m][1 ~ 2] = dp[n - 1][m][0 ~ 1]

        int[][][] dp = new int[N + 1][2][3];

        dp[1][0][0] = dp[1][1][0] = dp[1][0][1] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j + k > i) break;
                    dp[i][j][0] += dp[i - 1][j][k];

                    if (j > 0) {
                        dp[i][j][0] += dp[i - 1][j - 1][k];
                    }

                    if (k > 0) {
                        dp[i][j][k] += dp[i - 1][j][k - 1];
                    }

                    dp[i][j][k] %= mod;
                }

            }
        }

        int ans = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                ans += dp[N][i][j];
                ans %= mod;
            }
        }

        System.out.println(ans);
    }
}
