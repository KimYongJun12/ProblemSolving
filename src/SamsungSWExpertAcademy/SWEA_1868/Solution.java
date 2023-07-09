package SamsungSWExpertAcademy.SWEA_1868;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution
{
    static int N;
    static char[][] map;
    static int[][] numOfMines;
    static boolean[][] visited;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception
    {
        /*
        FileInputStream import와 System.setIn()은 온라인 제출시 삭제
        Format
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_번호/input.txt");
         */
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_1868/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            numOfMines = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '*') {
                        visited[i][j] = true;
                        for (int k = 0; k < 8; k++) {
                            int nearX = i + dx[k];
                            int nearY = j + dy[k];

                            if (!isInRange(nearX, nearY)) continue;
                            numOfMines[nearX][nearY]++;
                        }
                    }
                }
            }

            sb.append('#').append(test_case).append(' ').append(findMinCnt()).append('\n');
        }
        System.out.println(sb);
    }

    private static int findMinCnt() {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (numOfMines[i][j] == 0 && !visited[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 8; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || visited[nextX][nextY]) continue;
                if (numOfMines[nextX][nextY] == 0) {
                    q.offer(new Node(nextX, nextY));
                }

                visited[nextX][nextY] = true;
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
