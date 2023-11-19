package Baekjoon.dataStructure.mapNSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

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
public class BOJ_25192 {
    static HashSet<String> set = new HashSet();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            if ("ENTER".equals(input)) set.clear();
            else {
                if (!set.contains(input)) {
                    set.add(input);
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
