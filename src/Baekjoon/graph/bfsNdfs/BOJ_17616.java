package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17616 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] winner = new ArrayList[N + 1];
        ArrayList<Integer>[] loser = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            winner[i] = new ArrayList<>();
            loser[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            loser[a].add(b);
            winner[b].add(a);
        }

        int highRank = bfs(X, winner) + 1;
        int lowRank = N - bfs(X, loser);

        System.out.println(highRank + " " + lowRank);
    }

    private static int bfs(int x, ArrayList<Integer>[] graph) {
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[x] = true;
        q.offer(x);

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
