package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_24444 {
    static int N;
    static PriorityQueue<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new PriorityQueue[N + 1];
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            graph[i] = new PriorityQueue<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            graph[e].add(s);
        }

        System.out.println(bfs(R));
    }

    private static StringBuilder bfs(int start) {
        int cnt = 1;
        int[] visitOrder = new int[N + 1];
        visitOrder[start] = cnt++;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int now = q.poll();
            while (!graph[now].isEmpty()) {
                int next = graph[now].poll();
                if (visitOrder[next] > 0) continue;
                visitOrder[next] = cnt++;
                q.offer(next);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visitOrder[i]).append('\n');
        }

        return sb;
    }
}
