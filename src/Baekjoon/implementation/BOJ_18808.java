package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18808 {
    static int N, M, K;
    static int[][] notebook;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        notebook = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < c; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            fo : for (int j = 0; j < 4; j++) {
                r = sticker.length;
                c = sticker[0].length;

                for (int x = 0; x + r <= N; x++) {
                    for (int y = 0; y + c <= M; y++) {
                        if (canStick(x, y, r, c, sticker)) {
                            break fo;
                        }
                    }
                }

                sticker = spin(sticker, r, c);
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (notebook[i][j] == 1) cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static int[][] spin(int[][] sticker, int r, int c) {
        int[][] ret = new int[c][r];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ret[j][r - 1 - i] = sticker[i][j];
            }
        }

        return ret;
    }

    private static boolean canStick(int x, int y, int r, int c, int[][] sticker) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sticker[i][j] == 1 && notebook[x + i][y + j] == 1) return false;
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sticker[i][j] == 1) notebook[i + x][j + y] = 1;
            }
        }

        return true;
    }
}
