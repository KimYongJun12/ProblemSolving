package Baekjoon.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1663 {
    static long[][] dp;
    static String[] base = new String[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int query = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        dp = new long[101][3];
        dp[1][0] = 1;
        base[1] = "X";
        base[2] = "YZ";
        base[3] = "ZX";

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][2];
            dp[i][1] = dp[i - 1][0];
            dp[i][2] = dp[i - 1][0] + dp[i - 1][1];
        }

        if (query == 1) {
            System.out.println(dp[n][0] + dp[n][1] + dp[n][2]);
        } else if (query == 2) {
            long idx = Long.parseLong(br.readLine());
            System.out.println(findWord(n, idx));
        } else {
            int alphabet = br.readLine().charAt(0) - 'X';
            System.out.println(dp[n][alphabet]);
        }
    }

    private static char findWord(int depth, long idx) {
        if (depth <= 3) return base[depth].charAt((int) (idx - 1));
        long leftStringLength = 0;
        for (int i = 0; i < 3; i++) leftStringLength += dp[depth - 3][i];

        if (idx <= leftStringLength) return findWord(depth - 3, idx);
        else return findWord(depth - 2, idx - leftStringLength);
    }
}