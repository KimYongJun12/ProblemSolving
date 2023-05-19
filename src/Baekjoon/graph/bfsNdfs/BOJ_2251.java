package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_2251 {
    static TreeSet<Integer> set = new TreeSet<>();
    static int[] bottleSize = new int[3];

    static class Water {
        int[] amount;

        public Water(int[] amount) {
            this.amount = amount.clone();
        }

        Water move(int from, int to) {
            int[] next = amount.clone();

            if (amount[from] + amount[to] > bottleSize[to]) {
                next[to] = bottleSize[to];
                next[from] -= bottleSize[to] - amount[to];
            } else {
                next[from] = 0;
                next[to] += amount[from];
            }

            return new Water(next);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        bottleSize[0] = A;
        bottleSize[1] = B;
        bottleSize[2] = C;
        bfs();

        StringBuilder sb = new StringBuilder();
        for (int ans : set) {
            sb.append(ans).append(' ');
        }

        System.out.println(sb);
    }

    private static void bfs() {
        boolean[][] visited = new boolean[bottleSize[0] + 1][bottleSize[1] + 1];
        visited[0][0] = true;
        Queue<Water> q = new LinkedList<>();
        q.offer(new Water(new int[]{0, 0, bottleSize[2]}));

        while (!q.isEmpty()) {
            Water now = q.poll();

            if (now.amount[0] == 0) {
                set.add(now.amount[2]);
            }

            for (int i = 0; i < 3; i++) {
                if (now.amount[i] == 0) continue;
                for (int j = 0; j < 3; j++) {
                    if (i == j) continue;
                    Water next = now.move(i, j);
                    if (!visited[next.amount[0]][next.amount[1]]) {
                        q.offer(next);
                        visited[next.amount[0]][next.amount[1]] = true;
                    }
                }
            }
        }
    }

}
