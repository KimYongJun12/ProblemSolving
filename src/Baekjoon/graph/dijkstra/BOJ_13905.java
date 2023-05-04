package Baekjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13905 {
    static int N;
    static ArrayList<Bridge>[] graph;

    static class Bridge implements Comparable<Bridge> {
        int end;
        int cost;

        public Bridge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bridge o) {
            return o.cost - this.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Bridge(end, cost));
            graph[end].add(new Bridge(start, cost));
        }

        System.out.println(dijkstra(s, e));
    }

    private static int dijkstra(int s, int e) {
        PriorityQueue<Bridge> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        dist[s] = 1000001;
        pq.offer(new Bridge(s, 1000001));

        while (!pq.isEmpty()) {
            Bridge now = pq.poll();
            int start = now.end;
            int cost = now.cost;

            for (Bridge next : graph[start]) {
                int minCost = Math.min(cost, next.cost) ;
                if (dist[next.end] < minCost) {
                    dist[next.end] = minCost;
                    pq.offer(new Bridge(next.end, minCost));
                }
            }
        }

        return dist[e];
    }
}
