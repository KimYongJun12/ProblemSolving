package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1480 {
    static int n, m, c;
    static int[] jewel;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        jewel = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) jewel[i] = Integer.parseInt(st.nextToken());

        dp = new int[c + 1][m][1 << n];

        System.out.println(dfs(c, 0, 0));
    }

    private static int dfs(int inventory, int depth, int flag) {
        if (depth == m || (flag == (1 << n) - 1)) return 0;

        if (dp[inventory][depth][flag] != 0) return dp[inventory][depth][flag];

        for (int i = 0; i < n; i++) {
            if ((flag & (1 << i)) == 0) {
                if (inventory >= jewel[i]) {
                    dp[inventory][depth][flag] = Math.max(dp[inventory][depth][flag], dfs(inventory - jewel[i], depth, flag | (1 << i)) + 1);
                } else {
                    dp[inventory][depth][flag] = Math.max(dp[inventory][depth][flag], dfs(c, depth + 1, flag));
                }
            }
        }

        return dp[inventory][depth][flag];
    }

}
