package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2352 {
    static int[] LIS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        LIS = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        int len = 0;
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());

            if (LIS[len] < now) {
                len++;
                LIS[len] = now;
            } else {
                int idx = binarySearchLowerBound(now, len);
                LIS[idx] = now;
            }
        }

        System.out.println(len);
    }

    private static int binarySearchLowerBound(int value, int hi) {
        int lo = 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (LIS[mid] < value) {
                lo = mid + 1;
            } else hi = mid;
        }

        return hi;
    }
}
