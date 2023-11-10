package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:25
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 두 대의 경찰차
 * 시작지점 (1, 1), (N, N)
 * 사건이 순서대로 주어질 때 경찰차가 움직인 거리 합이 최소가 되는 값과 각 사건을 해결한 경찰차의 번호를 구해야 함
 *
 * 구해야 하는 것
 * 사건이 순서대로 주어질 때 경찰차가 움직인 거리 합이 최소가 되는 값과 각 사건을 해결한 경찰차의 번호를 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : 도로 개수 N
 * 둘째 줄 : 사건 수 W
 * 셋째 줄 ~ W개 줄 : 사건 좌표
 *
 * 제한 요소
 * 5 <= N <= 1000
 * 1 <= W <= 1000
 *
 * 생각나는 풀이
 * dp
 * dp[i][j] = 각 경찰차가 마지막으로 해결한 사건이 i번, j번일 때 이동 거리 최솟값
 * dp[i][j] = min(dp[i - 1][j] + dist[i], dp[i][j - 1] + dist[j])
 * i or j == W가 되면 탈출
 *
 * 구현해야 하는 기능
 * 1. 사건 저장할 배열
 * 2. dp 배열
 *
 */
public class BOJ_2618 {
    static int N, W;
    static Point[] crimes;
    static int[][] dp;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDist(Point p) {
            return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        crimes = new Point[W + 1];
        dp = new int[W + 1][W + 1];


        for (int i = 1; i <= W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            crimes[i] = new Point(x, y);
        }

        sb.append(findMin(0, 0, 1)).append('\n');

        int first = 0, second = 0;

        for (int i = 1; i <= W; i++) {
            int fDist = (first == 0) ? crimes[i].getDist(new Point(1, 1)) : crimes[i].getDist(crimes[first]);
            int num;
            // 첫번째가 움직인게 최소
            if (dp[first][second] - fDist == dp[i][second]) {
                num = 1;
                first = i;
            }
            // 두번째가 움직인게 최소
            else {
                num = 2;
                second = i;
            }

            sb.append(num).append('\n');
        }

        System.out.println(sb);
    }

    private static int findMin(int first, int second, int idx) {
        if (idx > W) return 0;
        if (dp[first][second] != 0) return dp[first][second];
        int fDist, sDist;
        // 첫 번째 차가 움직이는 거리 = 시작 지점이라면 (1,1)부터, 그렇지 않다면 현재 좌표부터 현재 범죄 좌표까지의 거리
        fDist = (first == 0) ? crimes[idx].getDist(new Point(1, 1)) : crimes[idx].getDist(crimes[first]);
        sDist = (second == 0) ? crimes[idx].getDist(new Point(N, N)) : crimes[idx].getDist(crimes[second]);

        return dp[first][second] = Math.min(findMin(idx, second, idx + 1) + fDist, findMin(first, idx, idx + 1) + sDist);
    }

}