package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1513 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int mod = 1000007;

        int[][] map = new int[N + 1][M + 1];
        // dp[r][c][오락실 번호 최대값][지나온 오락실의 개수]
        int[][][][] dp = new int[N + 1][M + 1][C + 1][C + 1];
        dp[1][1][0][0] = 1;

        for (int i = 1; i <= C; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (r == 1 && c == 1) {
                dp[1][1][0][0] = 0;
                dp[1][1][i][1] = 1;
            }

            map[r][c] = i;
        }


        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (i == 1 && j == 1) continue;

                if (map[i][j] > 0) {
                    for (int k = 0; k < map[i][j]; k++) {
                        for (int l = 0; l <= k; l++) {
                            dp[i][j][map[i][j]][l + 1] += dp[i - 1][j][k][l] + dp[i][j - 1][k][l];
                            dp[i][j][map[i][j]][l + 1] %= mod;
                        }
                    }
                } else {
                    for (int k = 0; k <= C; k++) {
                        for (int l = 0; l <= k; l++) {
                            dp[i][j][k][l] = dp[i][j - 1][k][l] + dp[i - 1][j][k][l];
                            dp[i][j][k][l] %= mod;
                        }
                    }
                }
            }
        }

        for (int i = 0; i <= C; i++) {
            int sum = 0;
            for (int j = 0; j <= C; j++) sum += dp[N][M][j][i];
            sum %= mod;
            sb.append(sum).append(' ');
        }

        System.out.println(sb);
    }
}