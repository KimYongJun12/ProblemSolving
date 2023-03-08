package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_4195 {
    static int[] parent;
    static int[] groupSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int idx = 0;
            HashMap<String, Integer> map = new HashMap<>();
            parent = new int[n * 2];
            groupSize = new int[n * 2];
            for (int i = 0; i <n * 2; i++) {
                parent[i] = i;
                groupSize[i] = 1;
            }

            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                String first = st.nextToken();
                String second = st.nextToken();
                if (!map.containsKey(first)) map.put(first, idx++);
                if (!map.containsKey(second)) map.put(second, idx++);

                sb.append(union(map.get(first), map.get(second))).append('\n');
            }

        }

        System.out.println(sb);
    }

    private static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    private static int union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }

        if (x != y) {
            parent[y] = x;
            groupSize[x] += groupSize[y];
        }
        return groupSize[x];
    }


}
