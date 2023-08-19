package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 풀이 시작 : 8:18
 * 풀이 완료 : 8:30
 * 풀이 시간 : 12분
 *
 * 문제 해석
 * 정사각형 모양 지도
 * 1 : 집, 0 : 빈 공간
 * 좌우, 상하로 다른 집이 있는 경우 연결되었다 => 하나의 단지
 * 지도륻 입력받았을 때 단지의 수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 출력
 *
 * 구해야 하는 것
 * 지도륻 입력받았을 때 단지의 수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 출력
 *
 * 문제 입력
 * 첫째 줄 : 지도의 크기 N
 * 둘째 줄 ~ N개 줄 : 지도 모양
 *
 * 제한 요소
 * 5 <= N <= 25
 *
 * 생각나는 풀이
 * dfs or bfs로 지도 탐색해서 단지 수 및 단지 크기 카운트
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 맵
 * 2. dfs or bfs
 * 3. 단지 수 저장할 cnt 변수
 * 4. 단지 크기 저장할 list
 * 5. 인접 탐색용 delta 배열
 *  list 정렬
 */
public class BOJ_2667 {
    static int N, cnt = 0;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> list = new ArrayList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '0') visited[i][j] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    cnt++;
                    list.add(dfs(i, j));
                }
            }
        }

        Collections.sort(list);
        System.out.println(cnt);
        for (int now : list) System.out.println(now);
    }

    private static int dfs(int x, int y) {
        visited[x][y] = true;
        int cnt = 1;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (!isInRange(nextX, nextY) || visited[nextX][nextY]) continue;
            cnt += dfs(nextX, nextY);
        }

        return cnt;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
