package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1080 {
    static int N, M;
    static boolean[][] originalMatrix;
    static boolean[][] changedMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        originalMatrix = new boolean[N][M];
        changedMatrix = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                originalMatrix[i][j] = s.charAt(j) == '1';
            }
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                changedMatrix[i][j] = s.charAt(j) == '1';
            }
        }

        System.out.println(greedy());
    }

    private static int greedy() {
        int cnt = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                if (originalMatrix[i][j] != changedMatrix[i][j]) {
                    flip(i, j);
                    cnt++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (originalMatrix[i][j] != changedMatrix[i][j]) {
                    cnt = -1;
                    break;
                }
            }
        }

        return cnt;
    }

    private static void flip(int x, int y) {
        for (int i = x; i <= x + 2; i++) {
            for (int j = y; j <= y + 2; j++) {
                originalMatrix[i][j] = !originalMatrix[i][j];
            }
        }
    }
}
