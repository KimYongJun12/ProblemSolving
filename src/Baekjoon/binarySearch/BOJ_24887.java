package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:13
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 할당량 M이 있을 때 일 안하고 연속으로 쉬는 날의 최솟값이 가장 커야 함
 *
 * 구해야 하는 것
 * 연속으로 쉬는 날 최솟값의 최댓값
 *
 * 문제 입력
 * 첫째 줄 : 날짜의 수 N, 할당량 M
 * 둘째 줄 : 하루에 할 수 있는 일의 양 W
 *
 * 제한 요소
 * 2 <= N <= 200_000
 * 1 <= M <= 10^8
 * 1 <= W <= 10^7
 *
 * 생각나는 풀이
 * 이분 탐색 & DP
 * 이분 탐색으로 K 범위 좁혀나감
 * K를 결정하는 값은 M이 가능한지 여부
 *
 * 연속해서 일하지 않는 날의 최솟값을 K라 할 때
 * i일에 할 수 있는 일의 최댓값 = dp[i]
 * dp[i] = Math.max(dp[i - 1], dp[i - k] + Wi)
 * dp[N] >= M이라면 K를 올림
 * dp[N] < M이라면 K를 내림
 *
 * 구현해야 하는 기능
 * 1. 이진 탐색
 * 2. dp
 *
 */
public class BOJ_24887 {
    static int N, M;
    static long[] works;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        works = new long[N + 1];
        dp = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        long sum = 0L;
        boolean isFree = false;
        for (int i = 1; i <= N; i++) {
            works[i] = Integer.parseInt(st.nextToken());
            if (works[i] >= M) {
                isFree = true;
                break;
            }
            sum += works[i];
        }

        if (isFree) {
            System.out.println("Free!");
        } else if (sum < M) {
            System.out.println(-1);
        } else {
            System.out.println(findMax());
        }
    }

    private static int findMax() {
        int lo = 1;
        int hi = N;
        int ans = -1;

        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (getMax(mid) >= M) {
                ans = Math.max(ans, mid);
                lo = mid + 1;
            } else hi = mid;
        }
        return ans - 1;
    }

    private static long getMax(int k) {
        Arrays.fill(dp, 0L);
        for (int i = 1; i <= k; i++) {
            dp[i] = Math.max(dp[i - 1], works[i]);
        }
        for (int i = k + 1; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - k] + works[i]);
        }
        return dp[N];
    }
}
