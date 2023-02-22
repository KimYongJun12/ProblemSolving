package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10775 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        int cnt = 0;
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) parent[i] = i;

        while (p-- > 0) {
            int planeNum = Integer.parseInt(br.readLine());
            int minNum = find(planeNum);
            if (minNum == 0) {
                break;
            }
            union(minNum - 1, minNum);
            cnt++;
        }

        System.out.println(cnt);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int idx) {
        if (parent[idx] == idx) return idx;

        return parent[idx] = find(parent[idx]);
    }
}