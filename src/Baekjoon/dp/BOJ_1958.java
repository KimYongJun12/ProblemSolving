package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1958 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] sequences = new char[3][];
        for (int i = 0; i < 3; i++) {
            sequences[i] = br.readLine().toCharArray();
        }

        int len1 = sequences[0].length;
        int len2 = sequences[1].length;
        int len3 = sequences[2].length;

        int[][][] dp = new int[len1 + 1][len2 + 1][len3 + 1];

        for (int i = 1; i <= len1; i++) {
            char c1 = sequences[0][i - 1];
            for (int j = 1; j <= len2; j++) {
                char c2 = sequences[1][j - 1];
                for (int k = 1; k <= len3; k++) {
                    char c3 = sequences[2][k - 1];

                    if (c1 == c2 && c2 == c3) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i][j][k - 1], Math.max(dp[i][j - 1][k], dp[i - 1][j][k]));
                    }
                }
            }
        }

        System.out.println(dp[len1][len2][len3]);
    }
}
