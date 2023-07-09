package SamsungSWExpertAcademy.SWEA_7465;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
    static int N;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String args[]) throws Exception
    {
        /*
        FileInputStream import와 System.setIn()은 온라인 제출시 삭제
        Format
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_번호/input.txt");
         */
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_7465/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N + 1];
            visited = new boolean[N + 1];

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                graph[b].add(a);
            }

            int ans = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    ans++;
                    bfs(i);
                }
            }

            sb.append('#').append(test_case).append(' ').append(ans).append('\n');
        }

        System.out.println(sb);
    }
    private static void bfs(int start) {
        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                if (visited[next]) continue;

                visited[next] = true;
                q.offer(next);
            }
        }
    }
}
