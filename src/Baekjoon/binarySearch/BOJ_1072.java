package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1072 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        long lo = 0, hi = X;
        if (Y * 100 / X >= 99) {
            hi = -1;
        }

        while (lo < hi) {
            long mid = (lo + hi) / 2;

            if ((((Y + mid) * 100) / (X + mid)) == (Y * 100) / X) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        System.out.println(hi);
    }
}
