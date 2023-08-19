package Baekjoon.graph.bfsNdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 풀이 시작 : 8:35
 * 풀이 완료 : 9:01
 * 풀이 시간 : 26분
 *
 * 문제 해석
 * 세로 2줄, 가로 N칸인 표가 있다
 * 첫 줄의 각 칸 : 1 ~ N까지 차례대로 들어 있음
 * 둘째 줄 각 칸 : 1 ~ N 사이의 정수가 들어 있음, 중복 가능
 * 첫째 줄의 칸 중 몇 개를 뽑아 첫째 줄의 숫자와 둘째 줄의 숫자 목록이 같게 만들어야 함
 * 최대로 가능한 수와 그 수의 목록
 *
 * 구해야 하는 것
 * 조건을 만족시키면서 최대로 가능한 갯수와 그 수의 목록, 오름차순으로
 *
 * 문제 입력
 * 첫째 줄 : N
 * 둘째 줄 ~ N개 줄 : 표의 둘째 줄에 들어가는 수, 순서대로
 *
 * 제한 요소
 * 1 <= N <= 100
 *
 * 생각나는 풀이
 * 부분집합 => 2^100이라 안될듯
 * dfs => 첫 번째 칸 기준으로 dfs 수행
 * 밑에 칸이 다음 이동할 칸
 * 사이클 발생한다면 이번 dfs 끝
 * 배열 2개를 dfs 시작 시마다 만들어서 위쪽과 아래쪽 방문 여부 체크
 * 사이클 발생했다면 배열 처음부터 돌면서 다른 경우가 있는지 확인 => 없다면 한번 더 돌면서 해당하는 애들 true로 변경
 * 마지막까지 다 돌았다면 해당하는 애들 개수 세고 출력
 *
 * 만약 사이클을 이루지 못한다면?
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 2차원 배열
 * 2. 각 dfs때 위아래 목록이 같은지 체크할 boolean형 2차원 배열
 * 3. 목록 같은 사이클이 발생했을 때 정답 리스트에 저장할 애들을 판별할 boolean형 1차원 배열
 *
 */
public class BOJ_2668 {
    static int N;
    static int[][] table;
    static boolean[][] visited;
    static boolean[] isAnswer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        table = new int[2][N + 1];
        visited = new boolean[2][N + 1];
        isAnswer = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            table[0][i] = i;
            table[1][i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited[0], false);
            Arrays.fill(visited[1], false);
            dfs(i);
            if (checkIsAnswer()) {
                for (int j = 1; j <= N; j++) {
                    if (visited[0][j]) isAnswer[j] = true;
                }
            }
        }

        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (isAnswer[i]) {
                cnt++;
                list.add(i);
            }
        }

        System.out.println(cnt);
        for (int value : list) {
            System.out.println(value);
        }
    }

    private static boolean checkIsAnswer() {
        for (int i = 1; i <= N; i++) {
            if (visited[0][i] ^ visited[1][i]) return false;
        }
        return true;
    }

    private static void dfs(int idx) {
        if (visited[0][idx]) return;
        visited[0][idx] = true;
        visited[1][table[1][idx]] = true;
        dfs(table[1][idx]);
    }
}
