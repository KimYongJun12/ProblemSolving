package Baekjoon.graph.minimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_6497 {
    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge> {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String input = " ";

        while (!(input = br.readLine()).equals("0 0")) {
            st = new StringTokenizer(input);
            pq = new PriorityQueue<>();

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int totalSum = 0;

            parent = new int[M];
            for (int i = 0; i < M; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                totalSum += cost;
                pq.offer(new Edge(start, end, cost));

            }

            int mst = findMst();
            sb.append(totalSum - mst).append('\n');
        }

        System.out.println(sb);
    }

    private static int findMst() {
        int totalCost = 0;
        int cnt = 0;
        while (cnt != M - 1) {
            Edge edge = pq.poll();

            if (union(edge.start, edge.end)) {
                cnt++;
                totalCost += edge.cost;
            }

        }

        return totalCost;
    }

    private static boolean union(int x, int y) {
        x = findParent(x);
        y = findParent(y);

        if (x == y) return false;
        if (x < y) {
            parent[y] = x;
        } else parent[x] = y;

        return true;
    }

    private static int findParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }
}