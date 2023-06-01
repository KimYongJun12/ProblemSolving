package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2343 {
    static int N, M;
    static int[] playTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        playTime = new int[N];
        st = new StringTokenizer(br.readLine());

        int lo = 0;
        int hi = 0;
        for (int i = 0; i < N; i++) {
            playTime[i] = Integer.parseInt(st.nextToken());
            hi += playTime[i];
            lo = Math.max(lo, playTime[i]);
        }

        int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (binarySearch(mid)) {
                ans = mid;
                hi = mid - 1;
            } else lo = mid + 1;
        }

        System.out.println(ans);
    }

    private static boolean binarySearch(int mid) {
        int cnt = 0;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            if (sum + playTime[i] > mid) {
                sum = 0;
                cnt++;
            }

            sum += playTime[i];
        }

        if (sum > 0) cnt++;
        return cnt <= M;
    }
}
