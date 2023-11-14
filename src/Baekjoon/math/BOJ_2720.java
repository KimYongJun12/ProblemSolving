package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 풀이 시작 :
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 *
 * 구해야 하는 것
 *
 * 문제 입력
 *
 * 제한 요소
 *
 * 생각나는 풀이
 *
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_2720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] num = new int[4];
        int[] price = {25, 10, 5, 1};

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            int change = Integer.parseInt(br.readLine());
            int idx = 0;
            Arrays.fill(num, 0);
            while (change > 0) {
                if (price[idx] <= change) {
                    num[idx] = change / price[idx];
                    change %= price[idx];
                }
                idx++;
            }
            for (int i = 0; i < 4; i++) {
                sb.append(num[i]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
