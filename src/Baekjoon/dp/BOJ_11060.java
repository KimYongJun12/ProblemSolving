package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11060 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] jump = new int[N + 1];
        int[] dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            jump[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1001;
        }

        dp[1] = 0;

        for (int i = 1; i <= N; i++) {
            int nowJump = jump[i];
            for (int j = 1; j <= nowJump; j++) {
                if (i + j > N) break;
                dp[i + j] = Math.min(dp[i] + 1, dp[i + j]);
            }
        }

        System.out.println(dp[N] == 1001 ? -1 : dp[N]);
    }
}
