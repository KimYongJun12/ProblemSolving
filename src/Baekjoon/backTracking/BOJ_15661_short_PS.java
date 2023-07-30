package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15661_short_PS {
    static int N, min = Integer.MAX_VALUE;
    static int[] rowSum;
    static int[] columnSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int totalSum = 0;
        rowSum = new int[N];
        columnSum = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int nowValue = Integer.parseInt(st.nextToken());
                rowSum[i] += nowValue; // i번 사람이 0 ~ N - 1번 사람과 같은 팀일 때 능력치 합
                columnSum[j] += nowValue; // 0 ~ N - 1번 사람이 j번 사람과 같은 팀일 때 능력치 합
                totalSum += nowValue; // 모든 사람이 같은 팀일 때 능력치 합
            }
        }

        backTracking(0, 0, totalSum); // 초기값으로 전부 스타트 팀에 있다고 가정하면 스타트 팀의 점수 = totalSum, 링크 팀의 점수 = 0
        System.out.println(min);
    }

    /**
     * 백트래킹 탐색의 초기 설정
     * 모든 사람이 스타트 팀에 속했다고 가정하면
     * 스타트 팀 점수 = totalSum, 링크 팀 점수 = 0
     * idx = 현재 팀원의 번호
     * idx번에 대해 탐색할 때 선택할 수 있는 경우는 2가지
     * 1. idx번을 링크 팀으로 보낸다.
     *  => 현재 스타트 팀의 점수 totalSum에서 S[idx][0 ~ N - 1]만큼의 점수와 S[0 ~ N - 1][idx]만큼의 점수를 빼야 한다.
     *  => S[idx][0 ~ N - 1] = rowSum[idx]이고, S[0 ~ N - 1][idx] = columnSum[idx]이므로
     *  => 스타트 팀 - 링크 팀 = totalSum - rowSum[idx] - columnSum[idx]이 된다.
     * 2. idx번을 스타트 팀에 남긴다.
     *  => 점수의 변동이 없다.
     *
     *  만약 모든 사람이 링크 팀에 속했다고 가정했어도 똑같은 결과이므로 팀원 수가 과반이 되면 중복된 결과가 나온다.
     *  => 사람을 모두 체크했거나 반대 팀의 인원이 과반이 되면 return하여 불필요한 탐색을 줄인다.
     * @param idx 사람 번호
     * @param numOfPeople 링크 팀에 있는 사람 수
     * @param totalSum 현재 (스타트 팀의 점수) - (링크 팀의 점수)
     */
    private static void backTracking(int idx, int numOfPeople, int totalSum) {
        min = Math.min(min, Math.abs(totalSum));
        if (idx == N || numOfPeople == (N / 2) + 1) return;

        backTracking(idx + 1, numOfPeople + 1, totalSum - rowSum[idx] - columnSum[idx]);
        backTracking(idx + 1, numOfPeople, totalSum);
    }
}
