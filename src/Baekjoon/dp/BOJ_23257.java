package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_23257 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        boolean[][] dp = new boolean[M + 1][1 << 10];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Math.abs(Integer.parseInt(st.nextToken()));
            dp[1][arr[i]] = true;
        }

        for (int i = 1; i < M; i++) {
            for (int j = 0; j < (1 << 10); j++) {
                if (dp[i][j]) {
                    for (int k = 0; k < N; k++) {
                        dp[i + 1][j ^ arr[k]] = true;
                    }
                }
            }
        }

        for (int i = (1 << 10) - 1; i >= 0; i--) {
            if (dp[M][i]) {
                System.out.println(i);
                break;
            }
        }
    }
}
