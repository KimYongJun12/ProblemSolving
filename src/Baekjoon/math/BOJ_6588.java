package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_6588 {
    static boolean[] primeNum = new boolean[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        StringBuilder sb = new StringBuilder();

        init();
        while (!(s = br.readLine()).equals("0")) {
            int N = Integer.parseInt(s);
            sb.append(findGoldBachNumber(N)).append('\n');
        }

        System.out.println(sb);
    }

    private static void init() {
        for (int i = 3; i <= 1000000; i += 2) {
            if (primeNum[i]) continue;
            for (int j = i + i; j <= 1000000; j += i) {
                primeNum[j] = true;
            }
        }
    }

    private static StringBuilder findGoldBachNumber(int n) {

        for (int i = 3; i <= n / 2; i += 2) {
            if (!primeNum[i] && !primeNum[n - i]) {
                return new StringBuilder().append(n).append(' ').append('=').append(' ').append(i).append(' ').append('+').append(' ').append(n - i);
            }
        }

        return new StringBuilder("Goldbach's conjecture is wrong.");
    }
}
