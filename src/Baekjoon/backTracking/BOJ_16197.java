package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16197 {
    static int N, M, min = 11;
    static char[][] map;
    static int[] dir = new int[10];
    static Node[] coins = new Node[2];
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
        map = new char[N][M];

        for (int i = 0, idx = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'o') {
                    coins[idx++] = new Node(i, j);
                }
            }
        }
        
        backTracking(0);
        if (min > 10) min = -1;
        System.out.println(min);
    }

    private static void backTracking(int depth) {
        if (depth == 10) {
            min = Math.min(min, simulation());
            return;
        }

        for (int i = 0; i < 4; i++) {
            dir[9 - depth] = i;
            backTracking(depth + 1);
        }
    }

    private static int simulation() {
        Node[] coin = new Node[2];
        for (int i = 0; i < 2; i++) {
            coin[i] = new Node(coins[i].x, coins[i].y);
        }

        boolean[] isFall = new boolean[2];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                int nextX = coin[j].x + dx[dir[i]];
                int nextY = coin[j].y + dy[dir[i]];

                if (!isInRange(nextX, nextY)) {
                    isFall[j] = true;
                    continue;
                }

                if (map[nextX][nextY] != '#') {
                    coin[j].x = nextX;
                    coin[j].y = nextY;
                }
            }
            
            if (isFall[0] ^ isFall[1]) return i + 1;
            if (isFall[0] & isFall[1]) return 11;
        }

        return 11;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
