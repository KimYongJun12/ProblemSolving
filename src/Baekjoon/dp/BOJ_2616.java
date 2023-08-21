package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 7:26
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 기차
 * 맨 앞에 있는 기관차 1대가 객차 여러 칸을 끌고 감
 * 소형 기관차 3개가 있음
 * 소형 기관차가 끌고 가는 객차의 조건
 *  - 소형 기관차가 최대로 끌 수 있는 객차의 수를 미리 정함, 그보다 많은 수의 객차는 못끌음, 3대의 소형 기관차가 끌 수 있는 객차의 수는 동일
 *  - 소형 기관차 3대를 이용해 최대한 많은 손님을 목적지까지 운송해야 함. 손님 수는 미리 알고 있음
 *  - 소형 기관차는 반드시 번호가 연속적으로 이어진 객차를 끌어야 함
 *
 * 구해야 하는 것
 * 소형 기관차 3대를 이용하여 최대로 운송할 수 있는 손님 수
 *
 * 문제 입력
 * 첫째 줄 : 객차의 수 N
 * 둘째 줄 : 객차에 타있는 손님 수, 객차 번호 순서대로
 * 셋째 줄 : 소형 기관차가 끌 수 있는 객차의 수 M
 *
 * 제한 요소
 * 3 < N <= 50000
 * 1 <= M < N / 3
 * 0 <= 객차 안의 손님 수 <= 100
 *
 * 생각나는 풀이
 * 객차 50000개, 열차 3개
 * 그리디하게 하면 안될 거 같은데
 * dp[1][M + i] => 열차 1개일 때 0 ~ M + i칸까지 봤을 때 가장 많이 태울 수 있는 경우
 * dp[2][2 * M + i] => 열차 2개일 때 M + i ~ 2 * M + i까지 봤을 때 가장 많이 태울 수 있는 경우
 * dp[3][3 * M + i] => 열차 3개일 때 2 * M + i ~ 3 * M + i까지 봤을 때 가장 많이 태울 수 있는 경우
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_2616 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] prefixSum = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[4][N + 1];
        int M = Integer.parseInt(br.readLine());
        for (int i = 1; i <= 3; i++) {
            for (int j = M * i; j <= N; j++) {
                // i = 열차의 개수, j = 현재 칸의 인덱스
                // dp[i][j] = 열차의 개수가 i개일 때 1번 칸 ~ j칸까지 고려하여 가장 많이 탈 수 있는 인원
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - M] + (prefixSum[j] - prefixSum[j - M]));
            }
        }
        System.out.println(dp[3][N]);
    }
}
