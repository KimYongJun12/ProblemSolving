package Baekjoon.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 9:25
 * 풀이 완료 : 9:53
 * 풀이 시간 : 28분
 *
 * 문제 해석
 * 십자 카드의 네 모서리에 1 ~ 9 숫자가 하나 쓰여 있다, 각 숫자는 중복 가능
 * 모든 가능한 십자 카드가 주어질 때 각각의 카드는 '시계수' 라는 번호를 가짐
 * 시계수
 *  - 카드의 숫자들을 시계 방향으로 읽어 만들어지는 네 자리 수 중 가장 작은 수
 * 입력으로 주어지는 카드의 시계수를 계산해 시계수가 모든 시계수 중 몇 번째로 작은 시계수인지 알아내야 함
 *
 * 구해야 하는 것
 * 4개의 수가 들어올 때 시계수를 구하고 그 시계수가 몇 번째로 작은 시계수인지 출력해야 함
 *
 * 문제 입력
 * 첫째 줄 : 시계 방향 순서대로 숫자 4개가 들어옴
 *
 * 제한 요소
 * 1 <= N <= 9
 *
 * 생각나는 풀이
 * 중복조합의 개수를 모두 구한 다음 list에 넣고
 * 입력받은 네 수의 시계수를 구함
 * list의 시계수와 같아질 때까지 앞에서부터 탐색
 * => 중복순열로 풀면 안됨, 1111 ~ 9999까지 전부 브루트포스해서 해당 수에서 시계수를 true로 해줘야 함
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 시계수
 * 2. 가능한 모든 시계수의 경우 중복조합으로 뽑기
 *  -> 중복조합이 아니라 브루트포스로 해당 순서일 때 시계수 만들기
 * 3. 시계수의 경우를 비교하며 몇 번째인지 찾기
 *
 */
public class BOJ_2659 {
    static int[] selected = new int[4];
    static boolean[] clockNum = new boolean[10000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        generateClockNum(0);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int minClockNum = getClockNum(new int[] {
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        });

        int cnt = 1;
        for (int i = 1111; i <= 9999; i++) {
            if (i == minClockNum) break;
            if (clockNum[i]) cnt++;
        }

        System.out.println(cnt);
    }

    private static void generateClockNum(int depth) {
        if (depth == 4) {
            clockNum[getClockNum(selected)] = true;
            return;
        }

        for (int i = 1; i <= 9; i++) {
            selected[depth] = i;
            generateClockNum(depth + 1);
        }
    }

    private static int getClockNum(int[] selected) {
        int min = 9999;
        for (int i = 0; i < 4; i++) {
            min = Math.min(min, selected[i] * 1000 + selected[(i + 1) % 4] * 100 + selected[(i + 2) % 4] * 10 + selected[(i + 3) % 4]);
        }

        return min;
    }
}
