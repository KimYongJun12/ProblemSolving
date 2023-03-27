package Baekjoon.dataStructure.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        /**
         * minHeap = 중앙값 초과값, 오름차순 정렬
         * maxHeap = 중앙값 이하값, 내림차순 정렬
         */

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        while (N-- > 0) {
            int now = Integer.parseInt(br.readLine());
            if (minHeap.size() == maxHeap.size()) maxHeap.offer(now);
            else minHeap.offer(now);
            if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
                // minHeap의 최소값이 maxHeap의 최대값보다 작으면 서로 교환
                // 항상 minHeap의 값은 중앙값 초과, maxHeap의 값은 중앙값 이하로 유지
                if (minHeap.peek() < maxHeap.peek()) {
                    int temp = maxHeap.poll();
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(temp);
                }
            }

            sb.append(maxHeap.peek()).append('\n');
        }

        System.out.println(sb);
    }
}
