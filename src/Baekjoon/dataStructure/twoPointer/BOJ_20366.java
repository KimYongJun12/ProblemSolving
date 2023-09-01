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
public class BOJ_20366 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);
        int ans = Integer.MAX_VALUE;

        fo : for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int nowSnowMan = num[i] + num[j];
                int left = 0, right = N - 1;
                while (left < right) {
                    if (left == i || left == j) {
                        left++;
                        continue;
                    }
                    if (right == i || right == j) {
                        right--;
                        continue;
                    }
                    int anotherSnowMan = num[left] + num[right];
                    if (nowSnowMan > anotherSnowMan) {
                        left++;
                    } else if (nowSnowMan < anotherSnowMan) {
                        right--;
                    } else {
                        ans = 0;
                        break fo;
                    }
                    ans = Math.min(ans, Math.abs(nowSnowMan - anotherSnowMan));
                }
            }
        }
        System.out.println(ans);
    }
}
