package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2698 {
    static int[][][] dp = new int[2][101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 101; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        dp[0][0][0] = dp[1][0][0] = 0;
        dp[0][1][0] = dp[1][1][0] = 1;
        dp[0][2][0] = 2;
        dp[1][2][0] = dp[1][2][1] = 1;

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            sb.append(memo(0, n, k) + memo(1, n, k)).append('\n');
        }

        System.out.println(sb);
    }

    private static int memo(int bit, int n, int k) {
        if (n < 0 || k < 0) return 0;
        if (dp[bit][n][k] != -1) return dp[bit][n][k];

        if (bit == 0) return dp[bit][n][k] = memo(0, n - 1, k) + memo(1, n - 1, k);
        else return dp[bit][n][k] = memo(0, n - 1, k) + memo(1, n - 1, k - 1);
    }
}
