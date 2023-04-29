package Baekjoon.dataStructure.slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3078 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] classmate = new int[N];
        long[] nameLength = new long[21];

        for (int i = 0; i < N; i++) {
            classmate[i] = br.readLine().length();
        }

        int left = 0, right = 0;
        long ans = 0;

        while (left < N) {
            while (right - left <= K && right < N) {
                nameLength[classmate[right++]]++;
            }

            int nowLength = classmate[left];
            nameLength[nowLength]--;
            ans += nameLength[nowLength];
            left++;
        }

        System.out.println(ans);
    }
}
