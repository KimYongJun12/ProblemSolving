package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2437 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) num[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(num);
        int sum = 0;

        /*
        직접 몇 번 해보면 규칙이 보인다.
        0부터 시작해 sum + 1 < num[i]일 때 sum + 1이 측정할 수 없는 최소 무게가 된다.
         */
        for (int i = 0; i < n; i++) {
            if (sum + 1 < num[i]) break;
            sum += num[i];
        }
        
        System.out.println(sum + 1);
    }

}
