package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16401 {
    static int N, M;
    static int[] pepero;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        pepero = new int[N];
        int lo = 1, hi = 0, ans = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pepero[i] = Integer.parseInt(st.nextToken());
            hi = Math.max(hi, pepero[i]);
        }

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (findNumOfShare(mid) >= M) {
                lo = mid + 1;
                ans = mid;
            } else hi = mid - 1;
        }

        System.out.println(ans);
    }

    private static int findNumOfShare(int value) {
        int tot = 0;

        for (int i = 0; i < N; i++) {
            if (tot >= M) return tot;
            tot += pepero[i] / value;
        }

        return tot;
    }
}

