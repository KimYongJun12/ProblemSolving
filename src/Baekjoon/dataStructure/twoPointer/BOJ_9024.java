package Baekjoon.dataStructure.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이 시작 :
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
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
public class BOJ_9024 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int n, k, min, cnt, left, right;

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            left = 0;
            right = n - 1;
            cnt = 0;
            min = Integer.MAX_VALUE;

            while (left < right) {
                int sum = arr[left] + arr[right];
                int diff = Math.abs(k - sum);
                if (diff < min) {
                    cnt = 1;
                    min = diff;
                } else if (diff == min) {
                    cnt++;
                }

                if (sum > k) {
                    right--;
                } else left++;
            }

            sb.append(cnt).append('\n');
        }

        System.out.println(sb);
    }
}
