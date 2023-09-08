package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:36
 * 풀이 완료 : 11:20
 * 풀이 시간 :
 *
 * 문제 해석
 * 길이 N인 컨베이어 벨트가 있음
 * 벨트는 길이 1 간격으로 2N개 칸
 * 초기 1번 칸의 위치가 올리는 칸
 * 초기 N번 칸의 위치가 내리는 칸
 *
 * 로봇은 올리는 칸에서만 올릴 수 있고 내리는 칸에서 반드시 내림
 * 컨베이어 벨트의 내구도 감소 조건
 * 1. 올리는 칸에서 로봇을 올릴 때
 * 2. 로봇이 앞으로 이동할 때 목적지 칸
 *
 * 로봇 옮기는 순서
 * 1. 벨트가 각 칸 위의 로봇과 함께 회전
 * 2. 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동, 이동 못하면 가만히 있음
 *  - 이동 가능한 조건 : 앞 칸에 로봇이 없고 내구도가 1 이상이어야 함
 * 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 로봇을 올림
 * 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정 종료, 아니면 1번으로 돌아감
 *
 * 구해야 하는 것
 * 옮기는 과정을 계속해서 수행했을 때 가능한 최대 단계
 *
 * 문제 입력
 * 첫째 줄 : N, K
 * 둘째 줄 : 각 컨베이어 벨트 초기 내구도
 *
 * 제한 요소
 * 2 <= N <= 100
 * 1 <= K <= 2N
 * 1 <= A[i] <= 1000
 *
 * 생각나는 풀이
 * 구현
 * 1. 컨베이어 클래스
 *  - 멤버
 *      - 내구도, 로봇 여부
 * 2. 회전 구현
 * 3. 이동 구현
 *  - 이동 조건 체크 메서드
 * 4. 첫 칸에 로봇 올림
 * 5. 내구도 0인 칸 체크
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_20055 {
    static int N, K;
    static conveyor[] belts;
    static class conveyor {
        int durability;
        boolean isRobot;

        public conveyor(int durability) {
            this.durability = durability;
            isRobot = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belts = new conveyor[2 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belts[i] = new conveyor(Integer.parseInt(st.nextToken()));
        }

        int turn = 0;
        while (K > 0) {
            turn++;
            simulation();
        }

        System.out.println(turn);
    }

    private static void simulation() {
        rotate();
        moveRobot();
        putRobot();
    }

    private static void rotate() {
        conveyor last = belts[belts.length - 1];
        for (int i = belts.length - 1; i > 0; i--) {
            belts[i] = belts[i - 1];
        }
        belts[0] = last;
        belts[N - 1].isRobot = false;
    }

    private static void moveRobot() {
        for (int i = N - 2; i >= 0; i--) {
            conveyor now = belts[i];
            conveyor next = belts[i + 1];
            if (next.isRobot || !now.isRobot || next.durability == 0) continue;
            if (--next.durability == 0) K--;
            if (i != N - 2) next.isRobot = true;
            now.isRobot = false;
        }
    }

    private static void putRobot() {
        conveyor first = belts[0];
        if (first.durability > 0) {
            if (--first.durability == 0) K--;
            first.isRobot = true;
        }
    }
}
