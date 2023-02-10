package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2098 {
    static int n, noCycle = 16000001, INF = noCycle * 2;
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
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], INF);
        }
//        dp[0][0] = 0;

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int now, int flag) {
        flag = (flag | (1 << now));

        if (flag == (1 << n) - 1) {
            if (cost[now][0] == 0) return noCycle;
            return cost[now][0];
        }

        if (dp[now][flag] != INF) return dp[now][flag];

        for (int i = 0; i < n; i++) {
            if (((flag & (1 << i)) == 0) && cost[now][i] != 0) {
                dp[now][flag] = Math.min(dp[now][flag], dfs(i, flag) + cost[now][i]);
            }
        }

        return dp[now][flag];
    }

}