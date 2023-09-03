package Baekjoon.graph.minimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:26
 * 풀이 완료 : 8:54
 * 풀이 시간 : 28분
 *
 * 문제 해석
 * N개의 논에 물을 대야함
 * 직접 논에 우물을 팔 수도, 이미 물을 대고 있는 다른 논으로부터 물을 끌어올 수도 있음
 * 최소 비용으로 논에 물을 대야 함
 *
 * 구해야 하는 것
 * 모든 논에 물을 댈 때 최소 비용
 *
 * 문제 입력
 * 첫째 줄 : 논의 수 N
 * 둘째 줄 ~ N개 줄 : i번째 논에 우물을 파는 비용 W[i]
 * 다음 N개 줄 : i번째 논과 j번째 논을 연결하는데 드는 비용 P[i]
 *
 * 제한 요소
 * 1 <= N <= 300
 * 1 <= W[i] <= 100000
 * 1 <= P[i] <= 100000
 *
 * 생각나는 풀이
 * MST로 논을 연결함
 * 프림 알고리즘으로
 * 현재 가능한 간선의 최소 비용보다
 * 우물 짓는 비용이 작다면 우물 지으면 될 듯
 *
 * 구현해야 하는 기능
 * 1. 입력에 따라 우물 짓는 비용을 저장할 배열
 * 2. 입력에 따라 물을 대는 비용을 저장할 배열
 * 과정
 * 1. 초기 논 위치 = 우물 비용이 가장 적은 논
 *  1-1. 나머지 우물을 짓는 비용은 초기 논-해당 논으로 우물 비용만큼의 간선으로 생각
 *  1-2. 물 대는 비용을 우물 짓는 비용이랑 비교해 작은 것으로 갱신하고 시작
 * 2. 처음 논부터 프림 알고리즘 수행
 *
 *
 */
public class BOJ_1368 {
    static int N;
    static int[][] dist;
    static int[] well;
    static class Edge implements Comparable<Edge> {
        int end, cost;

        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        well = new int[N];

        int minIdx = 0;
        for (int i = 0, cost = Integer.MAX_VALUE; i < N; i++) {
            well[i] = Integer.parseInt(br.readLine());
            if (cost > well[i]) {
                cost = well[i];
                minIdx = i;
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            dist[minIdx][i] = Math.min(dist[minIdx][i], well[i]);
        }

        System.out.println(primMST(minIdx));
    }

    private static int primMST(int start) {
        boolean[] visited = new boolean[N];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, well[start]));
        int total = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (visited[now.end]) continue;
            total += now.cost;
            visited[now.end] = true;

            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;
                pq.offer(new Edge(i, dist[now.end][i]));
            }
        }

        return total;
    }
}
