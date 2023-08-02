package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] weights = new int[N];
        boolean[][] dp = new boolean[N + 1][40001];
        dp[0][0] = true;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        dp[1][weights[0]] = true;

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= 15000; j++) {
                if (dp[i][j]) {
                    dp[i + 1][j] = true;
                    dp[i + 1][j + weights[i]] = true;
                    dp[i + 1][Math.abs(j - weights[i])] = true;
                }
            }
            dp[i + 1][weights[i]] = true;
        }

        int ball = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (ball-- > 0) {
            int value = Integer.parseInt(st.nextToken());
            sb.append(dp[N][value] ? 'Y' : 'N').append(' ');
        }

        System.out.println(sb);
    }
}