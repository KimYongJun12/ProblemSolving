package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 6:47
 * 풀이 완료 : 7:20
 * 풀이 시간 : 33분
 *
 * 문제 해석
 * 주사위를 쌓아 옆면 4면 중 한 면의 합이 가장 크게 만들기
 * 위에 쌓는 주사위의 아랫면은 아래 주사위의 윗면과 같아야 함
 * 첫 주사위는 아무 면이나 아랫면으로 가능
 * 
 * 구해야 하는 것
 * N개의 주사위를 쌓았을 때 옆면의 최대 합
 * 
 * 문제 입력
 * 첫째 줄 : 주사위의 개수 N
 * 둘째 줄 ~ N개 줄 : 1번부터 주사위의 종류가 주어짐
 *  주사위의 각 면의 숫자는 그림의 A ~ F순으로 주어짐
 *  
 * 제한 요소
 * 1 <= N <= 10000
 * 
 * 생각나는 풀이
 * 첫째 주사위의 윗면으로 놓을 수 있는 경우 = 6
 * 나머지는 바로 아래 주사위의 윗면에 따라 정해짐
 * 주사위의 위아랫면을 뺀 4개 값 중 가장 큰 값들만 모아 더해서 6개 중 하나
 * 주사위 아랫면이 주어졌을 때 윗면은
 * up[0] = 5, up[1] = 3, up[2] = 4, up[3] = 1, up[4] = 2, up[5] = 0
 * 
 * 구현해야 하는 기능
 * 1. 주사위의 값을 저장할 2차원 배열 dice[6개의 면][i번째 주사위]
 * 2. 첫 주사위의 아랫면을 결정 (6가지 경우)
 * 3. N개의 주사위를 조건에 따라 세움
 *  - 위, 아랫면이 아닌 4개의 면 중 가장 큰 값을 sum에 더함
 * 4. N개 다 세웠으면 sum을 가장 큰 값으로 갱신
 * 
 */
public class BOJ_2116 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] otherSide = {0, 6, 4, 5, 2, 3, 1};
        int[][] dice = new int[7][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 6; j++) {
                dice[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        boolean[] isUpDown = new boolean[7];
        for (int i = 1; i <= 6; i++) {
            int sum = 0;
            int prevUp = i;
            for (int j = 0; j < N; j++) {
                int idx = 0;
                for (int k = 1; k <= 6; k++) {
                    if (dice[k][j] == prevUp) {
                        idx = k;
                        break;
                    }
                }

                isUpDown[prevUp] = true;
                prevUp = dice[otherSide[idx]][j];
                isUpDown[prevUp] = true;
                for (int k = 6; k >= 0; k--) {
                    if (!isUpDown[k]) {
                        sum += k;
                        break;
                    }
                }

                Arrays.fill(isUpDown, false);
            }
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
