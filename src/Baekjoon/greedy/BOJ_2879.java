package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2879 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] diff = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            diff[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            diff[i] -= Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        boolean isAllCorrect = false;

        while (!isAllCorrect) {
            isAllCorrect = true;
            int prev;

            for (int i = 0; i < N; i++) {
                if (diff[i] == 0) continue;
                prev = diff[i];
                int end = i;
                int minValue = diff[i];

                while (end < N && prev * diff[end] > 0) {
                    if (Math.abs(minValue) > Math.abs(diff[end])) minValue = diff[end];
                    end++;
                }

                for (; i < end; i++) {
                    diff[i] -= minValue;
                }

                cnt += Math.abs(minValue);
                isAllCorrect = false;
                i--;
            }
        }

        System.out.println(cnt);
    }
}
