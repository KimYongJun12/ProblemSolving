package Baekjoon.greedy;
/*
가장 많이 남기려면?
초기 값이 가장 큰 음료 하나를 베이스로 다른 음료를 전부 절반씩 더함
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20115 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long sum = 0;
        long max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long now =Long.parseLong(st.nextToken());
            sum += now;
            max = Math.max(max, now);
        }

        System.out.println((double) (sum + max) / 2);
    }
}
