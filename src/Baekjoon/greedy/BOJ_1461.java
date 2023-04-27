package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] left = new int[N];
        int[] right = new int[N];

        st = new StringTokenizer(br.readLine());
        int lIdx = 0, rIdx = 0, max = 0;
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());
            if (now > 0) right[rIdx++] = now;
            else left[lIdx++] = -now;

            max = Math.max(Math.abs(now), max);
        }

        Arrays.sort(left);
        Arrays.sort(right);

        int ans = 0;

        for (int i = N - 1; i >= N - lIdx; i -= M) {
            ans += 2 * left[i];
        }

        for (int i = N - 1; i >= N - rIdx; i -= M) {
            ans += 2 * right[i];
        }

        System.out.println(ans - max);
    }
}
