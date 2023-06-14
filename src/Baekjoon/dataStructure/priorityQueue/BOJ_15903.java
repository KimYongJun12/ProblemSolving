package Baekjoon.dataStructure.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15903 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> card = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            card.offer(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            long first = card.poll();
            long second = card.poll();

            long sum = first + second;
            card.offer(sum);
            card.offer(sum);
        }

        long ans = 0;
        while (!card.isEmpty()) {
            ans += card.poll();
        }

        System.out.println(ans);
    }
}
