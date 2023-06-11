package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        for (int i = 0; i < input.length; i++) {
            char now = input[i];

            if (now == '<') {
                while (input[i] != '>') i++;

            } else if (now != ' ') {
                int start = i;

                while (i < input.length - 1) {
                    if (input[i] == ' ' || input[i] == '<') {
                        i--;
                        break;
                    }
                    i++;
                }

                int mid = (start + i) / 2;
                for (int j = 0; start + j <= mid; j++) {
                    char temp = input[start + j];
                    input[start + j] = input[i - j];
                    input[i - j] = temp;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            sb.append(input[i]);
        }

        System.out.println(sb);
    }
}
