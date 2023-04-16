package Baekjoon.graph.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2250 {
    static int N, maxLevel, loc = 0;
    static Node[] tree;
    static int[] minX;
    static int[] maxX;

    static class Node {
        int num, parent, left, right;

        public Node(int num, int left, int right) {
            this.parent = -1;
            this.num = num;
            this.left = left;
            this.right = right;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new Node[N + 1];
        minX = new int[N + 1];
        maxX = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new Node(i, -1, -1);
            minX[i] = N;
            maxX[i] = 0;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if (left != -1) {
                tree[num].left = left;
                tree[left].parent = num;
            }

            if (right != -1) {
                tree[num].right = right;
                tree[right].parent = num;
            }
        }

        // root를 찾는 과정, parent의 초기값을 -1로 두고 바뀌지 않은 노드가 root노드
        int root = 0;
        for (int i = 1; i <= N; i++) {
            if (tree[i].parent == -1) {
                root = i;
                break;
            }
        }

        inOrder(root, 1);

        int ansWidth = 0, ansLevel = 0;
        for (int i = 1; i <= maxLevel; i++) {
            int diff = maxX[i] - minX[i] + 1;
            if (ansWidth < diff) {
                ansWidth = diff;
                ansLevel = i;
            }
        }

        System.out.println(ansLevel + " " + ansWidth);
    }


    /**
     * 중위 순회 = l -> m -> r 순으로 순회
     * loc = 현재 방문하는 노드의 x좌표
     * 왼쪽 끝까지 탐색하면 그 노드의 좌표가 0이 되고 그 인덱스부터 +1씩 증가
     * minX[level] = 현재 level에서 작은 좌표
     * maxX[level] = 현재 level에서 큰 좌표, 여러번 방문해도 loc는 커지기만 하기 때문에 비교 필요 x
     */
    private static void inOrder(int parent, int level) {
        Node now = tree[parent];
        maxLevel = Math.max(maxLevel, level);

        if (now.left != -1) {
            inOrder(now.left, level + 1);
        }

        minX[level] = Math.min(minX[level], loc);
        maxX[level] = loc++;

        if (now.right != -1) {
            inOrder(now.right, level + 1);
        }
    }
}
