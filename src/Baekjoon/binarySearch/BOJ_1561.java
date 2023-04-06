package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1561 {
    static long N;
    static int M;
    static int[] playTime;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N <= M) {
            System.out.println(N);
        } else {
            playTime = new int[M + 1];
            int maxTime = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                playTime[i] = Integer.parseInt(st.nextToken());
                maxTime = Math.max(maxTime, playTime[i]);
            }

            long time = binarySearchLowerBound(0, (N / M) * maxTime);

            long cnt = M;
            for (int i = 1; i <= M; i++) cnt += (time - 1) / playTime[i];
            for (int i = 1; i <= M; i++) {
                if (time % playTime[i] == 0) cnt++;
                if (cnt == N) {
                    System.out.println(i);
                    break;
                }

            }
        }
    }

    private static long binarySearchLowerBound(long lo, long hi) {
        while (lo < hi) {
            long mid = (lo + hi) / 2;
            long people = M;

            for (int i = 1; i <= M; i++) people += mid / playTime[i];

            if (people < N) lo = mid + 1;
            else hi = mid;
        }

        return hi;
    }
}
