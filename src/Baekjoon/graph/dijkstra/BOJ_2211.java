package Baekjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풀이 시작 : 10:48
 * 풀이 완료 : 11:26
 * 풀이 시간 :
 *
 * 문제 해석
 * N개의 컴퓨터로 구성된 네트워크가 있다
 * 1번 컴퓨터가 보안 컴퓨터
 * 1번 컴퓨터에서 모든 컴퓨터로의 최단 경로를 구해야 함
 *
 * 구해야 하는 것
 * 1번 컴퓨터에서 모든 컴퓨터로의 최단 경로를 구해야 함
 * 복구할 회선의 개수 K 출력
 * K개의 줄에 복구한 회선의 정보 A B를 한 줄에 출력, A와 B를 연결했다는 의미
 *
 * 문제 입력
 * 첫째 줄 : N, M
 * 둘째 줄 ~ M개 줄 : 회선 정보 A, B, C
 *  - A, B = 노드, C = 비용
 *
 * 제한 요소
 * 1 <= PC 번호 <= N
 * 1 <= C <= 10
 *
 * 생각나는 풀이
 * 다익스트라
 * 최단경로를 구하면서 비용이 갱신됐을 때 이전 노드를 저장
 *
 * 구현해야 하는 기능
 * 1. 다익스트라
 * 2. 이전 노드 저장할 배열
 *
 */
public class BOJ_2211 {
    static int N, INF = 987654321;
    static int[] prev;
    static ArrayList<Edge>[] graph;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        prev = new int[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, cost));
            graph[b].add(new Edge(a, cost));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (prev[i] == 0) continue;
            sb.append(i).append(' ').append(prev[i]).append('\n');
            cnt++;
        }

        System.out.println(cnt);
        System.out.println(sb);
    }

    private static void dijkstra() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int nowEnd = now.end;
            int nowCost = now.cost;
            if (nowCost > dist[nowEnd]) continue;

            for (Edge next : graph[nowEnd]) {
                if (dist[next.end] > dist[nowEnd] + next.cost) {
                    dist[next.end] = dist[nowEnd] + next.cost;
                    prev[next.end] = nowEnd;
                    pq.offer(new Edge(next.end, dist[next.end]));
                }
            }
        }

    }
}
