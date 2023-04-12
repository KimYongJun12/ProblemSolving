package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1113 {
    static int N, M;
    static int[][] pool;
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pool = new int[N][M];

        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                pool[i][j] = s.charAt(j) - '0';
                maxHeight = Math.max(maxHeight, pool[i][j]);
            }
        }

        int ans = 0;

        // 물의 높이가 2일 때부터 차례대로 bfs 탐색하며 현재 물의 높이보다 작을 때만 탐색 실시.
        for (int i = 2; i <= maxHeight; i++) {
            visited = new boolean[N][M];
            for (int j = 1; j < N - 1; j++) {
                for (int k = 1; k < M - 1; k++) {
                    if (!visited[j][k] && pool[j][k] < i) ans += bfs(i, j, k);
                }
            }
        }

        System.out.println(ans);
    }

    private static int bfs(int height, int startX, int startY) {
        int water = 1;
        boolean checkGround = false;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startX, startY));
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY)) {
                    /**
                     * 땅으로 흘렀을 때 바로 return 0 해버리면 방문 여부가 달라짐.
                     * bfs가 끝난 후 리턴값만 0으로 넘겨야 함.
                     */
                    checkGround = true;
                    continue;
                }
                // 이웃한 블록이 물의 높이보다 낮은 경우 bfs 탐색
                if (visited[nextX][nextY] || pool[nextX][nextY] >= height) continue;
                visited[nextX][nextY] = true;
                q.offer(new Node(nextX, nextY));
                water++;
            }
        }

        return checkGround ? 0 : water;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
