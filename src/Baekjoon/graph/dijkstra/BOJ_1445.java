package Baekjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1445 {
    static int N, M;
    static char[][] map;
    static int[][] cost;
    static Node[] fromTo;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int GARBAGE = 2501, NEAR_GARBAGE = 1, INF = 6_252_501;


    static class Node implements Comparable<Node> {
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][];
        cost = new int[N][M];
        fromTo = new Node[2];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0, idx = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char entity = map[i][j];

                if (entity == 'F' || entity == 'S') {
                    fromTo[idx++] = new Node(i, j, 0);
                } else if (entity == 'g') {
                    cost[i][j] = GARBAGE;
                } else {
                    for (int k = 0; k < 4; k++) {
                        int nearX = i + dx[k];
                        int nearY = j + dy[k];

                        if (!isInRange(nearX, nearY)) continue;
                        if (map[nearX][nearY] == 'g') {
                            cost[i][j] = NEAR_GARBAGE;
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(findPath());
    }

    private static String findPath() {
        Node start = fromTo[0];
        Node end = fromTo[1];

        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], INF);
        dist[start.x][start.y] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(start);

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.cost > dist[now.x][now.y]) continue;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                if (!isInRange(nextX, nextY)) continue;

                if (dist[nextX][nextY] > now.cost + cost[nextX][nextY]) {
                    dist[nextX][nextY] = now.cost + cost[nextX][nextY];
                    pq.offer(new Node(nextX,nextY, dist[nextX][nextY]));
                }
            }
        }

        int garbages = dist[end.x][end.y] / GARBAGE;
        int nearGarbages = dist[end.x][end.y] % GARBAGE / NEAR_GARBAGE;

        return garbages + " " + nearGarbages;

    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }


}
