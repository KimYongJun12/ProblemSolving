package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int twoCnt = findPow(2, N) - findPow(2, N - M) - findPow(2, M);
        int fiveCnt = findPow(5, N) - findPow(5, N - M) - findPow(5, M);

        System.out.println(Math.min(twoCnt, fiveCnt));
    }

    private static int findPow(int base, int n) {
        int cnt = 0;

        while (n >= base) {
            cnt += n / base;
            n /= base;
        }

        return cnt;
    }
}
