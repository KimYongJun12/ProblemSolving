package Baekjoon.graph.floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 9:54
 * 풀이 완료 : 10:09
 * 풀이 시간 : 15분
 *
 * 문제 해석
 * 모든 사람들 간 몇 다리를 거치면 알 수 있음 -> 연결 그래프
 * 점수 = 한 사람을 기준으로 다른 사람까지의 거리 중 가장 긴 거리 (나를 중심으로 모든 노드까지의 거리 중 가장 먼 노드의 거리)
 * 점수가 가장 작은 사람을 구해야 함
 *
 * 구해야 하는 것
 * 첫 줄 : 회장 후보의 점수, 회장 후보의 수
 * 둘째 줄 : 회장 후보, 오름차순으로
 *
 * 문제 입력
 * 첫째 줄 : 회원의 수
 * 둘째 줄 ~ 여러 줄 : 한 줄에 두 개의 회원 번호, 서로 친구
 * 마지막 줄 : -1 -1
 *
 * 제한 요소
 * 1 <= N <= 50
 * 1 <= 회원 번호 <= N
 *
 * 생각나는 풀이
 * 플로이드 워셜로 모든 사람의 거리를 구함
 * 모든 사람의 최장거리를 구함
 * 최장거리가 가장 짧은 사람이 회장 후보
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 그래프 구현
 * 2. 플로이드 워셜
 * 3. 1 ~ N번까지 순회하며 최장거리를 구함
 * 4. 1 ~ N번까지 순회하며 최장거리가 가장 짧은 사람을 구함
 * 5. 1 ~ N번까지 순회하며 최장거리가 가장 짧은 사람의 수, 목록을 구함
 */
public class BOJ_2660 {
    static final int INF = 98765;
    static int N;
    static int[][] dist;
    static int[] maxDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dist = new int[N + 1][N + 1];
        maxDist = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        String input = "";
        while (!(input = br.readLine()).equals("-1 -1")) {
            st = new StringTokenizer(input);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            dist[start][end] = 1;
            dist[end][start] = 1;
        }

        floydWarshall();
        findMaxLength();
        findAnswer();
    }

    private static void floydWarshall() {
        for (int m = 1; m <= N; m++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    if (s == e || s == m || m == e) continue;
                    dist[s][e] = Math.min(dist[s][e], dist[s][m] + dist[m][e]);
                }
            }
        }
    }

    private static void findMaxLength() {
        for (int i = 1; i <= N; i++) {
            int max = 0;
            for (int j = 1; j <= N; j++) {
                max = Math.max(max, dist[i][j]);
            }
            maxDist[i] = max;
        }
    }

    private static void findAnswer() {
        int cnt = 0;
        int min = INF;

        for (int i = 1; i <= N; i++) {
            if (maxDist[i] < min) {
                cnt = 1;
                min = maxDist[i];
                continue;
            }

            if (maxDist[i] == min) cnt++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(min).append(' ').append(cnt).append('\n');

        for (int i = 1; i <= N; i++) {
            if (maxDist[i] == min) sb.append(i).append(' ');
        }

        System.out.println(sb);
    }
}
