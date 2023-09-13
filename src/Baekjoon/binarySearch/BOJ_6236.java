package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 11:46
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * N일동안 용돈 사용
 * 정확히 M번 통장에서 돈 인출
 * K원을 인출하며 수중의 돈이 P[i] 이상이라면 그 돈을 사용하고
 * 그렇지 않으면 다 넣고 K만큼 리필함
 * M번 인출할 때 K의 최솟값 구해야 함
 *
 * 구해야 하는 것
 *
 * 문제 입력
 *
 * 제한 요소
 *
 * 생각나는 풀이
 *
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_6236 {
    static int N, M;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cost = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, cost[i]);
        }

        int left = max;
        int right = 1_000_000_001;
        int K = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;

            int cnt = binarySearch(mid);
            if (cnt <= M) {
                right = mid - 1;
                K = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(K);
    }

    private static int binarySearch(int value) {
        int cnt = 1;
        int sum = value;
        for (int i = 0; i < N; i++) {
            sum -= cost[i];

            if (sum < 0) {
                cnt++;
                sum = value - cost[i];
            }
        }

        return cnt;
    }
}
