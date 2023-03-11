package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1029 {
    static int n;
    static int[][] cost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        dp = new int[n][1 << n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                cost[i][j] = s.charAt(j) - '0';
            }
        }


        System.out.println(dfs(0, 0, 0) + 1);
    }

    private static int dfs(int now, int nowCost, int flag) {
        flag = (flag | (1 << now));

        if (now == n) return 0;
        if (dp[now][flag] != 0) return dp[now][flag];

        for (int i = 0; i < n; i++) {
            if ((flag & (1 << i)) == 0 && cost[now][i] >= nowCost) {
                dp[now][flag] = Math.max(dp[now][flag], dfs(i, cost[now][i], flag) + 1);
            }
        }

        return dp[now][flag];
    }
}