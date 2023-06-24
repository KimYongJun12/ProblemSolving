package Baekjoon.dataStructure.slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] belt = new int[N];
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        int[] isSelected = new int[D + 1];
        int cnt = 0;

        for (int i = 0; i < K; i++) {
            if (isSelected[belt[i]]++ == 0) cnt++;
        }

        int max = cnt;

        for (int left = 0; left < N; left++) {
            if(max <= cnt) {
                if (isSelected[C] == 0) max = cnt + 1;
                else max = cnt;
            }

            int right = (left + K) % N;

            isSelected[belt[left]]--;
            if (isSelected[belt[left]] == 0) cnt--;
            if (isSelected[belt[right]] == 0) cnt++;
            isSelected[belt[right]]++;
        }

        System.out.println(max);
    }
}
