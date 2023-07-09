package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_13023 {
    static int N;
    static ArrayList<Integer>[] friend;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        friend = new ArrayList[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            friend[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friend[a].add(b);
            friend[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            dfs(0, i);
        }

        System.out.println(0);
    }

    private static void dfs(int depth, int idx) {
        if (depth == 4) {
            System.out.println(1);
            System.exit(0);
        }

        visited[idx] = true;
        for (int next : friend[idx]) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(depth + 1, next);
                visited[next] = false;
            }
        }
        visited[idx] = false;
    }
}
