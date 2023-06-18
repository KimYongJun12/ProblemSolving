package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961 {
    static int N, min = Integer.MAX_VALUE;
    static int[] sour;
    static int[] bitter;
    static boolean[] choice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        sour = new int[N];
        bitter = new int[N];
        choice = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sour[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }

        backTracking(0);
        System.out.println(min);
    }

    private static void backTracking(int depth) {
        if (depth == N) {
            boolean isOverOne = false;
            for (int i = 0; i < N; i++) {
                if (choice[i]) {
                    isOverOne = true;
                    break;
                }
            }

            if (!isOverOne) return;
            int s = 1;
            int b = 0;

            for (int i = 0; i < N; i++) {
                if (choice[i]) {
                    s *= sour[i];
                    b += bitter[i];
                }
            }

            min = Math.min(min, Math.abs(s - b));
            return;
        }

        choice[depth] = true;
        backTracking(depth + 1);
        choice[depth] = false;
        backTracking(depth + 1);
    }
}
