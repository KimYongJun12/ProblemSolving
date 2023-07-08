package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16948 {
    static int N;
    static int[] dx = {-2, -2, 0, 0, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -1, 1};

    static class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        System.out.println(bfs(r1, c1, r2, c2));
    }

    private static int bfs(int r1, int c1, int r2, int c2) {
        boolean[][] visited = new boolean[N][N];
        visited[r1][c1] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r1, c1, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 6; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || visited[nextX][nextY]) continue;
                if (nextX == r2 && nextY == c2) return now.cnt + 1;
                q.offer(new Node(nextX, nextY, now.cnt + 1));
                visited[nextX][nextY] = true;
            }
        }
        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
