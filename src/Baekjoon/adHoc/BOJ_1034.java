package Baekjoon.adHoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:24
 * 풀이 완료 : 10:45
 * 풀이 시간 : 21분
 *
 * 문제 해석
 * 램프가 들어있는 N * M 크기의 상자
 * 각 열의 아래에는 스위치가 있음
 * 스위치를 누르면 해당 열의 모든 전구의 상태가 반전됨
 * 스위치를 K번 눌러서 켜져 있는 행을 최대로 해야 함
 *
 * 구해야 하는 것
 * 스위치를 K번 눌러서 켜져 있는 행을 최대로 해야 함
 *
 * 문제 입력
 * 첫째 줄 : N, M
 * 둘째 줄 ~ N개 줄 : 램프의 상태, 1 = 켜짐, 0 = 꺼짐
 * 마지막 줄 : K
 *
 * 제한 요소
 * 1 <= N, M <= 50
 * 0 <= K <= 1000
 *
 * 생각나는 풀이
 * K가 아무리 커도 같은 자리를 2번 누르는 건 안 누르는 것과 같음
 * K %= M
 * 부분집합은 너무 큰데
 *
 * K(원래 크기)보다 꺼져 있는 전구 수가 많으면 고려할 필요 없음
 * K가 홀수번 = 홀수개 꺼져 있는 애들만 가능
 * K가 짝수번 = 짝수개 꺼져 있는 애들만 가능
 * 50비트 비트마스킹해서 0이면 1, 1이면 0 표시해놓고
 * 1 개수 세서 K % 2와 같은 나머지인 애들 중에 가장 많이 겹친 애들이 답임
 *
 * 구현해야 하는 기능
 * 1. 각 행의 꺼진 전구 수를 저장해놓는 배열
 * 2. 답으로 가능한 행들을 맵에 넣음
 *      key : 상태, value : 같은 상태인 애들의 수
 * 3. 맵에 저장된 애들 중 value가 가장 큰 값이 답
 */
public class BOJ_1034 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] turnedOffBulbs = new int[N];
        String[] status = new String[N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            status[i] = input;
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == '0') {
                    turnedOffBulbs[i]++;
                }
            }
        }

        int K = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (turnedOffBulbs[i] > K || K % 2 != turnedOffBulbs[i] % 2) continue;
            map.put(status[i], map.getOrDefault(status[i], 0) + 1);
        }


        int ans = 0;
        for (String s : map.keySet()) {
            ans = Math.max(map.get(s), ans);
        }

        System.out.println(ans);
    }
}
