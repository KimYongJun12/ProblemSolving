package Baekjoon.dataStructure.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2461 {
    static int N, M;
    static int[] count;
    static Student[] students;
    static class Student implements Comparable<Student> {
        int classNum, stat;

        public Student(int classNum, int stat) {
            this.classNum = classNum;
            this.stat = stat;
        }

        @Override
        public int compareTo(Student o) {
            return this.stat - o.stat;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        count = new int[N];
        students = new Student[N * M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int stat = Integer.parseInt(st.nextToken());

                students[i * M + j] = new Student(i, stat);
            }
        }

        Arrays.sort(students);

        int left = 0, right = 0;
        int end = N * M - 1;
        int minDiff = Integer.MAX_VALUE;

        while (left < end && right < end) {
            while (right < end && !hasAllClassNum()) {
                count[students[right++].classNum]++;
            }

            while (count[students[left].classNum] > 1) {
                count[students[left++].classNum]--;
            }

            if (hasAllClassNum()) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int i = left; i < right; i++) {
                    min = Math.min(min, students[i].stat);
                    max = Math.max(max, students[i].stat);
                }

                minDiff = Math.min(minDiff, max - min);
            }

            count[students[left++].classNum]--;
        }

        System.out.println(minDiff);
    }

    private static boolean hasAllClassNum() {
        for (int i = 0; i < N; i++) {
            if (count[i] == 0) return false;
        }
        return true;
    }
}
