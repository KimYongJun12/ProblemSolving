package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1747 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] primeNum = new boolean[1003002];
        primeNum[1] = true;
        for (int i = 2; i < 1003002; i++) {
            if (primeNum[i]) continue;
            for (int j = i + i; j < 1003002; j += i) {
                primeNum[j] = true;
            }
        }

        for (int i = N; i < 1003002; i++) {
            if (!primeNum[i]) {
                String s = String.valueOf(i);
                boolean isPalindrome = true;
                for (int j = 0; j <= s.length() / 2; j++) {
                    if (s.charAt(j) != s.charAt(s.length() - 1 - j)) {
                        isPalindrome = false;
                        break;
                    }
                }
                if (isPalindrome) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}
