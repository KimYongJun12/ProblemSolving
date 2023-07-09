package SamsungSWExpertAcademy.SWEA_1248;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
    static int V;
    static int[] depth;
    static int[] parent;
    static int[] numOfChildren;
    static ArrayList<Integer>[] tree;

    public static void main(String args[]) throws Exception
    {
        /*
        FileInputStream import와 System.setIn()은 온라인 제출시 삭제
        Format
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_번호/input.txt");
         */
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_1248/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            depth = new int[V + 1];
            parent = new int[V + 1];
            parent[1] = 1;
            tree = new ArrayList[V + 1];
            numOfChildren = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                tree[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int par = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                parent[child] = par;
                tree[par].add(child);
            }

            dfs(1, 0);
            int ans = findParent(a, b);
            sb.append('#').append(test_case).append(' ').append(ans).append(' ').append(numOfChildren[ans]).append('\n');
        }

        System.out.println(sb);
    }

    private static int findParent(int a, int b) {
        int depthA = depth[a];
        int depthB = depth[b];

        while (depthA > depthB) {
            a = parent[a];
            depthA--;
        }

        while (depthB > depthA) {
            b = parent[b];
            depthB--;
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    private static int dfs(int idx, int dep) {
        int num = 1;
        depth[idx] = dep;
        for (int ch : tree[idx]) {
            num += dfs(ch, dep + 1);
        }
        return numOfChildren[idx] = num;
    }
}
