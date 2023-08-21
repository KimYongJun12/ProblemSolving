package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 9:54
 * 풀이 완료 : 10:44
 * 풀이 시간 : 50분ㄷㄷ
 *
 * 문제 해석
 * 줄자를 3번 접는다, 두 점이 포개지도록
 * 이미 포개져 있으면 접지 않는다
 * 3번 접은 후 줄자의 길이를 출력한다
 *
 * 구해야 하는 것
 * 3번 접은 후 줄자의 길이를 출력한다
 *
 * 문제 입력
 * 첫째 줄 : 줄자의 길이 L
 * 둘째 줄 ~ 넷째 줄 : 접을 위치
 *
 * 제한 요소
 * 10 <= L <= 1000
 * 모든 점의 위치는 서로 다름
 *
 * 생각나는 풀이
 * 구현, 접어야 할 위치를 기준으로 뭔가 해야함
 * 접어야 할 위치 = 두 점의 가운데
 * 길이가 짧은 곳이 길이가 긴 곳 쪽으로 접힘
 * 접히는 좌표가 flip이라고 하면
 * - 왼쪽으로 접히는 경우
 *      - 접히는 곳 왼쪽의 좌표는 변화 없음
 *      - 접히는 곳 오른쪽의 좌표는 flip - (x - flip)
 * - 오른쪽으로 접히는 경우
 *      - 접히는 곳 왼쪽의 좌표는 (flip - x)
 *      - 접히는 곳 오른쪽의 좌표는 x - flip
 * 왼쪽으로 접히면 길이는 flip
 * 오른쪽으로 접히면 길이는 N - flip
 *
 * 구현해야 하는 기능
 * 위의 풀이 그대로 구현
 */
public class BOJ_2597 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double[][] dot = new double[3][2];
        double N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            double a = Double.parseDouble(st.nextToken());
            double b = Double.parseDouble(st.nextToken());
            dot[i][0] = a;
            dot[i][1] = b;
        }

        for (int i = 0; i < 3; i++) {
            if (dot[i][0] == dot[i][1]) continue;
            double flip = (dot[i][0] + dot[i][1]) / 2.0;
            if (flip < N - flip) { // 접는 구간이 반 아래 -> 오른쪽으로 접기
                for (int j = i + 1; j < 3; j++) {
                    for (int k = 0; k < 2; k++) {
                        if (dot[j][k] < flip) { // 접는 지점 왼쪽의 경우
                            dot[j][k] = flip - dot[j][k];
                        } else { // 접는 지점 오른쪽의 경우
                            dot[j][k] -= flip;
                        }
                    }
                }
                N -= flip;
            } else { // 접는 구간이 반 이상 -> 왼쪽으로 접기
                for (int j = i + 1; j < 3; j++) {
                    for (int k = 0; k < 2; k++) {
                        if (dot[j][k] > flip) { // 접는 지점 오른쪽의 경우
                            dot[j][k] = flip - (dot[j][k] - flip);
                        }
                    }
                }
                N = flip;
            }
        }

        System.out.printf("%.1f", N);
    }
}
