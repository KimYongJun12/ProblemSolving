package Baekjoon.dataStructure.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 풀이 시작 : 5:16
 * 풀이 완료 : 5:24
 * 풀이 시간 :
 *
 * 문제 해석
 * N개의 수가 주어졌을 때 오름차순 정렬해야 함
 *
 * 구해야 하는 것
 * N개의 수가 주어졌을 때 오름차순 정렬해야 함
 *
 * 문제 입력
 * 첫째 줄 : 수의 개수 N
 * 둘째 줄 ~ N개 줄 : 수의 값
 *
 * 제한 요소
 * 1 <= N <= 10_000_000
 * 1 <= value <= 10000
 *
 * 생각나는 풀이
 * O(nlogn)으로 정렬해도 통과되긴 함
 * 하지만 수의 범위가 1 ~ 10000이므로
 * O(N + K)인 계수 정렬(카운팅 정렬)이 효율적
 *
 * 구현해야 하는 기능
 * 1. 수가 몇 번 나왔는지 카운트하는 배열
 * 2. 카운트 배열 기반 누적합 배열
 * 3. 정렬한 값이 저장될 배열
 *
 * 정렬 순서
 * 1. 입력에 따라 카운트 배열의 인덱스에 해당하는 값이 몇 번 나왔는지 체크
 * 2. 1부터 오름차순으로 누적합을 구함. prefixSum[i] = 정렬했을 때 i가 저장되는 마지막 위치
 * 3. count배열의 뒤쪽부터 순회하며 count[i] > 0인 i에 대해 sorted 배열의 (prefixSum[i] - 1)번째 인덱스에 i를 저장
 *  - 뒤쪽부터 순회하는 이유는 stable 유지를 하기 위함
 * 4. 전부 다 순회했다면 배열이 정렬되어 있음
 */
public class BOJ_10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] count = new int[10001];
        int N = Integer.parseInt(br.readLine());

        int max = 0;
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(br.readLine());
            count[now]++;
            max = Math.max(max, now);
        }

        int[] prefixSum = new int[max + 1];
        for (int i = 1; i <= max; i++) {
            prefixSum[i] = prefixSum[i - 1] + count[i];
        }

        int[] sortedArr = new int[N];
        for (int i = max; i >= 1; i--) {
            while (count[i]-- > 0) {
                sortedArr[--prefixSum[i]] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(sortedArr[i]).append('\n');
        }

        System.out.println(sb);
    }
}
