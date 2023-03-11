package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1949 {
    static ArrayList<Integer>[] tree;
    static int[] people;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        people = new int[n + 1];
        dp = new int[n + 1][2];

        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) people[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            tree[start].add(end);
            tree[end].add(start);
        }

        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int node, int parent) {
        dp[node][1] = people[node];

        for (int next : tree[node]) {
            if (next == parent) continue;
            dfs(next, node);
            dp[node][0] += Math.max(dp[next][1], dp[next][0]);
            dp[node][1] += dp[next][0];
        }
    }

}