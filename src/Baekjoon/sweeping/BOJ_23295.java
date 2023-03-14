package Baekjoon.sweeping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_23295 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int maxEnd = 0;
        int[] times = new int[100001];
        int[] prefix = new int[100001];

        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(br.readLine());

            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                // 스위핑 기법
                times[start]++;
                times[end]--;
                maxEnd = Math.max(maxEnd, end);
            }
        }

        times[0] = prefix[0] = 0;

        for (int i = 1; i <= maxEnd; i++) {
            times[i] += times[i - 1];
            // 누적합
            prefix[i] += prefix[i - 1] + times[i];
        }

        int max = prefix[t - 1];
        int s = 0;
        int e = t;

        for (int i = 1; i <= maxEnd - t; i++) {
            // now : i ~ i + t까지 스터디 참여 인원 수
            int now = prefix[i + t - 1] - prefix[i - 1];
            if (now > max) {
                s = i;
                e = i + t;
                max = now;
            }
        }

        System.out.println(s + " " + e);
    }
}
