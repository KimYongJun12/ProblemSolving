package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1450 {
    static int n, c;
    static int[] weight;
    static ArrayList<Integer> left = new ArrayList<>();
    static ArrayList<Integer> right = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        weight = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) weight[i] = Integer.parseInt(st.nextToken());
        int half = n / 2;

        addSum(left, 0, half, 0);
        addSum(right, half, n, 0);

        Collections.sort(right);

        int ans = 0;
        for (int i = 0; i < left.size(); i++) {
            int start = 0;
            int end = right.size() - 1;
            int lValue = left.get(i);
            int idx = 0;

            while (start <= end) {
                int mid = (start + end) / 2;
                if (right.get(mid) + lValue > c) {
                    end = mid - 1;
                } else {
                    idx = mid;
                    start = mid + 1;
                }
            }

            ans += idx + 1;
        }

        System.out.println(ans);
    }

    private static void addSum(ArrayList<Integer> list, int idx, int end, int sum) {
        if (sum > c) return;
        if (idx == end) {
            list.add(sum);
            return;
        }

        addSum(list, idx + 1, end, sum);
        addSum(list, idx + 1, end, sum + weight[idx]);
    }

}