package Baekjoon.graph.bellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1738 {
    static int n, INF = -2_000_001;
    static ArrayList<Edge>[] graph;

    static class Edge {
        int end, cost;

        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Edge(end, cost));
        }

        System.out.println(bellmanFord());
    }

    private static StringBuilder bellmanFord() {
        StringBuilder sb = new StringBuilder();
        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];

        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Edge next : graph[j]) {
                    if (dist[next.end] < dist[j] + next.cost) {
                        if (i == n - 1 && isOnPath(next.end)) {
                            return sb.append("-1");
                        }
                        dist[next.end] = dist[j] + next.cost;
                        parent[next.end] = j;
                    }
                }
            }
        }

        if (dist[n] == INF) return sb.append("-1");

        int ans = n;
        Stack<Integer> stack = new Stack<>();
        while (ans != 0) {
            stack.push(ans);
            ans = parent[ans];
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }

        return sb;
    }

    private static boolean isOnPath(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        q.offer(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (Edge next : graph[now]) {
                if (!visited[next.end]) {
                    q.offer(next.end);
                    visited[next.end] = true;
                }
            }
        }

        return visited[n];
    }
}
