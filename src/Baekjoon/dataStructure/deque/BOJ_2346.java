package Baekjoon.dataStructure.deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_2346 {
    static class Node {
        int num, value;

        public Node(int num, int value) {
            this.num = num;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Node> deque = new ArrayDeque<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            deque.addLast(new Node(i, Integer.parseInt(st.nextToken())));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            Node now = deque.pollFirst();
            sb.append(now.num).append(' ');
            if (i == N - 1) break;
            if (now.value > 0) {
                for (int j = 0; j < now.value - 1; j++) {
                    deque.addLast(deque.pollFirst());
                }
            } else {
                for (int j = 0; j < -now.value; j++) {
                    deque.addFirst(deque.pollLast());
                }
            }
        }

        System.out.println(sb);
    }
}
