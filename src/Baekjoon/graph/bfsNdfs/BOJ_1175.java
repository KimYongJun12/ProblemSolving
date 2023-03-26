package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1175 {
    static int N, M;
    static char[][] classRoom;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1}; // 상하좌우

    static class Node {
        int dir, x, y, time, check;
        public Node(int dir, int x, int y, int time, int check) {
            this.dir = dir;
            this.x = x;
            this.y = y;
            this.time = time;
            this.check = check;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        classRoom = new char[N][];
        Node start = null;
        Node[] end = new Node[2];

        for (int i = 0, idx = 0; i < N; i++) {
            classRoom[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                char now = classRoom[i][j];
                if (now == 'S') start = new Node(-1, i, j, 0, 0);
                else if (now == 'C') end[idx++] = new Node(-1, i, j, 0, 0);
            }
        }

        System.out.println(bfs(start, end));
    }

    private static int bfs(Node start, Node[] end) {
        /** visited[방향][x][y][c1, c2 방문 여부]
         * 0 = 방문 X
         * 1 = c1 방문
         * 2 = c2 방문
         * 3 = 둘 다 방문 = 최소값 리턴
         */
        boolean[][][][] visited = new boolean[4][N][M][3];
        for (int i = 0; i < 4; i++) visited[i][start.x][start.y][0] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == end[0].x && now.y == end[0].y && now.check != 1) now.check += 1;
            else if (now.x == end[1].x && now.y == end[1].y && now.check != 2) now.check += 2;
            if (now.check == 3) return now.time;

            for (int i = 0; i < 4; i++) {
                if (now.dir == i) continue;
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || classRoom[nextX][nextY] == '#' || visited[i][nextX][nextY][now.check]) continue;
                visited[i][nextX][nextY][now.check] = true;
                q.offer(new Node(i, nextX, nextY, now.time + 1, now.check));

            }
        }
        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
