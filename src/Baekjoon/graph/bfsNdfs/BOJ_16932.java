package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16932 {
    static int n, m;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] groupSize;
    static int[][] groupNumber;

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

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        groupSize = new int[n][m];
        groupNumber = new int[n][m];

        Queue<Node> zeros = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) zeros.offer(new Node(i, j));
            }
        }

        int max = 0;
        visited = new boolean[n][m];
        for (int i = 0, groupNum = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) bfs(i, j, groupNum++);
            }
        }

        HashSet<Integer> set = new HashSet<>();
        while (!zeros.isEmpty()) {
            Node now = zeros.poll();
            int nowSize = 1;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || set.contains(groupNumber[nextX][nextY])) continue;
                nowSize += groupSize[nextX][nextY];
                set.add(groupNumber[nextX][nextY]);
            }

            max = Math.max(max, nowSize);
            set.clear();
        }

        System.out.println(max);
    }

    private static void bfs(int x, int y, int groupNum) {
        int size = 0;
        Queue<Node> q = new LinkedList<>();
        Queue<Node> group = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new Node(x, y));

        while (!q.isEmpty()) {
            Node now = q.poll();
            group.offer(now);
            size++;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || arr[nextX][nextY] == 0 || visited[nextX][nextY]) continue;

                q.offer(new Node(nextX, nextY));
                visited[nextX][nextY] = true;
            }

        }

        while (!group.isEmpty()) {
            Node now = group.poll();
            groupSize[now.x][now.y] = size;
            groupNumber[now.x][now.y] = groupNum;
        }

    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
