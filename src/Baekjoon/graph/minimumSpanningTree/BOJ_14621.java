package Baekjoon.graph.minimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14621 {
    static int N;
    static char[] sex;
    static int[] parent;
    static PriorityQueue<Edge> edges = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        sex = new char[N + 1];
        parent = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sex[i] = st.nextToken().charAt(0);
        }

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(start, end, weight));
        }

        System.out.println(findMinLength());
    }

    private static int findMinLength() {
        int cnt = 0;
        int length = 0;

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();

            if (sex[edge.start] == sex[edge.end] || find(edge.start) == find(edge.end)) continue;
            union(edge.start, edge.end);
            cnt++;
            length += edge.weight;
        }

        return cnt == N - 1 ? length : -1;
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) parent[y] = x;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
