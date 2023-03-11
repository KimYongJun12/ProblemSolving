package Baekjoon.dataStructure.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6549_SegmentTree {
    static int n;
    static long[] num;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String s;

        while (!(s = br.readLine()).equals("0")) {
            st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            num = new long[n + 1]; // 세그먼트 트리를 쉽게 이용하기 위해 인덱스는 1부터 시작
            for (int i = 1; i <= n; i++) num[i] = Long.parseLong(st.nextToken());

            // 트리 사이즈를 구하는 과정
            int exponent = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
            int treeSize = (int) Math.pow(2, exponent);
            tree = new int[treeSize];

            init(1, n, 1);
            sb.append(findMaxArea(1, n)).append('\n');
        }

        System.out.println(sb);
    }

    /*
    (start ~ end) 범위 내에서 최대 넓이를 구하는 메소드
    넓이 = 높이 * 너비이므로 최소 높이를 기준으로 양 옆으로 탐색하며 최대값을 갱신한다
     */
    private static long findMaxArea(int start, int end) {
        int mid = findMinHeight(1, n, 1, start, end);
        long area = (end - start + 1) * num[mid];

        // 최소 높이 인덱스 왼쪽에 막대가 있는 경우
        if (start <= mid - 1) {
            // 해당 범위에서 최대 넓이를 구해 비교
            long temp = findMaxArea(start, mid - 1);
            area = Math.max(area, temp);
        }

        // 최소 높이 인덱스 오른쪽에 막대가 있는 경우
        if (end >= mid + 1) {
            // 해당 범위에서 최대 넓이를 구해 비교
            long temp = findMaxArea(mid + 1, end);
            area = Math.max(area, temp);
        }

        return area;    // 범위 내에서 가장 큰 값 리턴
    }

    /*
    (start ~ end) 범위 내에서 가장 작은 높이를 가진 인덱스를 찾는 메소드
    기초적인 세그먼트 트리와는 다르게 비교값(높이)과 리턴값(인덱스)이 다르므로 과정이 좀 추가되었다
     */
    private static int findMinHeight(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return -1;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        int mLeft = findMinHeight(start, mid, node * 2, left, right);
        int mRight = findMinHeight(mid + 1, end, node * 2 + 1, left, right);

        if (mLeft == -1) return mRight;
        if (mRight == -1) return mLeft;
        return (num[mLeft] > num[mRight]) ? mRight : mLeft;
    }

    /*
    세그먼트 트리를 초기 세팅하는 메소드
    세그먼트 트리에 저장될 값은 (start ~ end) 범위 내에서 가장 작은 높이를 가진 인덱스
     */
    private static int init(int start, int end, int node) {
        if (start == end) return tree[node] = start;

        int mid = (start + end) / 2;

        int left = init(start, mid, node * 2);
        int right = init(mid + 1, end, node * 2 + 1);

        return tree[node] = (num[left] > num[right]) ? right : left;
    }

}