package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        boolean[] visited = new boolean[N + 1];

        int m = Integer.parseInt(br.readLine());
        Queue<Integer> friends = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
            if (a == 1) {
                visited[b] = true;
                friends.offer(b);

            } else if (b == 1) {
                visited[a] = true;
                friends.offer(a);
            }
        }

        visited[1] = true;
        int cnt = friends.size();

        while (!friends.isEmpty()) {
            int now = friends.poll();
            for (int next : graph[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
