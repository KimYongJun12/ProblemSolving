package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971 {
    static int N, INF = 987654321;
    static long minCost = Long.MAX_VALUE;
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 0) graph[i][j] = INF;
            }
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, i, 1, 0L);
        }

        System.out.println(minCost);
    }

    private static void dfs(int start, int now, int depth, long cost) {
        if (depth == N) {
            if (graph[now][start] != INF) {
                minCost = Math.min(minCost, cost + graph[now][start]);
            }

            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && graph[now][i] != INF) {
                visited[i] = true;
                dfs(start, i, depth + 1, cost + graph[now][i]);
                visited[i] = false;
            }
        }
    }

}
