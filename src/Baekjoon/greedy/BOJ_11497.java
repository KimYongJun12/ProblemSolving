package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11497 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] wood = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                wood[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(wood);
            int[] greedyWood = new int[N];
            int cnt = 0;
            int idx = 0;
            while (cnt < N) {
                if (cnt % 2 == 1) {
                    idx++;
                }

                greedyWood[(N + idx) % N] = wood[cnt++];
                idx *= -1;
            }

            int max = Math.abs(greedyWood[N - 1] - greedyWood[0]);
            for (int i = 0; i < N - 1; i++) {
                max = Math.max(max, Math.abs(greedyWood[i] - greedyWood[i + 1]));
            }

            sb.append(max).append('\n');
        }

        System.out.println(sb);
    }
}