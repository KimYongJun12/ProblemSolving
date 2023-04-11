package Baekjoon.graph.bellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1219 {
    static int N, M;
    static Edge[] roads;
    static int[] income;

    static class Edge {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        roads = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = -Integer.parseInt(st.nextToken());

            roads[i] = new Edge(start, end, cost);
        }

        income = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) income[i] = Integer.parseInt(st.nextToken());

        System.out.println(bellmanFord(startCity, endCity));


    }

    private static String bellmanFord(int startCity, int endCity) {
        long[] dist = new long[N];

        Arrays.fill(dist, Long.MIN_VALUE);
        dist[startCity] = income[startCity];

        // N의 최대값의 2배만큼 모든 간선을 탐색하면서 최대한 돈의 액수가 무한으로 발산할 수 있게끔 함
        for (int i = 0; i < N + 50; i++) {
            for (int j = 0; j < M; j++) {
                Edge now = roads[j];
                if (dist[now.start] == Long.MIN_VALUE) continue;
                // 시작점에서 돈이 무한 = 도착점도 무한
                else if (dist[now.start] == Long.MAX_VALUE) dist[now.end] = Long.MAX_VALUE;
                else if (dist[now.end] < dist[now.start] + now.cost + income[now.end]) {
                    if (i >= N - 1) { // N번째 탐색부터 양의 가중치 루프 = 무한 돈복사를 의미
                        dist[now.end] = Long.MAX_VALUE;
                        continue;
                    }
                    dist[now.end] = dist[now.start] + now.cost + income[now.end];
                }
            }
        }

        /**
         * 충분히 벨만-포드 알고리즘을 거치고 나서는 목적지의 값에 따라 리턴값을 판단 가능
         * 목적지의 최종값이 초기값과 같다면 도달하지 못했다는 의미
         * 목적지의 최종값이 무한대라면 중간에 양의 가중치 루프가 있었다는 의미
         * 위의 두 경우에 해당하지 않는다면 최종값이 곧 최대값
         */
        if (dist[endCity] == Long.MIN_VALUE) return "gg";
        if (dist[endCity] == Long.MAX_VALUE) return "Gee";
        return String.valueOf(dist[endCity]);
    }

}
