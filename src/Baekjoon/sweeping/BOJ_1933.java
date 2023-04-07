package Baekjoon.sweeping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1933 {
    static class Building implements Comparable<Building> {
        int x, h;

        public Building(int x, int h) {
            this.x = x;
            this.h = h;
        }

        @Override
        public int compareTo(Building o) {
            // 좌표 오름차순, 좌표 같다면 높이 내림차순
            if (this.x == o.x) return o.h - this.h;
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Building> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            // 빌딩의 왼쪽이라면 높이, 오른쪽이라면 -높이 삽입
            pq.offer(new Building(l, h));
            pq.offer(new Building(r, -h));
        }

        // treemap<높이, 같은 높이인 빌딩 개수>, 정렬 순서는 key(빌딩 높이) 내림차순
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(Collections.reverseOrder());
        treeMap.put(0, 1); // 항상 treeMap.firstKey() 메소드 이용하기 위해 높이 0짜리 빌딩 삽입
        int maxX = -1, maxH = 0;

        while (!pq.isEmpty()) {
            Building b = pq.poll();

            if (b.h > 0) { // 건물의 왼쪽 인덱스인 경우
                treeMap.put(b.h, treeMap.getOrDefault(b.h, 0) + 1);
            } else { // 건물의 오른쪽 인덱스인 경우
                int numOfSameHeightBuilding = treeMap.get(-b.h);
                if (numOfSameHeightBuilding == 1) treeMap.remove(-b.h);
                else treeMap.put(-b.h, numOfSameHeightBuilding - 1);
            }

            int nowMaxHeight = treeMap.firstKey();
            if (b.x != maxX && maxH != nowMaxHeight) {
                maxX = b.x;
                maxH = nowMaxHeight;
                sb.append(maxX).append(' ').append(maxH).append(' ');
            }
        }

        System.out.println(sb);
    }
}
