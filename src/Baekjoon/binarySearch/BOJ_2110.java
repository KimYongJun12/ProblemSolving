package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        int lo = 0, hi = house[N - 1] - house[0] + 1;

        // upper bound
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (binarySearch(mid) >= C) {
                lo = mid + 1;
            } else hi = mid;
        }

        System.out.println(lo - 1);
    }

    private static int binarySearch(int value) {
        int cnt = 1;
        int last = house[0];

        for (int i = 1; i < house.length; i++) {
            if (house[i] - last >= value) {
                cnt++;
                last = house[i];
            }
        }
        return cnt;
    }
}
