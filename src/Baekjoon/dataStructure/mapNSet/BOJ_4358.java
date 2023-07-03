package Baekjoon.dataStructure.mapNSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class BOJ_4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int num = 0;
        TreeMap<String, Integer> map = new TreeMap<>();
        String input;

        while ((input = br.readLine()) != null) {
            map.put(input, map.getOrDefault(input, 0) + 1);
            num++;
        }

        for (String s : map.keySet()) {
            sb.append(s).append(' ').append(String.format("%.4f", (double) map.get(s) * 100 / num)).append('\n');
        }

        System.out.println(sb);
    }
}
