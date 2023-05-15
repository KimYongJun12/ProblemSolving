package Baekjoon.dataStructure.mapNSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1351 {
    static HashMap<Long, Long> dp = new HashMap<>();
    static long P, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());

        dp.put(0L, 1L);
        System.out.println(memo(N));
    }

    private static Long memo(long n) {
        if (dp.containsKey(n)) return dp.get(n);

        long a = (n / P);
        long b = (n / Q);
        dp.put(n, memo(a) + memo(b));
        return dp.get(n);
    }
}
