package Baekjoon.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17182 {
    static int N;
    static int[][] cost;

    static class Node {
        int check, planet, time;

        public Node(int check, int planet, int time) {
            this.check = check;
            this.planet = planet;
            this.time = time;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        cost = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        System.out.println(bfs(K));
    }

    private static int bfs(int k) {
        int[][] dp = new int[N][(1 << N)];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], 999999);

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node((1 << k), k, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.check == (1 << N) - 1) continue;

            for (int i = 0; i < N; i++) {
                if (i == now.planet) continue;
                int nextCheck = (now.check | (1 << i));

                if (dp[i][nextCheck] > now.time + cost[now.planet][i]) {
                    dp[i][nextCheck] = now.time + cost[now.planet][i];
                    q.offer(new Node(nextCheck, i, dp[i][nextCheck]));
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) min = Math.min(min, dp[i][(1 << N) - 1]);
        return min;
    }

}
