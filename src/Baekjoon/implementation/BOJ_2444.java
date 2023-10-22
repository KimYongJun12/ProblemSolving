package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
public class BOJ_2444 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 2 * N - 1; i++) {
            for (int j = Math.abs(N - i); j > 0; j--) sb.append(' ');
            for (int j = 0; j < 2 * (N - Math.abs(N - i)) - 1; j++) sb.append('*');
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
