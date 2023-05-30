package Baekjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18352 {
    static int N, INF = 1_000_001;
    static ArrayList<Integer>[] graph;

    static class Node implements Comparable<Node> {
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
        }

        System.out.println(dijkstra(X, K));
    }

    private static StringBuilder dijkstra(int start, int k) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.end] < now.cost) continue;

            for (int next : graph[now.end]) {
                int cost = dist[now.end] + 1;
                if (cost < dist[next]) {
                    dist[next] = cost;
                    pq.offer(new Node(next, cost));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean isFilled = false;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == k) {
                sb.append(i).append('\n');
                isFilled = true;
            }
        }

        if (!isFilled) sb.append(-1).append('\n');

        return sb;
    }
}
