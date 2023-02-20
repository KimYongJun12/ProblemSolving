package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2482 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int mod = 1000000003;
        if (2 * k > n) {
            System.out.println(0);
        } else {
            int[][] dp = new int[n + 1][(n + 1) / 2 + 1];

            for (int i = 1; i <= n; i++) {
                dp[i][1] = i;
                dp[i][0] = 1;
            }
            for (int i = 3; i <= n; i++) {
                for (int j = 2; 2 * j <= n; j++) {
                    dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % mod;
                }
            }

            System.out.println((dp[n - 3][k - 1] + dp[n - 1][k]) % mod);
        }
    }

}
