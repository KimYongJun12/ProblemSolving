package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1783 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int ans;

        if (N == 1) {
            ans = 1;
        } else if (N == 2) {
            ans = Math.min((M + 1) / 2, 4);
        } else {
            if (M >= 7) {
                ans = M - 2;
            } else {
                ans = Math.min(M, 4);
            }
        }

        System.out.println(ans);
    }
}
