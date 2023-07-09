package SamsungSWExpertAcademy.SWEA_1247;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution
{
    static int N, INF = 87654321;
    static int[][] distance;
    static int[][] dp;
    

    public static void main(String[] args) throws Exception
    {
        /*
        FileInputStream import와 System.setIn()은 온라인 제출시 삭제
        Format
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_번호/input.txt");
         */
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_1247/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            distance = new int[N + 2][N + 2];
            int[][] location = new int[N + 2][2];

            st = new StringTokenizer(br.readLine());
            location[0][0] = Integer.parseInt(st.nextToken());
            location[0][1] = Integer.parseInt(st.nextToken());
            location[N + 1][0] = Integer.parseInt(st.nextToken());
            location[N + 1][1] = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < 2; j++) {
                    location[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N + 1; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    int dist = Math.abs(location[i][0] - location[j][0]) + Math.abs(location[i][1] - location[j][1]);
                    distance[i][j] = distance[j][i] = dist;
                }
            }

            sb.append('#').append(test_case).append(' ').append(findMinTime()).append('\n');
        }

        System.out.println(sb);
    }

    private static int findMinTime() {
        dp = new int[N + 1][1 << (N + 1)];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], INF);
        }

        return dfs(0, 0);
    }

    private static int dfs(int nowHouse, int flag) {
        flag |= (1 << nowHouse);

        if (flag == (1 << (N + 1)) - 1) return distance[nowHouse][N + 1];
        if (dp[nowHouse][flag] != INF) return dp[nowHouse][flag];

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i <= N; i++) {
            if (i == nowHouse) continue;
            if ((flag & (1 << i)) == 0) {
                minCost = Math.min(minCost, dfs(i, flag) + distance[nowHouse][i]);
            }
        }

        return dp[nowHouse][flag] = minCost;
    }
}
