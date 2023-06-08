package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int kx = (K % M == 0) ? K / M : K / M + 1, ky = (K % M == 0) ? M : K % M;
        int[][] dp = new int[N + 1][M + 1];
        Arrays.fill(dp[0], 1);
        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (j <= 1) {
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        int ans;

        if (K == 0) {
            ans = dp[N][M];
        } else {
            ans = dp[kx][ky] * dp[N - kx + 1][M - ky + 1];
        }

        System.out.println(ans);
    }
}
