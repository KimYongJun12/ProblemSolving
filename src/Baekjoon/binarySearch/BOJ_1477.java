package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] rest = new int[N + 2];
        rest[0] = 0;
        rest[N + 1] = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) rest[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(rest);

        int lo = 1, hi = L - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            int cnt = 0;
            for (int i = 0; i <= N; i++) {
                cnt += (rest[i + 1] - rest[i] - 1) / mid;
            }

            if (cnt <= M) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(lo);
    }
}
