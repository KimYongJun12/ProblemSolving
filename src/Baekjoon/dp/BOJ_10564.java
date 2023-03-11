package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_10564 {
    static int[][] dp;
    static HashSet<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            dp = new int[n + 1][101];
            for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1);
            set = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int score = Integer.parseInt(st.nextToken());
                set.add(score);
            }

            int ans = memoization(n, 1);
            ans = (ans < 0) ? -1 : ans;
            sb.append(ans).append('\n');
        }

        System.out.println(sb);
    }

    private static int memoization(int cnt, int turn) {
        if (cnt == 0) return 0;
        if (dp[cnt][turn] != -1) return dp[cnt][turn];
        dp[cnt][turn] = -987654321;

        for (int score : set) {
            int next = cnt - turn * score;
            if (next >= 0) dp[cnt][turn] = Math.max(dp[cnt][turn], memoization(next, turn + 1) + score);
        }

        return dp[cnt][turn];
    }
}
