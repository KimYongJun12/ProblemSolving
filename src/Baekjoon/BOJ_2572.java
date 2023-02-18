package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2572 {
    static int n, m, k;
    static char[] cards;
    static int[][] dp;
    static ArrayList<Road>[] graph;

    static class Road {
        int end;
        char color;

        public Road(int end, char color) {
            this.end = end;
            this.color = color;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        cards = new char[n];
        for (int i = 0; i < n; i++) cards[i] = st.nextToken().charAt(0);

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[m + 1][n + 1];
        graph = new ArrayList[m + 1];
        for (int i = 1; i <= m; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            char color = st.nextToken().charAt(0);

            graph[start].add(new Road(end, color));
            graph[end].add(new Road(start, color));
        }

        System.out.println(dfs(1, 0));

    }

    private static int dfs(int idx, int cnt) {
        if (cnt == n) {
            return dp[idx][n] = 0;
        }

        if (dp[idx][cnt] != -1) return dp[idx][cnt];

        int temp = 0;
        char nowColor = cards[cnt];

        for (Road next : graph[idx]) {
            int score = (nowColor == next.color) ? 10 : 0;
            temp = Math.max(temp, dfs(next.end, cnt + 1) + score);
        }

        return dp[idx][cnt] = temp;
    }

}