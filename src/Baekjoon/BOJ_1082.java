package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1082 {

    static class Pair implements Comparable<Pair> {
        int number;
        int cost;

        public Pair(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.cost == o.cost) return this.number - o.number;
            return this.cost - o.cost;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(br.readLine());

        Pair[] cards = new Pair[n];
        int[] costs = new int[n];

        for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            cards[i] = new Pair(i, costs[i]);
        }

        Arrays.sort(cards);

        System.out.println(solve(cards, costs, n, m));


    }

    private static String solve(Pair[] cards, int[] costs, int n, int m) {
        int[] ans = new int[100];
        int cnt = 0;
        if (cards[0].number == 0) {
            if (n == 1 || cards[1].cost > m) return "0";

            m -= cards[1].cost;
            ans[cnt++] = cards[1].number;
        }

        while (m >= cards[0].cost) {
            m -= cards[0].cost;
            ans[cnt++] = cards[0].number;
        }

        for (int i = 0; i < cnt; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == 0 && j == 0) continue;
                int nowCost = m + costs[ans[i]] - costs[j];

                if (nowCost >= 0) {
                    m = nowCost;
                    ans[i] = j;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt; i++) sb.append(ans[i]);

        return sb.toString();
    }
}
