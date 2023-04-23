package Baekjoon.graph.floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] graph = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            graph[first][second] = true;
        }

        for (int m = 1; m <= N; m++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    if (graph[s][m] && graph[m][e]) graph[s][e] = true;
                }
            }
        }

        int[] cnt = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] || graph[j][i]) cnt[i]++;
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (cnt[i] == N - 1) ans++;
        }

        System.out.println(ans);
    }
}
