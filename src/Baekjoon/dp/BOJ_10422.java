package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 풀이 시작 : 10:44
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 괄호의 개수 구하기
 *
 * 구해야 하는 것
 * 길이가 N인 괄호 문자열의 개수 (mod 1000000007)
 *
 * 문제 입력
 * 첫째 줄 : 테케 수 N
 * 둘째 줄 ~ N개 줄 : 문자열 길이 L
 *
 * 제한 요소
 * 1 <= N <= 100
 * 1 <= L <= 5000
 *
 * 생각나는 풀이
 * dp
 * 홀수는 올바른 괄호 문자열이 없음
 * 길이 2 = 1개 ()
 * 길이 4 = 2개 ()() (())
 * 길이 6 = ()()() (())() ()(()) ((()))
 * 길이 8 =
 * 구현해야 하는 기능
 *
 */
public class BOJ_10422 {
    static final long MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] dp = new long[5001];
        dp[0] = dp[2] = 1;

        for (int i = 4; i <= 5000; i += 2) {
            for (int j = 0; j < i; j += 2) {
                dp[i] += dp[j] * dp[i - j - 2];
                dp[i] %= MOD;
            }
        }

        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append('\n');
        }

        System.out.println(sb);
    }
}
