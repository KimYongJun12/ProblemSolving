package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:15
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 크기가 3*3인 배열 A
 * 배열 인덱스는 1부터 시작
 * 1초 지날 때마다 배열 연산 적용
 * 연산의 종류
 * - R 연산 : 배열의 모든 행에 대해 정렬 수행, 행의 개수 >= 열의 개수인 경우에 적용
 * - C 연산 : 배열의 모든 열에 대해 정렬 수행, 행의 개수 < 열의 개수인 경우에 적용
 *
 * 한 행 또는 열에 있는 수를 정렬하려면 각각의 수가 몇 번 나왔는지 알아야 함.
 * 그 다음, 수의 등장 횟수 오름차순,
 * 같다면 수 오름차순으로 정렬해야 함
 * 정렬된 결과를 배열에 넣을 때에는 수와 등장 횟수를 모두 넣으며, 순서는 수가 먼저임
 * ex) [3, 1, 1] => [3, 1, 1, 2] (3이 1번, 1이 2번)
 *
 * R연산 후에는 가장 큰 행 기준으로 모든 행의 크기가 변함
 * C연산 후에는 가장 큰 열 기준으로 모든 열의 크기가 변함
 *
 * 행이나 열 크기가 커진 곳에는 0이 채워짐. 0은 정렬 시 반영 안함
 * 행이나 열 크기가 100 넘어가는 경우에는 처음 100개 제외한 나머지는 버림
 *
 * 구해야 하는 것
 * A에 들어 있는 수와 r, c, k가 주어졌을 때 A[r][c]에 들어 있는 값이 k가 되기 위한 최소 시간
 * 100초 지나도 A[r][c]가 k가 안되면 -1 출력
 *
 * 문제 입력
 * 첫째 줄 : r, c, k
 * 둘째 줄 ~ 3개 줄 : 배열 A의 초기값
 *
 * 제한 요소
 * 1 <= r, c, k <= 100
 *
 * 생각나는 풀이
 *
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_17140 {
    static int R, C, K, rLength = 3, cLength = 3;
    static int[][] arr = new int[100][100];
    static int[] countOfNum = new int[101];
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    static class Pair implements Comparable<Pair> {
        int value, count;

        public Pair(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.count == o.count) {
                return this.value - o.value;
            }
            return this.count - o.count;
        }

        @Override
        public String toString() {
            return "[count : " + this.count +  " , value : " + this.value + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = -1;
        for (int i = 0; i <= 100; i++) {
            if (arr[R][C] == K) {
                min = i;
                break;
            }
            if (rLength >= cLength) {
                rSort();
            } else {
                cSort();
            }
        }

        System.out.println(min);
    }

    private static void rSort() {
        int maxLength = 0;
        for (int i = 0; i < rLength; i++) {
            pq.clear();
            int idx = 0;
            Arrays.fill(countOfNum, 0);
            while (idx < 100) {
                countOfNum[arr[i][idx++]]++;
            }

            for (int j = 1; j <= 100; j++) {
                if (countOfNum[j] != 0) {
                    pq.offer(new Pair(j, countOfNum[j]));
                }
            }

            int nowIdx = 0;
            while (!pq.isEmpty() && nowIdx < 100) {
                Pair now = pq.poll();
                arr[i][nowIdx++] = now.value;
                arr[i][nowIdx++] = now.count;
            }

            maxLength = Math.max(maxLength, nowIdx);

            for (; nowIdx < 100; nowIdx++) {
                arr[i][nowIdx] = 0;
            }
        }

        cLength = maxLength;
    }

    private static void cSort() {
        int maxLength = 0;
        for (int i = 0; i < cLength; i++) {
            pq.clear();
            int idx = 0;
            Arrays.fill(countOfNum, 0);
            while (idx < 100) {
                countOfNum[arr[idx++][i]]++;
            }

            for (int j = 1; j <= 100; j++) {
                if (countOfNum[j] != 0) {
                    pq.offer(new Pair(j, countOfNum[j]));
                }
            }

            int nowIdx = 0;

            while (!pq.isEmpty() && nowIdx < 100) {
                Pair now = pq.poll();
                arr[nowIdx++][i] = now.value;
                arr[nowIdx++][i] = now.count;
            }

            maxLength = Math.max(maxLength, nowIdx);

            for (; nowIdx < 100; nowIdx++) {
                arr[nowIdx][i] = 0;
            }
        }

        rLength = maxLength;
    }
}
