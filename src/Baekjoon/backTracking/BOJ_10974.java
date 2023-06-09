package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10974 {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static boolean[] visited;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        ans = new int[N];

        backTracking(0);
        System.out.println(sb);

    }

    private static void backTracking(int depth) {
        if (depth == N) {
            for (int i = 0; i < N; i++) {
                sb.append(ans[i]).append(' ');
            }

            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            ans[depth] = i;
            backTracking(depth + 1);
            visited[i] = false;
        }
    }
}
