package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16940 {
    static int N;
    static HashSet<Integer>[] tree;
    static int[] visitOrder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        tree = new HashSet[N + 1];

        for (int i = 1; i <= N; i++) tree[i] = new HashSet<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tree[start].add(end);
            tree[end].add(start);
        }

        visitOrder = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) visitOrder[i] = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    private static int bfs() {
        if (visitOrder[0] != 1) return 0;
        int idx = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(visitOrder[0]);

        while (!q.isEmpty()) {
            if (idx == N) return 1;
            int now = q.poll();

            while (idx < N && tree[now].contains(visitOrder[idx])) {
                q.offer(visitOrder[idx++]);
            }
        }

        return 0;
    }
}
