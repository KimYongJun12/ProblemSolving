package SamsungSWExpertAcademy.SWEA_4193;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution
{
    static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String args[]) throws Exception
    {
        /*
        FileInputStream import와 System.setIn()은 온라인 제출시 삭제
        Format
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_번호/input.txt");
         */
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_4193/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = st.nextToken().charAt(0);
                    if (map[i][j] == '1') {
                        visited[i][j] = true;
                    }
                }
            }

            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int goalX = Integer.parseInt(st.nextToken());
            int goalY = Integer.parseInt(st.nextToken());

            sb.append('#').append(test_case).append(' ').append(bfs(startX, startY, goalX, goalY)).append('\n');
        }
        System.out.println(sb);
    }

    private static int bfs(int startX, int startY, int goalX, int goalY) {
        visited[startX][startY] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startX, startY, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!isInRange(nextX, nextY) || visited[nextX][nextY]) continue;
                if (map[nextX][nextY] == '2' && now.time % 3 != 2) {
                    q.offer(new Node(now.x, now.y, now.time + 1));
                    continue;
                }

                if (nextX == goalX && nextY == goalY) return now.time + 1;
                visited[nextX][nextY] = true;
                q.offer(new Node(nextX, nextY, now.time + 1));
            }
        }

        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
