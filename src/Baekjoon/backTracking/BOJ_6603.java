package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6603 {
    static int K;
    static int[] num;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String input = "";

        while (!(input = br.readLine()).equals("0")) {
            st = new StringTokenizer(input);
            K = Integer.parseInt(st.nextToken());
            num = new int[K];
            ans = new int[6];

            for (int i = 0; i < K; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            backTracking(0, 0);
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void backTracking(int depth, int idx) {
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(ans[i]).append(' ');
            }

            sb.append('\n');
            return;
        }

        for (int i = idx; i < K; i++) {
            ans[depth] = num[i];
            backTracking(depth + 1, i + 1);
        }

    }
}
