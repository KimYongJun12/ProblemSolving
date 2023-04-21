package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2262 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> rank = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) rank.add(Integer.parseInt(st.nextToken()));

        int last = N;
        int ans = 0;

        while (last != 1) {
            for (int i = 0, minDiffIdx; i < last; i++) {
                if (rank.get(i) == last) {
                    if (i == 0) {
                        minDiffIdx = i + 1;
                    } else if (i == last - 1) {
                        minDiffIdx = i - 1;
                    } else {
                        int left = rank.get(i - 1);
                        int right = rank.get(i + 1);

                        minDiffIdx = (left < right) ? i + 1 : i - 1;
                    }

                    ans += last - rank.get(minDiffIdx);
                    rank.remove(i);
                    break;
                }
            }

            last--;
        }
        System.out.println(ans);
    }
}
