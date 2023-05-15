package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17141 {
    static int N, M, minTime = Integer.MAX_VALUE, emptyRoom = 0;
    static int[][] map;
    static Node[] virusPlace;
    static boolean[] isVirus;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        Queue<Node> virusQ = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value != 1) emptyRoom++;
                if (value == 2) virusQ.add(new Node(i, j, 0));
                map[i][j] = (value == 1) ? 1 : 0;
            }
        }

        emptyRoom -= M;

        virusPlace = new Node[virusQ.size()];
        for (int i = 0; i < virusPlace.length; i++) {
            virusPlace[i] = virusQ.poll();
        }

        isVirus = new boolean[virusPlace.length];
        backTracking(0, 0);

        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }

    private static void backTracking(int cnt, int idx) {
        if (cnt == M) {
            minTime = Math.min(minTime, bfs());
            return;
        }

        if (idx == isVirus.length) return;

        for (int i = idx; i < isVirus.length; i++) {
            isVirus[i] = true;
            backTracking(cnt + 1, i + 1);
            isVirus[i] = false;
        }
    }

    private static int bfs() {
        int empty = emptyRoom;
        boolean[][] visited = new boolean[N][N];

        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < isVirus.length; i++) {
            if (isVirus[i]) {
                Node now = virusPlace[i];
                q.offer(now);
                visited[now.x][now.y] = true;
            }
        }

        int time = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();
            time = Math.max(time, now.time);
            if (time >= minTime) break;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY)
                        || visited[nextX][nextY]
                        || map[nextX][nextY] == 1) continue;

                q.offer(new Node(nextX, nextY, now.time + 1));
                visited[nextX][nextY] = true;
                empty--;
            }
        }

        return (empty == 0) ? time : Integer.MAX_VALUE;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
