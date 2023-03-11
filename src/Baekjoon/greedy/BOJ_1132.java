package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1132 {

    static class Pair implements Comparable<Pair> {
        long sum;
        int idx;
        long num = -1;

        public Pair(int idx) {
            this.sum = 0;
            this.idx = idx;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.sum < o.sum) return -1;
            else return 1;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] alphabet = new long[12][10];
        boolean[] noZero = new boolean[10];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            int start = 12 - s.length();
            for (int j = 0; j < s.length(); j++) {
                if (j == 0) noZero[s.charAt(j) - 'A'] = true;
                alphabet[start + j][s.charAt(j) - 'A']++;
            }
        }

        Pair[] sum = new Pair[10];

        for (int i = 0; i < 10; i++) {
            long digit = 1;
            sum[i] = new Pair(i);
            for (int j = 11; j >= 0; j--) {
                sum[i].sum += digit * alphabet[j][i];
                digit *= 10;
            }
        }

        Arrays.sort(sum);

        if (noZero[sum[0].idx]) {
            for (int i = 1; i < 10; i++) {
                if (!noZero[sum[i].idx]) {
                    sum[i].num = 0;
                    break;
                }
            }
        }

        for (int i = 9, num = 9; i >= 0; i--) {
            if (sum[i].num == 0) continue;
            sum[i].num = num--;
        }

        long ans = 0;
        for (int i = 0; i < 10; i++) ans += sum[i].num * sum[i].sum;
        System.out.println(ans);
    }

}