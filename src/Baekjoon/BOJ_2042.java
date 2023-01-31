package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042 {
    static long[] num, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        num = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            num[i] = Long.parseLong(br.readLine());
        }

        /*
        세그먼트 트리의 사이즈는 2^k >= n을 만족하는 k 이상이어야 함.
        양 변에 log를 취하면 k >= log n / log 2
         */
        int exponent = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int treeSize = (int) Math.pow(2, exponent);
        tree = new long[treeSize];

        init(1, n, 1);

        int query = m + k;
        while (query-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int firstNum = Integer.parseInt(st.nextToken());
            long secondNum = Long.parseLong(st.nextToken());

            if (cmd == 1) {
                long diff = secondNum - num[firstNum];
                num[firstNum] = secondNum;
                update(1, n, 1, firstNum, diff);
            } else {
                sb.append(prefixSum(1, n, 1, firstNum, (int) secondNum)).append('\n');
            }
        }

        System.out.println(sb);
    }

    /*
    세그먼트 트리를 초기화 하는 메소드
    재귀를 통해 구현
    루트 노드는 인덱스 1, 각 노드는 부모 범위의 절반씩 누적합을 저장
    리프 노드에 도달(start == end)하면 return 해주면서 tree에 값 저장
     */
    private static long init(int start, int end, int node) {
        if (start == end) return tree[node] = num[start];

        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    /*
    세그먼트 트리에서 특정 인덱스의 값을 수정하는 메소드
    마찬가지로 재귀를 통해 구현
    수정할 값이 들어 있는 노드만 원래 값과 현재 값의 차이를 더해줌
     */
    private static void update(int start, int end, int node, int index, long diff) {
        if (start > index || end < index) return;

        tree[node] += diff;

        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }

    /*
    구간합을 구하는 메소드
    재귀로 구현
    구하고자 하는 범위에서 벗어난 노드는 0 리턴
    구하고자 하는 범위 내에 완전히 속한 노드는 그 값 리턴
    그렇지 않으면 재귀로 내려가면서 범위 내에 속한 노드가 나올 때까지 탐색
     */
    private static long prefixSum(int start, int end, int node, int startSum, int endSum) {
        if (startSum > end || endSum < start) return 0;
        if (startSum <= start && end <= endSum) return tree[node];

        int mid = (start + end) / 2;
        return prefixSum(start, mid, node * 2, startSum, endSum) + prefixSum(mid + 1, end, node * 2 + 1, startSum, endSum);
    }
}
