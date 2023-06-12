package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        LinkedList<Integer> list = new LinkedList<>();
        int[] order = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i >= 1; i--) {
            list.add(order[i], i);
        }

        StringBuilder sb = new StringBuilder();
        for (int num : list) sb.append(num).append(' ');

        System.out.println(sb);
    }
}
