package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182 {
    static int N, S, ans = 0;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        num = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        backTracking(0, 0);
        if (S == 0) ans--;
        System.out.println(ans);
    }

    private static void backTracking(int depth, int sum) {
        if (depth == N) {
            if (sum == S) ans++;
            return;
        }

        backTracking(depth + 1, sum + num[depth]);
        backTracking(depth + 1, sum);
    }
}
