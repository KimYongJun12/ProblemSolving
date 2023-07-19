package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ_5002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Character> list = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            list.add(input.charAt(i));
        }

        int mCnt = 0;
        int wCnt = 0;

        while (!list.isEmpty()) {
            char first = list.getFirst();
            if (first == 'M' && Math.abs(mCnt + 1 - wCnt) <= N) {
                list.removeFirst();
                mCnt++;
                continue;
            } else if (first == 'W' && Math.abs(mCnt - 1 - wCnt) <= N) {
                list.removeFirst();
                wCnt++;
                continue;
            }

            if (list.size() == 1) break;

            char second = list.get(1);
            if (second == 'M' && Math.abs(mCnt + 1 - wCnt) <= N) {
                list.remove(1);
                mCnt++;
                continue;
            } else if (second == 'W' && Math.abs(mCnt - 1 - wCnt) <= N) {
                list.remove(1);
                wCnt++;
                continue;
            }
            break;
        }

        System.out.println(mCnt + wCnt);
    }
}
