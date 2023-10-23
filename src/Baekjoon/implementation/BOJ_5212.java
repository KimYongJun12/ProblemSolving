package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:27
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * R * C 격자
 * 'X' = 땅
 * '.' = 바다
 * 인접한 구역이 3개 이상 바다면 잠김
 * 격자 외부는 전부 바다
 * 잠긴 후 모양
 *
 * 구해야 하는 것
 * 잠긴 후 모양
 *
 * 문제 입력
 * 첫째 줄 : R, C
 * 둘째 줄 ~ R개 줄 : 초기 맵
 *
 * 제한 요소
 * 1 <= R, C <= 10
 *
 * 생각나는 풀이
 * 배열 순회
 *
 * 구현해야 하는 기능
 * 1. 초기 맵 배열
 * 2. 인접 땅 저장할 배열
 *
 */
public class BOJ_5212 {
    static int R, C;
    static char[][] map;
    static int[][] land;
    static int[] dr = {0, 1};
    static int[] dc = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][];
        land = new int[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int dir = 0; dir < 2; dir++) {
                    int nextX = i + dr[dir];
                    int nextY = j + dc[dir];
                    if (!isInRange(nextX, nextY)) continue;
                    if (map[i][j] == 'X') land[nextX][nextY]++;
                    if (map[nextX][nextY] == 'X') land[i][j]++;
                }
            }
        }

        int minX, minY, maxX, maxY;
        minX = minY = 11;
        maxX = maxY = -1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') continue;
                if (land[i][j] <= 1) map[i][j] = '.';
                else {
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                    maxX = Math.max(maxX, i);
                    maxY = Math.max(maxY, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static boolean isInRange(int x, int y) {
        return x < R && y < C;
    }
}
