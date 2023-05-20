package Programmers.Prog_181188;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Prog_181188 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Solution s = new Solution();
        int N = Integer.parseInt(br.readLine());
        int[][] targets = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            targets[i][0] = a;
            targets[i][1] = b;
        }

        System.out.println(s.solution(targets));
    }
}

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
