package Baekjoon.dataStructure.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2461_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] students = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) Arrays.sort(students[i]);

        int[] idx = new int[N];
        int min = Integer.MAX_VALUE;
        int minIdx = 0;

        while (idx[minIdx] < M) {
            int nowMin = Integer.MAX_VALUE;
            int nowMax = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                if (students[i][idx[i]] > nowMax) {
                    nowMax = students[i][idx[i]];
                }

                if (students[i][idx[i]] < nowMin) {
                    nowMin = students[i][idx[i]];
                    minIdx = i;
                }
            }

            min = Math.min(min, nowMax - nowMin);
            idx[minIdx]++;
        }

        System.out.println(min);
    }

}
