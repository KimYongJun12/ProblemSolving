package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3745 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String s = "";

        while ((s = br.readLine()) != null) {
            st = new StringTokenizer(s);
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            int[] lis = new int[N];
            int len = 1;
            lis[0] = Integer.parseInt(st.nextToken());

            for (int i = 1; i < N; i++) {
                int now = Integer.parseInt(st.nextToken());
                if (now > lis[len - 1]) {
                    lis[len++] = now;
                } else {
                    binarySearchLowerBound(lis, now, len);
                }
            }

            sb.append(len).append('\n');
        }

        System.out.println(sb);
    }

    private static void binarySearchLowerBound(int[] lis, int now, int hi) {
        int lo = 0;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (lis[mid] < now) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        lis[lo] = now;
    }
}
