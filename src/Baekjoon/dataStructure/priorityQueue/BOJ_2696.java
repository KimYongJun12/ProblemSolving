package Baekjoon.dataStructure.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2696 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            int M = Integer.parseInt(br.readLine());
            Queue<Integer> q = new LinkedList<>();

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

            for (int i = 0; i < M; i++) {
                if (i % 10 == 0) st = new StringTokenizer(br.readLine());

                int now = Integer.parseInt(st.nextToken());
                if (i % 2 == 0) maxHeap.offer(now);
                else minHeap.offer(now);

                if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
                    if (minHeap.peek() < maxHeap.peek()) {
                        int temp = minHeap.poll();
                        minHeap.offer(maxHeap.poll());
                        maxHeap.offer(temp);
                    }
                }

                if (i % 2 == 0) q.offer(maxHeap.peek());
            }

            int qSize = q.size();
            sb.append(qSize).append('\n');
            for (int i = 0; i < qSize; i++) {
                if (i != 0 && i % 10 == 0) sb.append('\n');
                sb.append(q.poll()).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
