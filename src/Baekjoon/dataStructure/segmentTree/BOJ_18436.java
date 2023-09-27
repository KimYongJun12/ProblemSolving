package Baekjoon.dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 8:17
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 * 쿼리 수행 프로그램
 * 쿼리 종류
 * - 1 i x : Ai를 X로 바꾼다
 * - 2 l r : [l, r] 구간의 모든 Ai 중에서 짝수 개수 출력
 * - 3 l r : [l, r] 구간의 모든 Ai 중에서 홀수 개수 출력
 *
 * 구해야 하는 것
 * 쿼리 수행 결과
 *
 * 문제 입력
 * 첫째 줄 : 수열 크기 N
 * 둘째 줄 : 수열 요소
 * 셋째 줄 : 쿼리 개수 M
 * 넷째 줄 ~ M개 줄 : 쿼리 내용
 *
 * 제한 요소
 * 1 <= N <= 100000
 * 1 <= M <= 100000
 * 1 <= Ai <= 10^9
 *
 * 생각나는 풀이
 * 세그먼트 트리
 * 홀수, 짝수 세그먼트 트리 만들지 말고 1개만 만들고 여집합으로 구하면 될듯
 * 값 저장할 땐 2로 나눈 나머지만 계산해서 1의 개수(구간합)을 구하면 될듯
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 수열 저장할 배열
 * 2. 세그먼트 트리
 * 3. 쿼리 수행
 *
 */
public class BOJ_18436 {
    static int N;
    static int[] arr;
    static int[] segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) & 1;
        }

        int exp = (int) (Math.ceil(Math.log(N) / Math.log(2))) + 1;
        int treeSize = 1 << exp;
        segTree = new int[treeSize];

        init(1, N, 1);
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                int value = b & 1;
                if (arr[a] != value) {
                    update(1, N, 1, a, value - arr[a]);
                    arr[a] = value;
                }
            } else if (cmd == 2) {
                sb.append(b - a + 1 - findOdd(1, N, 1, a, b)).append('\n');
            } else {
                sb.append(findOdd(1, N, 1, a, b)).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static void update(int start, int end, int idx, int target, int diff) {
        if (target < start || end < target) return;
        segTree[idx] += diff;
        if (start == end) return;
        int mid = (start + end) >> 1;
        update(start, mid, idx << 1, target, diff);
        update(mid + 1, end, (idx << 1) + 1, target, diff);
    }

    private static int findOdd(int start, int end, int idx, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return segTree[idx];

        int mid = (start + end) >> 1;
        return findOdd(start, mid, idx << 1, left, right) + findOdd(mid + 1, end, (idx << 1) + 1, left, right);
    }

    private static void init(int start, int end, int idx) {
        if (start == end) {
            segTree[idx] = arr[start];
            return;
        }
        int mid = (start + end) >> 1;
        init(start, mid, idx << 1);
        init(mid + 1, end, (idx << 1) + 1);
        segTree[idx] = segTree[idx << 1] + segTree[(idx << 1) + 1];
    }
}
