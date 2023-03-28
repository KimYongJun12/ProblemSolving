package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1365 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int len = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());

            if (dp[len] < now) {
                len++;
                dp[len] = now;
                continue;
            }

            dp[binarySearchLowerBound(dp, len, now)] = now;
        }

        System.out.println(N - len);
    }

    private static int binarySearchLowerBound(int[] dp, int hi, int now) {
        int lo = 0;
        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if (dp[mid] > now) hi = mid;
            else lo = mid + 1;
        }

        return hi;
    }
}
