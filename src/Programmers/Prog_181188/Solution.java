package Programmers.Prog_181188;
import java.util.*;

class Solution {
    static class Range implements Comparable<Range> {
        int s, e;

        public Range(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Range o) {
            return this.s - o.s;
        }
    }

    public int solution(int[][] targets) {
        PriorityQueue<Range> pq = new PriorityQueue<>();
        for (int i = 0; i < targets.length; i++) {
            pq.offer(new Range(targets[i][0], targets[i][1]));
        }

        int answer = 0;
        int end = -1;

        while (!pq.isEmpty()) {
            Range now = pq.poll();

            if (now.s < end) {
                end = Math.min(end, now.e);
            } else {
                answer++;
                end = now.e;
            }
        }

        return answer;
    }
}

