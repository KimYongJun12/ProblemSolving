package Baekjoon.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3085 {
    static int N;
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i < N - 1) {
                    swap(i, j, i + 1, j);
                    max = Math.max(max, Math.max(findMax(i, j), findMax(i + 1, j)));
                    swap(i, j, i + 1, j);
                }

                if (j < N - 1) {
                    swap(i, j, i, j + 1);
                    max = Math.max(max, Math.max(findMax(i, j), findMax(i, j + 1)));
                    swap(i, j, i, j + 1);
                }
            }
        }

        System.out.println(max);
    }

    private static int findMax(int x, int y) {
        char now = board[x][y];
        int cnt1 = 1, cnt2 = 1;

        for (int i = x - 1; i >= 0; i--) {
            if (board[i][y] != now) break;
            cnt1++;
        }

        for (int i = x + 1; i < N; i++) {
            if (board[i][y] != now) break;
            cnt1++;
        }

        for (int i = y - 1; i >= 0; i--) {
            if (board[x][i] != now) break;
            cnt2++;
        }

        for (int i = y + 1; i < N; i++) {
            if (board[x][i] != now) break;
            cnt2++;
        }

        return Math.max(cnt1, cnt2);
    }

    private static void swap(int x1, int y1, int x2, int y2) {
        char temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }
}
