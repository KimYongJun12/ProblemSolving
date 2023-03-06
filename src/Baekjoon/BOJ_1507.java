package Baekjoon;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_1507 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] shortestPath = new int[n + 1][n + 1];
        int[][] dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                shortestPath[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = shortestPath[i][j];
            }
        }
        for (int m = 1; m <= n; m++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (m == i || i == j || j == m) continue;

                    if (shortestPath[i][j] > shortestPath[i][m] + shortestPath[m][j]) {
                        System.out.println(-1);
                        System.exit(0);
                    }

                    if (shortestPath[i][j] == (shortestPath[i][m] + shortestPath[m][j])) {
                        dist[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) continue;
                ans += dist[i][j];
            }
        }

        System.out.println(ans / 2);
    }

}