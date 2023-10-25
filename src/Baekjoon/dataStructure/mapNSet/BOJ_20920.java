package Baekjoon.dataStructure.mapNSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 10:04
 * 풀이 완료 :
 * 풀이 시간 :
 * <p>
 * 문제 해석
 * 단어 암기
 * 우선순위
 * 1. 자주 나오는 것
 * 2. 단어 길이가 긴 것
 * 3. 알파벳 사전 순
 * <p>
 * 구해야 하는 것
 * 길이 M 이상의 외울 단어 목록
 * <p>
 * 문제 입력
 * 첫째 줄 : N, M
 * 둘째 줄 ~ N개 줄 : 단어
 * <p>
 * 제한 요소
 * 1 <= N <= 100000
 * 1 <= M <= 10
 * <p>
 * 생각나는 풀이
 * 맵
 * <p>
 * 구현해야 하는 기능
 */
public class BOJ_20920 {
    static class Pair implements Comparable<Pair> {
        String voca;
        int number;

        public Pair(String voca, int number) {
            this.voca = voca;
            this.number = number;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.number == o.number) {
                if (this.voca.length() == o.voca.length()) {
                    return this.voca.compareTo(o.voca);
                }
                return o.voca.length() - this.voca.length();
            }
            return o.number - this.number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            if (input.length() < M) continue;
            map.put(input, map.getOrDefault(input, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (String v : map.keySet()) {
            pq.offer(new Pair(v, map.get(v)));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll().voca).append('\n');
        }
        System.out.println(sb);
    }
}
