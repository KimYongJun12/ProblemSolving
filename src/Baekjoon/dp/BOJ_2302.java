package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] dp = new int[41];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= 40; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        int prevVip = 0;
        int ans = 1;
        for (int i = 0; i < M; i++) {
            int vip = Integer.parseInt(br.readLine());
            ans *= dp[vip - prevVip - 1];

            prevVip = vip;
        }
        ans *= dp[N - prevVip];

        System.out.println(ans);
    }
}
