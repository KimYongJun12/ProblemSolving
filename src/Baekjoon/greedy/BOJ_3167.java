package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3167 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int min = 0, max = 0;
        int minCheckedP = 0;
        int maxUncheckedP = 0;
        int remainP = 0;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int outP = Integer.parseInt(st.nextToken());
            int inP = Integer.parseInt(st.nextToken());
            remainP += (inP - outP);

            min += Math.max(outP - minCheckedP, 0);
            minCheckedP = Math.max(0, minCheckedP - outP);

            max += Math.min(maxUncheckedP, outP);
            maxUncheckedP = Math.max(0, maxUncheckedP - outP);

            maxUncheckedP += inP;

            if (i % k == 1 || k == 1) {
                minCheckedP = remainP;
                maxUncheckedP = 0;
            }
        }

        System.out.println(min + " " + max);
    }

}