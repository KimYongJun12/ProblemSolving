package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 풀이 시작 : 10:14
 * 풀이 완료 : 10:20
 * 풀이 시간 : 6분
 *
 * 문제 해석
 * 수가 주어지면 소인수분해해서 출력해야 함
 *
 * 구해야 하는 것
 * 수를 소인수분해 한 결과
 *
 * 문제 입력
 * 첫째 줄 : tc의 수
 * 테케당 입력 : 정수 N
 *
 * 제한 요소
 * 2 <= N <= 100_000
 *
 * 생각나는 풀이
 * 소인수분해
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_2312 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            for (int i = 2; i <= N; i++) {
                int cnt = 0;
                while (N % i == 0) {
                    N /= i;
                    cnt++;
                }

                if (cnt != 0) sb.append(i).append(' ').append(cnt).append('\n');
                if (N == 0) break;
            }
        }
        System.out.println(sb);
    }
}
