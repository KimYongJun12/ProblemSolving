package Baekjoon.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 풀이 시작 : 8:30
 * 풀이 완료 : 8:58
 * 풀이 시간 : 28분
 *
 * 문제 해석
 * 전구 100개가 10 * 10 정사각형 모양으로 있음
 * 전구의 스위치를 누르면 그 전구와 인접한 상하좌우 전구의 상태도 바뀜
 * 모든 전구를 끄기 위해 최소한으로 눌러야 하는 스위치 개수를 구해야 함
 *
 * 구해야 하는 것
 * 모든 전구를 끄기 위해 최소한으로 눌러야 하는 스위치 개수를 구해야 함
 * 만약 전부 끄지 못한다면 -1
 *
 * 문제 입력
 * 첫째 줄 ~ 10번째 줄 : 전구의 상태
 * # : 꺼짐, O : 켜짐
 *
 * 제한 요소
 *
 * 생각나는 풀이
 * 위에서부터 내려오면서 현재 전구를 끌지 말지 선택해야 함
 * 현재 칸의 윗줄이 꺼져있다면 그곳은 켜면 안됨
 *  => 다시 끈다면 같은 곳을 2번 누르는 것
 *  => 처음부터 누르지 않으면 0회임
 * 그러면 맨 윗줄에서 누르는 버튼의 경우만 선택하면 그 아래칸부터는 항상 정해짐
 * => 맨 윗줄 누를 버튼 고르는 경우의 수 => 부분집합 => 2^10 => 1024
 * 둘째 줄부터는 현재 위치의 윗칸이 켜져 있는 경우에만 스위치 누름
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 이차원 boolean 배열
 * 2. 맨 윗줄 누를 전구의 경우 뽑는 메서드
 * 3. 시뮬레이션
 *  3-1. 맨 윗줄 전구를 뽑은 모양대로 누름
 *  3-2. 둘째 줄에서 시작, 자신 윗줄 전구가 켜져있다면 스위치 누름
 *  3-3. 마지막 줄까지 끝났으면 켜져 있는 전구가 있는지 탐색
 *      => 윗줄에 전구가 켜져있다면 스위치를 눌렀으므로 마지막 줄에 켜져 있는 전구가 있는지만 체크하면 됨
 *  3-4. 없다면 최소 횟수와 현재 횟수 비교해서 갱신
 *
 */
public class BOJ_14939 {
    static int minCnt = Integer.MAX_VALUE;
    static boolean[] isSwitched = new boolean[10];
    static boolean[][] originalBulb = new boolean[10][10];
    static boolean[][] tempBulb = new boolean[10][10];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            String input = br.readLine();
            for (int j = 0; j < 10; j++) {
                originalBulb[i][j] = input.charAt(j) == 'O';
            }
        }

        generateTopBulbPowerSet(0);
        System.out.println(minCnt);
    }

    private static void generateTopBulbPowerSet(int depth) {
        if (depth == 10) {
            minCnt = Math.min(minCnt, simulation());
            return;
        }

        isSwitched[depth] = true;
        generateTopBulbPowerSet(depth + 1);
        isSwitched[depth] = false;
        generateTopBulbPowerSet(depth + 1);
    }

    private static int simulation() {
        for (int i = 0; i < 10; i++) {
            System.arraycopy(originalBulb[i], 0, tempBulb[i], 0, 10);
        }

        int cnt = 0;
        for (int i = 0; i < 10; i++) {
            if (isSwitched[i]) {
                turnSwitch(0, i);
                cnt++;
            }
        }

        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tempBulb[i - 1][j]) {
                    turnSwitch(i, j);
                    cnt++;
                }
            }
        }

        return allTurnedOff() ? cnt : Integer.MAX_VALUE;
    }

    private static boolean allTurnedOff() {
        for (int i = 0; i < 10; i++) if (tempBulb[9][i]) return false;
        return true;
    }

    private static void turnSwitch(int x, int y) {
        tempBulb[x][y] = !tempBulb[x][y];
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (!isInRange(nextX, nextY)) continue;
            tempBulb[nextX][nextY] = !tempBulb[nextX][nextY];
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }
}
