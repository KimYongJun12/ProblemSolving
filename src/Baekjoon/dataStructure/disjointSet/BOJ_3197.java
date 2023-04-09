package Baekjoon.dataStructure.disjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3197 {
    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static Node[] swans = new Node[2];
    static Node[][] parent;
    static boolean[][] visited;
    static Queue<Node> q = new LinkedList<>();

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

        map = new char[R][C];
        parent = new Node[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0, idx = 0; i < R; i++) {
            fo : for (int j = 0; j < C; j++) {
                parent[i][j] = new Node(i, j);

                if (map[i][j] == 'X') {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (isInRange(nx, ny) && map[nx][ny] != 'X') {
                            q.offer(new Node(i, j));
                            continue fo;
                        }
                    }
                } else if (map[i][j] == 'L') {
                    swans[idx++] = new Node(i, j);
                    map[i][j] = '.';
                }

                for (int k = 0; k < 2; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (isInRange(nx, ny) && map[nx][ny] == '.') union(new Node(nx, ny), new Node(i, j));
                }
            }
        }

        int cnt = 0;
        Node swan1 = find(swans[0]);
        Node swan2 = find(swans[1]);

        while (swan1.x != swan2.x || swan1.y != swan2.y) {
            simulation();
            cnt++;
            swan1 = find(swan1);
            swan2 = find(swan2);
        }

        System.out.println(cnt);
    }

    private static void simulation() {
        int qSize = q.size();

        for (int i = 0; i < qSize; i++) {
            Node now = q.poll();
            map[now.x][now.y] = '.';

            for (int j = 0; j < 4; j++) {
                int nx = now.x + dx[j];
                int ny = now.y + dy[j];

                if (!isInRange(nx, ny)) continue;
                if (map[nx][ny] == '.') {
                    union(now, new Node(nx, ny));
                } else {
                    if (visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny));
                }
            }
        }
    }

    private static void union(Node a, Node b) {
        a = find(a);
        b = find(b);

        if (a.x > b.x || a.y > b.y) {
            Node temp = new Node(a.x, a.y);
            a = new Node(b.x, b.y);
            b = temp;
        }

        parent[b.x][b.y] = a;
    }

    private static Node find(Node a) {
        if (parent[a.x][a.y].x == a.x && parent[a.x][a.y].y == a.y) return a;

        return parent[a.x][a.y] = find(parent[a.x][a.y]);
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}