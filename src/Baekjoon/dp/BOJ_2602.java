package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2602 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String order = br.readLine();
        char[][] bridge = new char[2][];
        for (int i = 0; i < 2; i++) {
            bridge[i] = br.readLine().toCharArray();
        }

        int orderLen = order.length();
        int len = bridge[0].length;

        int[][][] dp = new int[2][len][orderLen];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < len; j++) {
                if (bridge[i][j] == order.charAt(0)) dp[i][j][0] = 1;
            }
        }

        for (int i = 0; i < orderLen - 1; i++) {
            char now = order.charAt(i);
            char next = order.charAt(i + 1);

            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < len; k++) {
                    if (bridge[j][k] == now) {
                        for (int l = k + 1; l < len; l++) {
                            if (bridge[1 - j][l] == next) {
                                dp[1 - j][l][i + 1] += dp[j][k][i];
                            }
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < len; j++) {
                ans += dp[i][j][orderLen - 1];
            }
        }

        System.out.println(ans);
    }
}
