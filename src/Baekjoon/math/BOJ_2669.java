package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 9:02
 * 풀이 완료 : 9:07
 * 풀이 시간 : 5분
 *
 * 문제 해석
 * 2차원 좌표평면에 네 개의 직사각형이 있음
 * 직사각형은 모두 축과 평행
 * 직사각형이 차지하는 면적 구하기
 *
 * 구해야 하는 것
 * 직사각형이 차지하는 면적 구하기
 *
 * 문제 입력
 * 첫째 줄 ~ 넷째 줄 : 왼쪽 아래 꼭짓점의 x좌표, 왼쪽 아래 꼭짓점의 y좌표, 오른쪽 위 꼭짓점의 x좌표, 오른쪽 위 꼭짓점의 y좌표
 *
 * 제한 요소
 * 1 <= x, y <= 100
 *
 * 생각나는 풀이
 * 왼쪽위부터 오른쪽아래까지 true해놓고 완탐해서 true 개수 출력
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 사각형 위치 표시할 boolean 2차원 배열
 *
 */
public class BOJ_2669 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = 0;
        boolean[][] paper = new boolean[101][101];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = x1; j < x2; j++) {
                for (int k = y1; k < y2; k++) {
                    if (paper[j][k]) continue;
                    cnt++;
                    paper[j][k] = true;
                }
            }
        }

        System.out.println(cnt);
    }
}
