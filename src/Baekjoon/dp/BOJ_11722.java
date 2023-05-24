package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11722 {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        int length = 0;
        dp[0] = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());
            if (dp[length] > now) {
                length++;
                dp[length] = now;
            } else {
                dp[binarySearchLowerBound(now, length)] = now;
            }
        }

        System.out.println(length);
    }

    private static int binarySearchLowerBound(int num, int length) {
        int lo = 0, hi = length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if (dp[mid] <= num) hi = mid;
            else lo = mid + 1;
        }

        return lo;
    }
}
