package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636 {
    static int N, M;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num;
        int ans = 0, cnt = 0;
        while ((num = simulation()) != 0) {
            cnt++;
            ans = num;
        }

        System.out.println(cnt);
        System.out.println(ans);
    }

    private static int simulation() {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        q.offer(new Node(0, 0));
        int cheeSize = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || visited[nextX][nextY]) continue;
                if (board[nextX][nextY] == 1) {
                    cheeSize++;
                    board[nextX][nextY] = 0;
                } else {
                    q.offer(new Node(nextX, nextY));
                }

                visited[nextX][nextY] = true;
            }
        }

        return cheeSize;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
