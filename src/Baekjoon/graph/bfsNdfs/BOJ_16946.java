package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16946 {
    static int N, M;
    static int[][] map;
    static int[][] group;
    static Queue<Node> wallList = new LinkedList<>();
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
        map = new int[N][M];
        group = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                int now = s.charAt(j) - '0';
                map[i][j] = now;
                if (now == 1) {
                    wallList.offer(new Node(i, j));
                    group[i][j] = -1;
                }
            }
        }

        HashMap<Integer, Integer> numOfGroupMembers = new HashMap<>();
        numOfGroupMembers.put(-1, 0);

        int groupNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (group[i][j] == 0) {
                    numOfGroupMembers.put(groupNum, bfs(new Node(i, j), groupNum++));
                }
            }
        }

        HashSet<Integer> nearGroup = new HashSet<>();
        while (!wallList.isEmpty()) {
            nearGroup.clear();
            Node now = wallList.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY)) continue;
                nearGroup.add(group[nextX][nextY]);
            }

            for (int idx : nearGroup) {
                map[now.x][now.y] += numOfGroupMembers.get(idx);
            }
            map[now.x][now.y] %= 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static int bfs(Node start, int groupNum) {
        Queue<Node> q = new LinkedList<>();
        q.offer(start);

        int cnt = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            group[now.x][now.y] = groupNum;
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || group[nextX][nextY] != 0) continue;
                group[nextX][nextY] = groupNum;
                q.offer(new Node(nextX, nextY));
            }
        }

        return cnt;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
