package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2457 {
    static class Flower implements Comparable<Flower> {
        int sDay, eDay;

        public Flower(int sDay, int eDay) {
            this.sDay = sDay;
            this.eDay = eDay;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.sDay == o.sDay) return o.eDay - this.eDay;
            return this.sDay - o.sDay;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Flower[] flowers = new Flower[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sMonth = Integer.parseInt(st.nextToken());
            int sDay = sMonth * 100 + Integer.parseInt(st.nextToken());
            int eMonth = Integer.parseInt(st.nextToken());
            int eDay = eMonth * 100 + Integer.parseInt(st.nextToken());
            flowers[i] = new Flower(sDay, eDay);
        }

        Arrays.sort(flowers);

        int startDay = 301;
        int endDay = 1201;
        int cnt = 0;
        int lastDay = 0;
        int idx = 0;
        boolean hasFlower = true;

        while (startDay < endDay && hasFlower) {
            hasFlower = false;
            for (; idx < N; idx++) {
                if (flowers[idx].sDay > startDay) break;

                if (lastDay < flowers[idx].eDay) {
                    hasFlower = true;
                    lastDay = flowers[idx].eDay;
                }
            }

            if (hasFlower) {
                cnt++;
                startDay = lastDay;
            }
        }

        System.out.println(lastDay < endDay ? 0 : cnt);
    }
}
