package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 2:34
 * 풀이 완료 : 3:20
 * 풀이 시간 : 46분
 *
 * 문제 해석
 * 자동차 경주 대회
 * 일정 거리마다 정비소에서 정비를 받아야 함
 * 정비소의 정비 시간은 다 다름
 * 출발지에서 도착지까지 갈 때 가장 빠른 시간과 그 때 방문하는 정비소를 차례로 출력
 *
 * 구해야 하는 것
 * 출발지에서 도착지까지 갈 때 가장 빠른 시간과 그 때 방문하는 정비소를 차례로 출력
 *
 * 문제 입력
 * 첫째 줄 : 정비를 받지 않고 갈 수 있는 최대 거리 L
 * 둘째 줄 : 정비소의 개수
 * 셋째 줄 : 인접한 정비소 사이의 거리 d
 * 넷째 줄 : 정비소별 정비 시간
 *
 * 제한 요소
 * 1 <= L <= 2^31 - 1
 * 1 <= d <= L
 * 1 <= 정비소 수 N <= 100
 * 모든 정비 시간의 합 <= 2^31 - 1
 *
 * 생각나는 풀이
 * dp[N], dp[현재 정비소 번호] = 현재 정비소에서 정비 받는 최소 시간
 * dp[i] = min(갈 수 있는 거리 내에 있는 모든 정비소의 수리 시간) + fix[i]
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 정비소의 위치, 정비소 수리 시간 저장하는 배열, 이전 정비소 저장하는 배열
 * 2. dp
 *  - 뒤에서부터 앞으로 가능한 거리까지 역탐색하며 최소 시간을 찾음
 *  - 최소 시간에 현재 수리 시간을 더함
 *  - 최소 시간인 인덱스를 현재 인덱스의 이전 정비소로 저장함
 */
public class BOJ_2651 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long L = Long.parseLong(br.readLine());
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] dist = new long[N + 1];
        for (int i = 0; i <= N; i++) {
            dist[i] = Long.parseLong(st.nextToken());
        }

        long[] time = new long[N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            time[i] = Long.parseLong(st.nextToken());
        }

        long[] dp = new long[N + 2];
        int[] prev = new int[N + 2];
        Arrays.fill(prev, -1);

        for (int i = 1; i <= N + 1; i++) {
            long distSum = 0;
            long minCost = Long.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if (distSum + dist[j] > L) break;
                distSum += dist[j];
                if (dp[j] < minCost) {
                    minCost = dp[j];
                    prev[i] = j;
                }
            }
            dp[i] = minCost + time[i];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[N + 1]).append('\n');
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int now = prev[N + 1];
        while (now > 0) {
            stack.push(now);
            now = prev[now];
        }

        sb.append(stack.size()).append('\n');
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }

        System.out.println(sb);
    }
}
