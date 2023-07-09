package SamsungSWExpertAcademy.SWEA_1959;

import java.io.FileInputStream;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        /*
        FileInputStream import와 System.setIn()은 온라인 제출시 삭제
        Format
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_번호/input.txt");
         */
        System.setIn(new FileInputStream("src/SamsungSWExpertAcademy/SWEA_1959/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] arr1 = new int[N];
            int[] arr2 = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                arr2[i] = Integer.parseInt(st.nextToken());
            }
            sb.append('#').append(test_case).append(' ').append(findMax(arr1, arr2)).append('\n');
        }
        System.out.println(sb);
    }

    private static int findMax(int[] arr1, int[] arr2) {
        int ret = Integer.MIN_VALUE;
        if (arr1.length > arr2.length) {
            int[] temp = arr1.clone();
            arr1 = arr2;
            arr2 = temp;
        }

        for (int i = 0; i + arr1.length <= arr2.length; i++) {
            int now = 0;
            for (int j = 0; j < arr1.length; j++) {
                now += arr1[j] * arr2[i + j];
            }
            ret = Math.max(now, ret);
        }

        return ret;
    }
}
