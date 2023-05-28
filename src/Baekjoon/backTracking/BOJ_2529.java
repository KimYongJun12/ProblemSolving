package Baekjoon.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2529 {
    static int K;
    static boolean[] check = new boolean[10];
    static char[] sign;
    static int[] nums;
    static int[] min;
    static int[] max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        sign = new char[K + 1];
        nums = new int[K + 1];
        min = new int[K + 1];
        Arrays.fill(min, Integer.MAX_VALUE);
        max = new int[K + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            sign[i] = st.nextToken().charAt(0);
        }

        for (int i = 0; i <= 9; i++) {
            nums[0] = i;
            check[i] = true;
            backTracking(1);
            check[i] = false;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K + 1; i++) {
            sb.append(max[i]);
        }
        sb.append('\n');
        for (int i = 0; i < K + 1; i++) {
            sb.append(min[i]);
        }

        System.out.println(sb);
    }

    private static void backTracking(int depth) {
        if (depth == K + 1) {
            boolean isChanged = false;

            for (int i = 0; i < K + 1; i++) {
                if (min[i] > nums[i]) {
                    isChanged = true;
                    break;
                } else if (min[i] < nums[i]) {
                    break;
                }
            }

            if (isChanged) min = nums.clone();

            isChanged = false;

            for (int i = 0; i < K + 1; i++) {
                if (max[i] < nums[i]) {
                    isChanged = true;
                    break;
                } else if (max[i] > nums[i]) {
                    break;
                }
            }

            if (isChanged) max = nums.clone();
            return;
        }

        if (sign[depth] == '>') {
            for (int i = 0; i <= 9; i++) {
                if (check[i] || nums[depth - 1] < i) continue;

                nums[depth] = i;
                check[i] = true;
                backTracking(depth + 1);
                check[i] = false;
            }
        } else {
            for (int i = 0; i <= 9; i++) {
                if (check[i] || nums[depth - 1] > i) continue;

                nums[depth] = i;
                check[i] = true;
                backTracking(depth + 1);
                check[i] = false;
            }
        }
    }
}
