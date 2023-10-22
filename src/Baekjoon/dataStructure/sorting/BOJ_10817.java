package Baekjoon.dataStructure.sorting;

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
public class BOJ_10817 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[3];
        arr[0] = Integer.parseInt(st.nextToken());
        arr[1] = Integer.parseInt(st.nextToken());
        arr[2] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        System.out.println(arr[1]);
    }
}
