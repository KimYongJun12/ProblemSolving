package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_23296 {
    static int n;
    static int[] destination;
    static int[] depth;
    static boolean[] check;
    static LinkedList<Integer> list = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        destination = new int[n + 1];
        depth = new int[n + 1];
        check = new boolean[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            destination[i] = Integer.parseInt(st.nextToken());
            depth[destination[i]]++;
        }

        dfs(1);
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> depth[o1] - depth[o2]);

        for (int i = 1; i <= n; i++) pq.offer(i);
        while (!pq.isEmpty()) {
            int now = pq.poll();
            if (check[now]) continue;
            list.add(now);
            dfs(now);
        }

        sb.append(list.size()).append('\n');
        for (int now : list) {
            sb.append(now).append(' ');
        }
        System.out.println(sb);
    }

    private static void dfs(int floor) {
        if (check[floor]) {
            return;
        }
        check[floor] = true;
        list.add(destination[floor]);
        dfs(destination[floor]);
    }
}
