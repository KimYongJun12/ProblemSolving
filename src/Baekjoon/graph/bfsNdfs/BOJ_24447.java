/*
너비우선탐색
구해야 할 값 : 각 노드의 깊이 * 각 노드의 방문 순서의 총 합
시작 정점의 방문 순서 : 1
시작 정점의 깊이 : 0
방문할 수 없는 노드의 순서 : 0
방문하지 못한 노드의 깊이 : -1
인접 정점은 오름차순으로 방문
정점의 수 N : 5 <= N <= 100_000
간선의 수 M : 1 <= M <= 200_000
양방향 간선

1. 그래프 생성
2. BFS 탐색하며 방문 순서와 depth 체크
2-1. 인접 정점 방문 순서는 오름차순
3. 모든 정점에 대해 깊이 * 방문 순서를 더함
 */

package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_24447 {
    static int N;
    static PriorityQueue<Integer>[] graph;

    static class Node {
        int end, depth;

        public Node(int end, int depth) {
            this.end = end;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        graph = new PriorityQueue[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].offer(e);
            graph[e].offer(s);
        }

        System.out.println(bfs(R));
    }

    private static long bfs(int s) {
        long[] depth = new long[N + 1];
        long[] visited = new long[N + 1];
        visited[s] = 1;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(s, 0));
        int cnt = 2;

        while (!q.isEmpty()) {
            Node now = q.poll();

            while (!graph[now.end].isEmpty()) {
                int next = graph[now.end].poll();
                if (visited[next] != 0) continue;
                visited[next] = cnt++;
                depth[next] = now.depth + 1;
                q.offer(new Node(next, now.depth + 1));
            }
        }

        long sum = 0L;
        for (int i = 1; i <= N; i++) {
            if (visited[i] == 0L) continue;
            sum += visited[i] * depth[i];
        }

        return sum;
    }
}
