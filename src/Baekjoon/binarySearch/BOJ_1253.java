package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            int now = arr[i];
            boolean goodNum = false;

            fo : for (int j = N - 1; j >= 0; j--) {
                if (j == i) continue;

                int lo = 0;
                int hi = N - 1;

                while (lo <= hi) {
                    int mid = (lo + hi) / 2;
                    int sum = arr[j] + arr[mid];

                    if (sum == now && mid != i && mid != j) {
                        goodNum = true;
                        break fo;
                    } else if (sum < now) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            }

            if (goodNum) ans++;
        }

        System.out.println(ans);
    }
}
