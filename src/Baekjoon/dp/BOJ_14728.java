package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:31
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 시험 문제의 조건
 * 1. 여러 단원을 융합한 문제는 출제하지 않음
 * 2. 한 단원에 한 문제 출제, 그 단원의 모든 내용을 알고 있어야 풀 수 있음
 * 각 단원별 배점이 있음
 * 어떤 단원의 문제를 맞추기 위해서는 그 단원의 예상 공부 시간 이상 공부해야 맞출 수 있음
 *
 * 구해야 하는 것
 * 남은 시간 동안 공부해서 얻을 수 있는 최고 점수
 *
 * 문제 입력
 * 첫째 줄 : 단원의 개수 N, 남은 시간 T
 * 둘째 줄 ~ N개 줄 : 각 단원 별 예상 공부 시간 K, 문제의 배점 S
 *
 * 제한 요소
 * 1 <= N <= 100
 * 1 <= T <= 10000
 * 1 <= K <= 1000
 * 1 <= S <= 1000
 *
 * 생각나는 풀이
 * 냅색문제같음
 * 무게 T 이하에서 만족도의 합이 가장 큰 경우를 찾는 것과 같음
 * 1차원 냅색 사용해서 풀어보자
 *
 * 구현해야 하는 기능
 * 1. dp배열
 * 2. 점화식
 *
 */
public class BOJ_14728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] weight = new int[N + 1];
        int[] value = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            weight[i] = w;
            value[i] = v;
        }

        int[] dp = new int[T + 1];
        for (int i = 1; i <= N; i++) {
            int min = weight[i];
            int nowValue = value[i];
            for (int j = T; j >= min; j--) {
                dp[j] = Math.max(dp[j], dp[j - min] + nowValue);
            }
        }

        System.out.println(dp[T]);
    }
}
