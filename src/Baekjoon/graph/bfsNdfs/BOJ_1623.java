package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1623 {
    static int n;
    static int[] score;
    static ArrayList<Integer>[] tree;
    static int[][] dp;
    static boolean[] check;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        score = new int[n + 1];
        check = new boolean[n + 1];

        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) score[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            tree[parent].add(i);
        }

        dp = new int[n + 1][2];

        dfs(1);

        sb.append(dp[1][0]).append(' ').append(dp[1][1]).append('\n');
        list = new ArrayList<>();
        trace(1, 0);
        Collections.sort(list);
        for (int idx : list) sb.append(idx).append(' ');
        sb.append("-1").append('\n');
        list.clear();
        trace(1, 1);
        Collections.sort(list);
        for (int idx : list) sb.append(idx).append(' ');
        sb.append("-1");
        System.out.println(sb);
    }

    private static void trace(int parent, int isCome) {
        if (isCome == 0) {
            list.add(parent);
        }

        for (int next : tree[parent]) {
            if (isCome == 0) {
                trace(next, 1);
            } else {
                if (check[next]) trace(next, 0);
                else trace(next, 1);
            }
        }
    }


    private static void dfs(int idx) {
        dp[idx][0] += score[idx];
        check[idx] = true;

        for (int next : tree[idx]) {
            dfs(next);
            dp[idx][0] += dp[next][1];

            if (dp[next][0] > dp[next][1]) {
                check[next] = true;
                dp[idx][1] += dp[next][0];
            } else {
                check[next] = false;
                dp[idx][1] += dp[next][1];
            }
        }
    }
}
