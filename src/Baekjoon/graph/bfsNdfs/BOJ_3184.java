package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3184 {
    static int R, C, sheep = 0, wolf = 0;
    static char[][] backyard;
    static boolean[][] visited;
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
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        backyard = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            backyard[i] = s.toCharArray();
            for (int j = 0; j < C; j++) {
                char now = backyard[i][j];
                if (now == '#') {
                    visited[i][j] = true;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && backyard[i][j] != '.') {
                    bfs(i, j);
                }
            }
        }

        System.out.println(sheep + " " + wolf);
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;

        int s = 0, w = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (backyard[now.x][now.y] == 'o') {
                s++;
            } else if (backyard[now.x][now.y] == 'v') {
                w++;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || visited[nextX][nextY]) continue;
                q.offer(new Node(nextX, nextY));
                visited[nextX][nextY] = true;
            }
        }

        if (s > w) {
            w = 0;
        } else s = 0;

        sheep += s;
        wolf += w;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
