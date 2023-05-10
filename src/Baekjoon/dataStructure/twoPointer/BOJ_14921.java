package Baekjoon.dataStructure.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] solution = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;

        int ans = Integer.MAX_VALUE;
        while (left < right && ans != 0) {
            int value = solution[left] + solution[right];

            if (Math.abs(ans) > Math.abs(value)) {
                ans = value;
            }

            if (value > 0) right--;
            else left++;
        }

        System.out.println(ans);
    }
}
