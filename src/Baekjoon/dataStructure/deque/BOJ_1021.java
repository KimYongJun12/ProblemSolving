package Baekjoon.dataStructure.deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            linkedList.add(i);
        }

        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        for (int i = 0, len = N - 1; i < M; i++, len--) {
            int now = Integer.parseInt(st.nextToken());
            int idx = linkedList.indexOf(now);

            if (idx <= len - idx) {
                while (linkedList.getFirst() != now) {
                    linkedList.addLast(linkedList.removeFirst());
                    cnt++;
                }
            } else {
                while (linkedList.getFirst() != now) {
                    linkedList.addFirst(linkedList.removeLast());
                    cnt++;
                }
            }

            linkedList.removeFirst();
        }

        System.out.println(cnt);
    }
}
