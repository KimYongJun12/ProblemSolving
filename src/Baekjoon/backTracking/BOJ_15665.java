package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15665 {
    static int N, M;
    static int[] num;
    static int[] choices;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N];
        choices = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);
        backTracking(0);

        System.out.println(sb);
    }

    private static void backTracking(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(choices[i]).append(' ');
            }
            sb.append('\n');

            return;
        }

        int prev = 0;
        for (int i = 0; i < N; i++) {
            if (prev == num[i]) continue;
            choices[depth] = num[i];
            backTracking(depth + 1);
            prev = choices[depth];
        }
    }
}
