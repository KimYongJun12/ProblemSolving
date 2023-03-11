package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3663 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        while (t-- > 0) {
            char[] name = br.readLine().toCharArray();
            int ans = 0;
            int move = Integer.MAX_VALUE;
            for (int i = 0; i < name.length; i++) ans += Math.min(name[i] - 'A', 'Z' + 1 - name[i]);

            for (int i = 0; i < name.length; i++) {
                int j = i + 1;
                while (j < name.length && name[j] == 'A') {
                    j++;
                }

                int right = i + (i + (name.length - j));
                int left = (name.length - j) + ((name.length - j) + i);
                move = Math.min(move, Math.min(right, left));
            }

            ans += move;
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }
}
