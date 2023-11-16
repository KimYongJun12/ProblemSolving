package Baekjoon.dataStructure.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 풀이 시작 :
 * 풀이 완료 :
 * 풀이 시간 :
 *
 * 문제 해석
 *
 * 구해야 하는 것
 *
 * 문제 입력
 *
 * 제한 요소
 *
 * 생각나는 풀이
 *
 *
 * 구현해야 하는 기능
 *
 */
public class BOJ_1644 {
    static ArrayList<Integer> primeNum = new ArrayList<>();
    static boolean[] isPrime;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isPrime = new boolean[N + 1];
        makePrime();

        int left = 0, right = 0;
        int sum = 2;
        int ans = 0;

        while (N != 1 && left <= right) {
            if (sum == N) {
                ans++;
                sum -= primeNum.get(left++);
            } else if (sum < N) {
                if (++right == primeNum.size()) break;
                sum += primeNum.get(right);
            } else {
                sum -= primeNum.get(left++);
            }
        }

        System.out.println(ans);
    }

    private static void makePrime() {
        if (N == 1) return;
        primeNum.add(2);
        for (int i = 2; i <= N; i += 2) {
            isPrime[i] = true;
        }
        for (int i = 3; i <= N; i += 2) {
            if (isPrime[i]) continue;
            primeNum.add(i);
            for (int j = 1; i * j <= N; j += 2) {
                isPrime[i * j] = true;
            }
        }
    }
}
