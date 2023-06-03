package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] diff = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            diff[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][S] = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                if (dp[i][j]) {
                    if (j - diff[i] >= 0) {
                        dp[i + 1][j - diff[i]] = true;
                    }
                    if (j + diff[i] <= M) {
                        dp[i + 1][j + diff[i]] = true;
                    }
                }
            }
        }

        int ans = -1;
        for (int i = M; i >= 0; i--) {
            if (dp[N][i]) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }
}
