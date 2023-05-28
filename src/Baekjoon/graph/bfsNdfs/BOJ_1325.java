package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1325 {
    static int N, M;
    static ArrayList<Integer>[] graph;

    static class Pair implements Comparable<Pair> {

        int num, cnt;

        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.cnt == o.cnt) {
                return this.num - o.num;
            }

            return o.cnt - this.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b].add(a);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            pq.offer(new Pair(i, bfs(i)));
        }

        StringBuilder sb = new StringBuilder();

        int max = pq.peek().cnt;
        while (!pq.isEmpty() && pq.peek().cnt == max) {
            sb.append(pq.poll().num).append(' ');
        }

        System.out.println(sb);
    }

    private static int bfs(int idx) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[idx] = true;
        q.offer(idx);
        int cnt = 1;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                if (visited[next]) continue;
                q.offer(next);
                visited[next] = true;
                cnt++;
            }
        }

        return cnt;
    }
}
