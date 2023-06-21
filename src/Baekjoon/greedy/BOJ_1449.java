package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1449 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] hole = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hole[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(hole);

        int start = hole[0], cnt = 1;
        for (int i = 0; i < N; i++) {
            if (hole[i] - start > L - 1) {
                start = hole[i];
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
