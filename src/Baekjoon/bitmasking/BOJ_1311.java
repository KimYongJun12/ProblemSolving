package Baekjoon.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1311 {
    static int n;
    static int INF = 987654321;
    static int[][] cost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        dp = new int[n][1 << n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) cost[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int num, int flag) {
        if (num == n) return 0;
        if (dp[num][flag] != 0) return dp[num][flag];

        int result = INF;
        for (int i = 0; i < n; i++) {
            if ((flag & (1 << i)) == 0)
                result = Math.min(result, cost[num][i] + dfs(num + 1, flag | (1 << i)));
        }
        dp[num][flag] = result;
        return dp[num][flag];
    }

}