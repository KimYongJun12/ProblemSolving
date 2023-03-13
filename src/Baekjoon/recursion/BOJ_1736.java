package Baekjoon.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1736 {
    static int n, m, trash;
    static int[][] room;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        trash = 0;

        room = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == 1) trash++;
            }
        }

        int cnt = 0;

        while (trash > 0) {
            clean(0, 0);
            cnt++;
        }

        System.out.println(cnt);
    }

    private static void clean(int c, int r) {
        for (int i = c; i < n; i++) {
            for (int j = r; j < m; j++) {
                if (room[i][j] == 1) {
                    room[i][j] = 0;
                    trash--;
                    clean(i, j);
                    return;
                }
            }
        }
    }
}
