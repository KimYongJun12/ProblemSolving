package Baekjoon.greedy;

import java.util.*;
import java.io.*;

public class BOJ_1135 {
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 1; i < n; i++) {
            int boss = Integer.parseInt(st.nextToken());
            tree[boss].add(i);
        }

        System.out.println(dfs(0));
    }

    private static int dfs(int now) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int next : tree[now]) {
            list.add(dfs(next));
        }

        Collections.sort(list, Collections.reverseOrder());

        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            result = Math.max(result, list.get(i) + i + 1);
        }

        return result;
    }
}