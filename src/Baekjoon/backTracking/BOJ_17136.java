package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 4:40
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 정사각형 모양의 다섯 종류의 색종이
 * 1 * 1 ~ 5 * 5
 * 각 색종이는 5개씩 있음
 * 색종이를 10 * 10 종이 위에 붙여야 함
 * 1이 적힌 칸은 모두 색종이로 덮여야 함
 * 색종이는 경계 밖으로 나가면 안됨
 * 색종이는 겹치면 안됨
 * 색종이는 칸의 경계와 일치하게 붙여야 함 (0이 적힌 칸에는 색종이가 있으면 안됨)
 *
 * 구해야 하는 것
 * 1이 적힌 칸을 붙이는데 필요한 색종이의 최소 개수
 * 불가능하면 -1 출력
 *
 * 문제 입력
 * 첫째 줄 ~ 10번째 줄 : 종이의 각 칸에 적힌 수
 * 0 : 빈 칸 1 : 덮어야 하는 칸
 *
 * 제한 요소
 * 각 종류마다 색종이는 5개
 *
 * 생각나는 풀이
 * 백트래킹
 * 왼쪽 위부터 탐색
 * 1을 만나면 해당 칸에서부터 시작하는 가능한 모든 종이를 붙여서 재귀로 다음 차례에 넘김
 * 만약 1이 남았는데 가능한 종이가 없다면 리턴
 * 오른쪽 아래까지 도달하면 그 때 사용한 종이의 수 중 최소 체크
 *
 * 구현해야 하는 기능
 * 1. 배경 종이의 상태 저장할 2차원 배열
 * 2. 사용한 색종이 수 저장할 1차원 배열
 * 3. 재귀
 */
public class BOJ_17136 {
    static int minCnt = Integer.MAX_VALUE;
    static int[] colorPaper = new int[6];
    static boolean[][] map = new boolean[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = st.nextToken().charAt(0) == '1';
            }
        }


        backTracking(0, 0, 0);
        System.out.println(minCnt == Integer.MAX_VALUE ? -1 : minCnt);
    }

    private static void backTracking(int x, int y, int cnt) {
        if (x == 9 && y == 10) {
            minCnt = Math.min(minCnt, cnt);
            return;
        }

        if (cnt >= minCnt) return;

        if (y == 10) {
            backTracking(x + 1, 0, cnt);
            return;
        }

        if (map[x][y]) {
            for (int size = 5; size >= 1; size--) {
                if (colorPaper[size] < 5 && canGlue(x, y, size)) {
                    glue(x, y, size);
                    colorPaper[size]++;
                    backTracking(x, y + size, cnt + 1);
                    glue(x, y, size);
                    colorPaper[size]--;
                }
            }
        } else backTracking(x, y + 1, cnt);
    }

    private static void glue(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = !map[i][j];
            }
        }
    }

    private static boolean canGlue(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) return false;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (!map[i][j]) return false;
            }
        }
        return true;
    }
}
