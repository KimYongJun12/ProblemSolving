package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2611 {
    static int N;
    static ArrayList<Node>[] track;
    static int[] parent;
    static int[] dp;

    static class Node {
        int end, score;

        public Node(int end, int score) {
            this.end = end;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        track = new ArrayList[N + 1];
        parent = new int[N + 1];
        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 1; i <= N; i++) track[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            q = (q == 1) ? 0 : q;
            int score = Integer.parseInt(st.nextToken());

            track[p].add(new Node(q, score));
        }

        System.out.println(findMaxScore(1));
        System.out.println(trace());
    }

    private static StringBuilder trace() {
        StringBuilder sb = new StringBuilder();

        int now = 1;
        while (now != 0) {
            sb.append(now).append(' ');
            now = parent[now];
        }
        sb.append(1);

        return sb;
    }

    private static int findMaxScore(int idx) {
        if (dp[idx] != -1) return dp[idx];

        int max = 0;

        for (Node next : track[idx]) {
            int temp = findMaxScore(next.end) + next.score;
            if (max < temp) {
                parent[idx] = next.end;
                max = temp;
            }
        }

        return dp[idx] = max;
    }
}
