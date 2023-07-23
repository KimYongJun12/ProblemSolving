package Baekjoon.meetInTheMiddle;
/**
 * 풀이 시작 : 20:13
 * 풀이 완료 : 21:15
 * 풀이 시간 : 1시간 2분
 *
 * 문제 해석
 * 두 배열 A, B에서 A의 부배열 + B의 부배열의 합 = T가 되는 부배열 쌍의 개수
 * 부배열이란?
 * 길이가 N인 배열 A에 대해 (1 <= i <= j <= N)를 만족할 때
 * A[i], A[i + 1], ... , A[j - 1], A[j] (A[i] ~ A[j]까지 연속한 배열의 값)
 *
 * 구해야 하는 것
 * A의 부배열 원소의 합 + B의 부배열 원소의 합 = T가 되는 부배열 쌍의 개수
 *
 * 제한 조건
 * -1_000_000_000 <= T <= 100_000_000
 * A의 배열 원소 개수 1 <= n <= 1000
 * B의 배열 원소 개수 1 <= m <= 1000
 * A, B의 원소 -1_000_000 <= A[i], B[i] <= 1_000_000
 *
 * 생각나는 풀이
 * 중간에서 만나기
 * A or B의 부배열 쌍의 최대 개수 = 1000 + 999 + 998 + ... + 1 = 1001 * 500 = 500500
 * 500500회 * 2 = 약 100만
 * 정렬 nlogn 2회 = 약 285만 * 2 = 570만
 * A 부배열 합 left와 B 부배열 합 right에서 가운데로 좁히며 cnt 증가 약 100만
 *
 * 구현해야 하는 기능
 * 1. 입력값에 따라 A배열, B배열 생성 및 값 할당 (할당하지 않고 바로 누적합 배열로 생성)
 * 2. 누적합 구하기
 * 3. 반복문으로 가능한 부배열 합을 list에 담기
 * 4. A리스트와 B리스트 정렬하기
 * 5. A리스트 맨 앞과 B리스트 맨 뒤의 합을 더해 T이면 cnt를 증가
 * 5-1. 증가시키는 방법은 A리스트 현재 값의 개수 * B리스트 현재 값의 개수만큼 증가
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] prefixA = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int nowValue = Integer.parseInt(st.nextToken());
            prefixA[i] = prefixA[i - 1] + nowValue;
        }

        int m = Integer.parseInt(br.readLine());
        int[] prefixB = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            int nowValue = Integer.parseInt(st.nextToken());
            prefixB[i] = prefixB[i - 1] + nowValue;
        }

        ArrayList<Integer> listA = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                listA.add(prefixA[j] - prefixA[i]);
            }
        }

        ArrayList<Integer> listB = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j <= m; j++) {
                listB.add(prefixB[j] - prefixB[i]);
            }
        }

        Collections.sort(listA);
        Collections.sort(listB);

        int left = 0, right = listB.size() - 1;
        long cnt = 0L;
        while (left < listA.size() && right >= 0) {
            int sum = listA.get(left) + listB.get(right);
            if (sum == T) {
                long aCnt = 0;
                int nowValue = listA.get(left);
                while (left < listA.size() && listA.get(left) == nowValue) {
                    aCnt++;
                    left++;
                }

                long bCnt = 0;
                nowValue = listB.get(right);
                while (right >= 0 && listB.get(right) == nowValue) {
                    bCnt++;
                    right--;
                }

                cnt += aCnt * bCnt;
            } else if (sum < T) {
                int nowValue = listA.get(left);
                while (left < listA.size() && listA.get(left) == nowValue) {
                    left++;
                }
            } else {
                int nowValue = listB.get(right);
                while (right >= 0 && listB.get(right) == nowValue) {
                    right--;
                }
            }
        }

        System.out.println((T == 0) ? cnt - 1 : cnt);
    }
}
