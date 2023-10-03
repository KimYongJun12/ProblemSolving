package Baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 풀이 시작 : 9:40
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 돌 게임
 * 탁자 위에 돌 N개가 있음
 * 턴을 번갈아가며 돌을 가져감, 돌은 1 or 3 or 4개 가져갈 수 있음
 * 마지막 돌을 가져가는 사람이 게임 이김
 * 상근이와 창영이가 완벽하게 게임했을 때 이기는 사람을 구해야 함
 *
 * 구해야 하는 것
 * 상근이와 창영이가 완벽하게 게임했을 때 이기는 사람을 구해야 함
 *
 * 문제 입력
 * 첫째 줄 : 돌의 수 N
 *
 * 제한 요소
 * 1 <= N <= 1_000_000_000_000
 *
 * 생각나는 풀이
 * 10^12이므로 규칙에 따라 결정될 것
 * 규칙을 찾아야 함
 * 제일 유력한건 나머지 연산
 *
 * 1개
 *  - 상근 1, 승
 * 2개
 *  - 상근 1
 *  - 창영 1, 승
 * 3개
 *  - 상근 3, 승
 * 4개
 *  - 상근 4, 승
 * 5개
 *  - 상근 3
 *  - 창영 1
 *  - 상근 1, 승
 * 6개
 *  - 상근 4
 *  - 창영 1
 *  - 상근 1, 승
 * 7개 (나머지 0)
 *  - 상근 1
 *  - 창영 4
 *  - 상근 1
 *  - 창영 1, 승
 * 8개
 *  - 상근 1
 *  - 7개일 때 순서 반대니까 상근 승
 * => 7로 나눈 나머지로 승패가 정해짐
 *
 * 상근 승 : 1, 3, 4, 5, 6
 * 창영 승 : 0, 2
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_9660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mod = (int) (Long.parseLong(br.readLine()) % 7L);

        switch (mod) {
            case 0:
            case 2:
                System.out.println("CY");
                break;
            default:
                System.out.println("SK");
        }
    }
}
