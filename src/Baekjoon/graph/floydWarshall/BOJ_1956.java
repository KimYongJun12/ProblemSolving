package Baekjoon.graph.floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1956 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int INF = 987654321;

        int[][] dist = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[start][end] = cost;
        }

        for (int m = 1; m <= V; m++) {
            for (int s = 1; s <= V; s++) {
                for (int e = 1; e <= V; e++) {
                    dist[s][e] = Math.min(dist[s][e], dist[s][m] + dist[m][e]);
                }
            }
        }

        int ans = INF;
        for (int i = 1; i <= V; i++) {
            ans = Math.min(ans, dist[i][i]);
        }

        ans = ans == INF ? -1 : ans;
        System.out.println(ans);
    }
}
