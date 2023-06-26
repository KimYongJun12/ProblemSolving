package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            long[] price = new long[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                price[i] = Long.parseLong(st.nextToken());
            }

            long max = price[N - 1];
            long sum = 0;

            for (int i = N - 2; i >= 0; i--) {
                if (max < price[i]) {
                    max = price[i];
                }

                sum += max - price[i];
            }

            sb.append(sum).append('\n');
        }

        System.out.println(sb);
    }
}
