package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1114 {
    static int l, k, c;
    static int[] point;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        point = new int[k + 2];
        point[0] = 0;
        point[k + 1] = l;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= k; i++) point[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(point);

        int left = 1;
        int right = l;

        int maxLength = l;
        int firstPoint = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int[] res = cut(mid);
            if (res[0] > c) {
                left = mid + 1;
            } else {
                right = mid - 1;
                maxLength = mid;
                firstPoint = res[1];
            }
        }

        System.out.println(maxLength + " " + firstPoint);
    }

    private static int[] cut(int mid) {
        int cnt = 0;
        int diff = 0;
        int[] res = new int[2];

        for (int i = k; i >= 0; i--) {
            diff += point[i + 1] - point[i];

            if (diff > mid) {
                diff = point[i + 1] - point[i];
                if (diff > mid) {
                    res[0] = c + 1;
                    return res;
                }
                cnt++;
            }
        }

        res[0] = cnt;
        res[1] = (cnt < c) ? point[1] : diff;

        return res;
    }
}