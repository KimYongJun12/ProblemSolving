package Baekjoon.graph.floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1613 {
    static int N;
    static boolean[][] history;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        history = new boolean[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            history[first][second] = true;
        }

        floydWarshall();
        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (history[a][b]) {
                sb.append("-1");
            } else {
                if (history[b][a]) sb.append("1");
                else sb.append("0");
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void floydWarshall() {
        for (int m = 1; m <= N; m++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    if (history[s][m] && history[m][e]) history[s][e] = true;
                }
            }
        }
    }

}
