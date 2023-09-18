package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:25
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 피자 반죽 넣기
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
public class BOJ_1756 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        if (D < N) {
            System.out.println(0);
        } else {
            int[] oven = new int[D + 1];
            int prev = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= D; i++) {
                int now = Integer.parseInt(st.nextToken());
                oven[i] = Math.min(prev, now);
                prev = oven[i];
            }

            int top = D;
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                int now = Integer.parseInt(st.nextToken());
                while (top >= 0 && now > oven[top--]);
            }

            System.out.println(top + 1);
        }

    }
}
