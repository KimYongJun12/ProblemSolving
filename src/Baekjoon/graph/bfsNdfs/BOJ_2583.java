package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2583 {
    static int M, N;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> area = new ArrayList<>();

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
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        visited = new boolean[M][N];

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int sX = Integer.parseInt(st.nextToken());
            int sY = Integer.parseInt(st.nextToken());
            int eX = Integer.parseInt(st.nextToken());
            int eY = Integer.parseInt(st.nextToken());

            for (int i = sX; i < eX; i++) {
                for (int j = sY; j < eY; j++) {
                    visited[j][i] = true;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    area.add(bfs(i, j));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(area.size()).append('\n');

        Collections.sort(area);
        for (int ans : area) {
            sb.append(ans).append(' ');
        }

        System.out.println(sb);
    }

    private static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new Node(x, y));

        int size = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || visited[nextX][nextY]) continue;

                size++;
                visited[nextX][nextY] = true;
                q.offer(new Node(nextX, nextY));
            }
        }

        return size;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}
