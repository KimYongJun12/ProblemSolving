package Baekjoon.dataStructure.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2014 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        long[] primes = new long[K];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            primes[i] = Long.parseLong(st.nextToken());
            pq.offer(primes[i]);
        }

        for (int i = 0; i < N - 1; i++) { // N - 1번 수행
            long now = pq.poll(); // i번째 수행 결과 가장 작은 곱
            for (int j = 0; j < K; j++) { // 가장 작은 곱과 모든 소수와 곱셈
                pq.offer(now * primes[j]); // 그 값을 넣음
                if (now % primes[j] == 0) break; // 중복 처리
            }
        }

        System.out.println(pq.poll()); // N번째 작은 곱
    }
}

