package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486 {
    static class Pair {
        int time, pay;

        public Pair(int time, int pay) {
            this.time = time;
            this.pay = pay;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Pair[] pairs = new Pair[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            pairs[i] = new Pair(i + t - 1, p);
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (pairs[i].time <= N) {
                dp[pairs[i].time] = Math.max(dp[pairs[i].time], max + pairs[i].pay);
            }

            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
