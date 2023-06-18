package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    static int N, C;
    static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        house = new int[N];

        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        int lo = 1, hi = house[N - 1] - house[0];
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (binarySearch(mid)) {
                hi = mid;
            } else lo = mid + 1;
        }

        System.out.println(hi - 1);
    }

    private static boolean binarySearch(int distance) {
        int cnt = 1;
        int last = house[0];

        for (int i = 1; i < N; i++) {
            int now = house[i];
            if (now - last >= distance) {
                cnt++;
                last = now;
            }
        }

        return cnt < C;
    }
}
