package Baekjoon.graph.floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int INF = 987654321;

        int[][] cost = new int[N + 1][N + 1];
        int[][] firstStation = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            cost[start][end] = time;
            cost[end][start] = time;

            firstStation[start][end] = end;
            firstStation[end][start] = start;
        }

        for (int m = 1; m <= N; m++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    if (s == e) continue;
                    if (cost[s][e] > cost[s][m] + cost[m][e]) {
                        cost[s][e] = cost[s][m] + cost[m][e];
                        firstStation[s][e] = m;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (cost[i][j] == 0) {
                    sb.append('-').append(' ');
                } else {
                    int first = j;
                    while (firstStation[i][first] != first) {
                        first = firstStation[i][first];
                    }
                    sb.append(first).append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
