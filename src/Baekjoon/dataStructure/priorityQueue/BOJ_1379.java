package Baekjoon.dataStructure.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1379 {
    static class Lecture {
        int num, start, end;

        public Lecture(int num, int start, int end) {
            this.num = num;
            this.start = start;
            this.end = end;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] roomNum = new int[n + 1];
        Lecture[] lectures = new Lecture[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lectures[i] = new Lecture(num, start, end);
        }
        Arrays.sort(lectures, (o1, o2) -> {
            if (o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });

        PriorityQueue<Lecture> pq = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);

        int maxNum = 1;
        Queue<Integer> emptyRooms = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            Lecture now = lectures[i];

            if (!pq.isEmpty()) {
                if (pq.peek().end > now.start) {
                    if (emptyRooms.isEmpty()) {
                        emptyRooms.offer(maxNum++);
                    }
                } else {
                    while (!pq.isEmpty() && pq.peek().end <= now.start) {
                        emptyRooms.offer(roomNum[pq.poll().num]);
                    }
                }

                roomNum[now.num] = emptyRooms.poll();
            } else {
                roomNum[now.num] = maxNum++;
            }
            pq.offer(now);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxNum - 1).append('\n');
        for (int i = 1; i <= n; i++) sb.append(roomNum[i]).append('\n');

        System.out.println(sb);
    }
}
