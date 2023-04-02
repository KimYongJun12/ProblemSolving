package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_14003 {
    static Pair[] LIS;

    // LIS에 저장할 값(자신의 크기와 자신 직전의 Pair 값)
    static class Pair {
        int value;
        Pair parent;

        public Pair(int value, Pair parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int len = 0;
        LIS = new Pair[N + 1];
        LIS[0] = new Pair(-1_000_000_001, null);

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int nowValue = Integer.parseInt(st.nextToken());
            int idx;

            /**
             * 지금 들어온 값 = 최댓값
             * 최장길이 + 1
             * 최장 길이에 해당하는 인덱스에 현재 값 저장
             * 그렇지 않다면 이분 탐색으로 적절한 위치 탐색해서 그 값을 현재 값으로 치환
             * 최댓값이든 아니든 삽입한 인덱스 바로 전 값을 부모로 저장
             */
            if (nowValue > LIS[len].value) {
                len++;
                idx = len;
            } else {
                idx = binarySearchLowerBound(nowValue, len);
            }

            LIS[idx] = new Pair(nowValue, LIS[idx - 1]);
        }

        // 정답출력
        StringBuilder sb = new StringBuilder();
        sb.append(len).append('\n');

        Pair ans = LIS[len];
        Stack<Integer> stack = new Stack<>();

        while (ans.parent != null) {
            stack.push(ans.value);
            ans = ans.parent;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }

        System.out.println(sb);
    }

    private static int binarySearchLowerBound(int nowValue, int hi) {
        int lo = 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (LIS[mid].value < nowValue) {
                lo = mid + 1;
            } else hi = mid;
        }

        return lo;
    }
}
