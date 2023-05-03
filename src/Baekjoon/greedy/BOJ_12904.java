package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ_12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        char[] t = br.readLine().toCharArray();

        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < t.length; i++) {
            deque.add(t[i]);
        }

        boolean isReverse = false;
        while (S.length() < deque.size()) {
            char now = isReverse ? deque.pollFirst() : deque.pollLast();

            if (now == 'B') isReverse = !isReverse;
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(isReverse ? deque.pollLast() : deque.pollFirst());
        }

        System.out.println(sb.toString().equals(S) ? 1 : 0);
    }
}