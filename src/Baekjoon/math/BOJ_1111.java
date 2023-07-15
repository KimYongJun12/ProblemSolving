package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1111 {
    static int N;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        String ans;

        if (N == 1) {
            ans = "A";
        } else if (N == 2) {
            ans = num[0] == num[1] ? String.valueOf(num[0]) : "A";
        } else {
            int a, b;

            if (num[0] == num[1]) {
                a = 1;
                b = 0;
            } else {
                a = (num[2] - num[1]) / (num[1] - num[0]);
                b = num[1] - (a * num[0]);
            }

            int now = isCorrect(a, b);
            ans = (now == Integer.MAX_VALUE) ? "B" : String.valueOf(now);
        }

        System.out.println(ans);
    }

    private static int isCorrect(int a, int b) {
        for (int i = 0; i < num.length - 1; i++) {
            if (num[i + 1] != num[i] * a + b) return Integer.MAX_VALUE;
        }

        return num[N - 1] * a + b;
    }
}
