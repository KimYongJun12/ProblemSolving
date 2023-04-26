package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16434_binarySearch {
    static int N;
    static Room[] dungeon;

    static class Room {
        boolean potion;
        long a, h;

        public Room(boolean potion, long a, long h) {
            this.potion = potion;
            this.a = a;
            this.h = h;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long ATK = Integer.parseInt(st.nextToken());

        dungeon = new Room[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());

            dungeon[i] = new Room(t == 2, a, h);
        }

        long lo = 1L, hi = Long.MAX_VALUE;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;

            boolean result = simulation(mid, ATK);

            if (result) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(lo);
    }

    private static boolean simulation(long hMax, long ATK) {
        long hCur = hMax;

        for (int i = 0; i < N; i++) {
            Room now = dungeon[i];
            if (now.potion) {
                ATK += now.a;
                hCur = Math.min(hCur + now.h, hMax);
            } else {
                long battle = now.h / ATK;
                if (now.h % ATK == 0) battle--;
                long damage = battle * now.a;
                hCur -= damage;
                if (hCur <= 0) return false;
            }
        }

        return true;
    }
}
