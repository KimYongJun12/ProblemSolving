package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_24480 {
    static int N, cnt = 1;
    static PriorityQueue<Integer>[] graph;
    static int[] visitOrder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new PriorityQueue[N + 1];
        visitOrder = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new PriorityQueue<>(Comparator.reverseOrder());
        }

        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph[s].offer(e);
            graph[e].offer(s);
        }

        dfs(R);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visitOrder[i]).append('\n');
        }

        System.out.println(sb);
    }

    private static void dfs(int now) {
        visitOrder[now] = cnt++;

        while (!graph[now].isEmpty()) {
            int next = graph[now].poll();
            if (visitOrder[next] == 0) dfs(next);
        }
    }
}
