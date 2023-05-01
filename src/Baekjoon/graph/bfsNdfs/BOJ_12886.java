package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12886 {
    static class Stone {
        int a, b, c;

        public Stone(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(bfs(A, B, C));
    }

    private static int bfs(int a, int b, int c) {
        if ((a + b + c) % 3 != 0) return 0;

        Queue<Stone> q = new LinkedList<>();
        boolean[][] visited = new boolean[1501][1501];
        q.offer(new Stone(a, b, c));
        visited[a][b] = true;

        while (!q.isEmpty()) {
            Stone now = q.poll();
            a = now.a;
            b = now.b;
            c = now.c;

            if (a == b && b == c) return 1;

            if (a != b) divideStone(q, visited, a, b, c);
            if (a != c) divideStone(q, visited, a, c, b);
            if (b != c) divideStone(q, visited, b, c, a);
        }

        return 0;
    }

    private static void divideStone(Queue<Stone> q, boolean[][] visited, int x, int y, int z) {
        int nextX = (x > y) ? x - y : x * 2;
        int nextY = (x > y) ? y * 2 : y - x;

        if (!visited[nextX][nextY]) {
            q.offer(new Stone(nextX, nextY, z));
            visited[nextX][nextY] = true;
        }
    }

}
