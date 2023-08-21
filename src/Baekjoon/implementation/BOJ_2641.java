package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 풀이 시작 : 5:15
 * 풀이 완료 : 5:54
 * 풀이 시간 : 39분
 *
 * 문제 해석
 * 모눈종이에 다각형 그림
 * 모양수열로 표시
 * 1 - 우, 2 - 상, 3 - 좌, 4 - 하
 * 표본 모양수열과 여러 모양수열이 주어졌을 때 표본 모양수열을 그릴 수 있는 모양수열을 찾아야 함
 *
 * 구해야 하는 것
 * 표본 모양수열과 여러 모양수열이 주어졌을 때 표본 모양수열을 그릴 수 있는 모양수열을 찾아야 함
 * 첫째 줄에는 가능한 모양수열의 개수, 둘째 줄부터는 모양수열을 출력
 *
 * 문제 입력
 * 첫째 줄 : 표본 모양수열의 길이 L
 * 둘째 줄 : 표본 모양수열의 모습
 * 셋째 줄 : 모양수열의 개수 N
 * 넷째 줄 ~ N개 줄 : 각 모양수열의 모습
 *
 * 제한 요소
 * 1 <= L <= 50
 * 1 <= N <= 100
 *
 * 생각나는 풀이
 * 표본 모양수열을 받아서 가능한 모든 모양수열을 set에 저장
 *  - 같은 방향의 모양수열 = 시작 지점이 한 칸씩 미뤄지고, 숫자의 배열이 같은 수열들 = L개만큼 있음
 *  - 반대 방향의 모양수열 = 순서와 방향이 반대이고, 시작 지점을 한 칸씩 미루면 됨 = L개만큼 있음
 * 모양수열을 입력받아 set에 있는 모양수열이면 출력
 *
 * 구현해야 하는 기능
 * 1. 입력에 따른 표본 모양수열 저장
 * 2. 표본 모양수열이 되는 모든 가짓수 set에 저장
 * 3. 입력으로 들어온 모양수열이 set에 있는지 체크
 * 4. set에 있는 모양수열이라면 cnt++, 출력
 *
 */
public class BOJ_2641 {
    static int L;
    static HashSet<String> sameShape = new HashSet<>();
    static char[] standard;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        L = Integer.parseInt(br.readLine());
        standard = new char[L];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < L; i++) {
            standard[i] = st.nextToken().charAt(0);
        }

        generateAllSet();
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        String input;
        StringBuilder answerBuilder = new StringBuilder();
        while (N-- > 0) {
            input = br.readLine();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < L; i++) {
                sb.append(input.charAt(2 * i));
            }
            if (sameShape.contains(sb.toString())) {
                cnt++;
                answerBuilder.append(input).append('\n');
            }
        }
        System.out.println(cnt);
        System.out.println(answerBuilder);

    }

    private static void generateAllSet() {
        StringBuilder sb = new StringBuilder();
        sb.append(standard);
        char[] invert = {'3', '4', '1', '2'};
        StringBuilder sbReverse = new StringBuilder();
        for (int i = 0; i < L; i++) {
            sbReverse.append(invert[sb.charAt(L - 1 - i) - '1']);
        }

        for (int i = 0; i < L; i++) {
            sameShape.add(sb.toString());
            sameShape.add(sbReverse.toString());
            char first = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(first);
            first = sbReverse.charAt(0);
            sbReverse.deleteCharAt(0);
            sbReverse.append(first);
        }
    }

}
