package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] powTwo = new int[26];
        int two = 1;

        for (int i = 1; i <= 25; i++) {
            powTwo[i] = two;
            two *= 2;
        }

        while (K-- > 1) {
            int now = 0;

            for (int i = 0; i < 26; i++) {
                if (powTwo[i] > N) {
                    now = powTwo[i - 1];
                    break;
                }
            }

            N -= now;
        }

        int min = 0;
        for (int i = 0; i < 26; i++) {
            if (powTwo[i] >= N) {
                min = powTwo[i];
                break;
            }
        }

        System.out.println(min - N);
    }
}
