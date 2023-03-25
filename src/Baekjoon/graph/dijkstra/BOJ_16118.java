package Baekjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16118 {
    static int N;
    static long INF = 40000000001L;
    static ArrayList<Node>[] stubs;

    static class Node implements Comparable<Node> {
        int end, isFast;
        long time;

        public Node(int end, long time) {
            this.end = end;
            this.time = time;
        }

        public Node(int end, long time, int isFast) {
            this.end = end;
            this.time = time;
            this.isFast = isFast;
        }

        @Override
        public int compareTo(Node o) {
            if (this.time < o.time) return -1;
            else return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        stubs = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) stubs[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long dist = 2 * Long.parseLong(st.nextToken());
            stubs[start].add(new Node(end, dist));
            stubs[end].add(new Node(start, dist));
        }
        long[] foxTime = new long[N + 1];
        long[][] wolfTime = new long[2][N + 1];

        dijkstraFox(foxTime);
        dijkstraWolf(wolfTime);

        int cnt = 0;
        for (int i = 2; i <= N; i++) {
            if (foxTime[i] < Math.min(wolfTime[0][i], wolfTime[1][i])) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static void dijkstraFox(long[] time) {
        Arrays.fill(time, INF);
        time[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (time[now.end] < now.time) continue;

            for (Node next : stubs[now.end]) {
                long cost = time[now.end] + next.time;
                if (time[next.end] > cost) {
                    time[next.end] = cost;
                    pq.offer(new Node(next.end, cost));
                }
            }
        }

    }

    private static void dijkstraWolf(long[][] time) {
        for (int i = 0; i < 2; i++) Arrays.fill(time[i], INF);
        time[0][1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int isFast = now.isFast;

            if (time[isFast][now.end] < now.time) continue;

            for (Node next : stubs[now.end]) {
                int nextFast = 1 - isFast;  // 1 - 0 / 0 - 1 변경
                long cost = time[isFast][now.end] + ((isFast == 0) ? next.time / 2 : next.time * 2);
                if (time[nextFast][next.end] > cost) {
                    time[nextFast][next.end] = cost;
                    pq.offer(new Node(next.end, cost, nextFast));
                }
            }
        }

    }

}
