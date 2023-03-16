package Baekjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2307 {
    static int N, M, INF = 500000001;
    static ArrayList<Road>[] graph;
    static int[] previous;

    static class Road implements Comparable<Road> {
        int dir, time;

        public Road(int dir, int time) {
            this.dir = dir;
            this.time = time;
        }

        @Override
        public int compareTo(Road o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        previous = new int[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph[start].add(new Road(end, time));
            graph[end].add(new Road(start, time));
        }

        int shortestTime = dijkstra();
        int now = N;
        int prev = previous[now];
        int max = 0;

        while (prev != 0) {
            int nowTime = noRoadDijkstra(prev, now);
            if (nowTime >= INF) {
                max = -1;
                break;
            }

            max = Math.max(max, nowTime - shortestTime);
            now = prev;
            prev = previous[now];
        }

        System.out.println(max);
    }

    private static int dijkstra() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1, 0));

        while (!pq.isEmpty()) {
            Road now = pq.poll();

            if (dist[now.dir] < now.time) continue;

            for (Road next : graph[now.dir]) {
                int cost = dist[now.dir] + next.time;
                if (cost < dist[next.dir]) {
                    dist[next.dir] = cost;
                    pq.offer(new Road(next.dir, cost));
                    previous[next.dir] = now.dir;
                }
            }
        }

        return dist[N];
    }

    private static int noRoadDijkstra(int start, int end) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1, 0));

        while (!pq.isEmpty()) {
            Road now = pq.poll();

            if (dist[now.dir] < now.time) continue;

            for (Road next : graph[now.dir]) {
                if (now.dir == start && next.dir == end) continue;
                if (now.dir == end && next.dir == start) continue;
                int cost = dist[now.dir] + next.time;
                if (cost < dist[next.dir]) {
                    dist[next.dir] = cost;
                    pq.offer(new Road(next.dir, cost));
                    previous[next.dir] = now.dir;
                }
            }
        }

        return dist[N];
    }

}
