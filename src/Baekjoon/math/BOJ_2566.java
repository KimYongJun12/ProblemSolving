package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
public class BOJ_2566 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int r = 0, c = 0, max = -1;
        StringTokenizer st;
        for (int i = 1; i <= 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                int now = Integer.parseInt(st.nextToken());
                if (max < now) {
                    r = i;
                    c = j;
                    max = now;
                }
            }
        }

        System.out.printf("%d%n%d %d", max, r, c);
    }
}
