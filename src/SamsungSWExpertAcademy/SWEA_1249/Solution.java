package SamsungSWExpertAcademy.SWEA_1249;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution
{
    static int N;
    static int[][] time;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] visited;

    static class Node {
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception
    {
        /*
        FileInputStream import와 System.setIn()은 온라인 제출시 삭제
        Format
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_번호/input.txt");
         */
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_1249/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            time = new int[N][N];
            visited = new int[N][N];

            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], Integer.MAX_VALUE);
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    time[i][j] = s.charAt(j) - '0';
                }
            }

            sb.append('#').append(test_case).append(' ').append(findMinTime()).append('\n');
        }
        System.out.println(sb);
    }

    private static int findMinTime() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0));
        visited[0][0] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || visited[nextX][nextY] <= now.cost + time[nextX][nextY]) continue;
                visited[nextX][nextY] = now.cost + time[nextX][nextY];
                q.offer(new Node(nextX, nextY, visited[nextX][nextY]));
            }
        }

        return visited[N - 1][N - 1];
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
