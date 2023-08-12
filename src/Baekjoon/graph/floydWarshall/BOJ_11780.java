package Baekjoon.graph.floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11780 {
    static int INF = 87654321;
    static int N;
    static int[][] dist;
    static int[][] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dist = new int[N + 1][N + 1];
        prev = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            Arrays.fill(prev[i], INF);
            dist[i][i] = 0;
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[start][end] = Math.min(dist[start][end], cost);
            prev[start][end] = start;
        }

        floydWarshall();
        printAnswer();
    }

    private static void floydWarshall() {
        for (int m = 1; m <= N; m++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    if (dist[s][e] > dist[s][m] + dist[m][e]) {
                        dist[s][e] = dist[s][m] + dist[m][e];
                        prev[s][e] = prev[m][e];
                    }
                }
            }
        }
    }


    private static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append((dist[i][j] == INF) ? 0 : dist[i][j]).append(' ');
            }
            sb.append('\n');
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (prev[i][j] == INF) sb.append('0');
                else {
                    int prevCity = j;
                    stack.addLast(j);
                    while (i != prev[i][prevCity]) {
                        prevCity = prev[i][prevCity];
                        stack.addLast(prevCity);
                    }
                    stack.addLast(i);

                    sb.append(stack.size()).append(' ');
                    while (!stack.isEmpty()) {
                        sb.append(stack.removeLast()).append(' ');
                    }
                }
                sb.append('\n');
            }
        }

        System.out.println(sb);
    }
}
