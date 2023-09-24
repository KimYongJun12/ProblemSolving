package Baekjoon.graph.minimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 6:54
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 행성 간 플로우를 설치하여 운용
 * 모든 행성을 연결하면서 플로우 관리 비용을 최소화해야 함
 *
 * 구해야 하는 것
 * 모든 행성을 연결하면서 플로우 관리 비용을 최소화할 때 비용
 *
 * 문제 입력
 * 첫째 줄 : 행성의 수 N
 * 둘째 줄 ~ N개 줄 : 각 행성간의 플로우 관리 비용
 *
 * 제한 요소
 * 1 <= N <= 1000
 * 1 <= i, j <= N
 * 1 <= Cij <= 100_000_000
 * Cij = Cji
 * Cii = 0
 *
 * 생각나는 풀이
 * MST
 * 간선이 많으므로 크루스칼보다는 프림이 유리할듯
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 비용 저장
 * 2. 프림 알고리즘
 *
 */
public class BOJ_16398 {
    static int N;
    static int[][] cost;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int cost, end;

        public Edge(int cost, int end) {
            this.cost = cost;
            this.end = end;
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
        cost = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N];
        visited[0] = true;

        long total = 0L;
        int now = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N; j++) {
                if (now == j) continue;
                pq.offer(new Edge(cost[now][j], j));
            }
            while (true) {
                Edge edge = pq.poll();
                if (visited[edge.end]) continue;
                total += edge.cost;
                visited[edge.end] = true;
                now = edge.end;
                break;
            }
        }

        System.out.println(total);
    }
}
