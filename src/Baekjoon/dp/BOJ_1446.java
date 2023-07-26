package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1446 {
    static class ShortCut implements Comparable<ShortCut> {
        int start, end, cost;

        public ShortCut(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(ShortCut o) {
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] dp = new int[D + 1];
        ShortCut[] shortCuts = new ShortCut[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            shortCuts[i] = new ShortCut(start, end, cost);
        }

        Arrays.sort(shortCuts);

        int idx = 0;
        for (int i = 1; i <= D; i++) {
            dp[i] = i;
            if (idx < N) {
                if (shortCuts[idx].end == i) {
                    while (idx < N && shortCuts[idx].end == i) {
                        ShortCut now = shortCuts[idx];
                        dp[i] = Math.min(dp[i], dp[now.start] + now.cost);
                        idx++;
                    }
                }
            }

            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
        }
        System.out.println(dp[D]);
    }
}
