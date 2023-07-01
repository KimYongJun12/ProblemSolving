package Baekjoon.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1051 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int maxL = Math.min(N, M);

        int maxArea = 1;
        fo : for (int i = maxL - 1; i >= 1; i--) {
            for (int j = 0; j + i < N; j++) {
                for (int k = 0; k + i < M; k++) {
                    char now = arr[j][k];
                    if (arr[j][k + i] != now) continue;
                    if (arr[j + i][k] != now) continue;
                    if (arr[j + i][k + i] != now) continue;

                    maxArea = (int) Math.pow(i + 1, 2);
                    break fo;
                }
            }
        }

        System.out.println(maxArea);
    }
}
