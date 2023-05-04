package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1939 {
    static int N;
    static ArrayList<Bridge>[] graph;

    static class Bridge {
        int end, cost;

        public Bridge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

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

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int min = 0, max = 1000000001;

        while (min < max) {
            int mid = (min + max) / 2;

            if (bfs(mid, s, e)) {
                min = mid + 1;
            } else max = mid;
        }

        System.out.println(min - 1);
    }

    private static boolean bfs(int mid, int s, int e) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[s] = true;
        q.offer(s);

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == e) return true;

            for (Bridge next : graph[now]) {
                if (visited[next.end] || next.cost < mid) continue;
                q.offer(next.end);
                visited[next.end] = true;
            }
        }

        return false;
    }
}
