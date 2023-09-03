package Baekjoon.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_9536 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer allSoundTokenizer;
        StringTokenizer animalSoundTokenizer;
        HashSet<String> notFoxSound = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        String input;

        while (tc-- > 0) {
            notFoxSound.clear();
            allSoundTokenizer = new StringTokenizer(br.readLine());

            while (!(input = br.readLine()).equals("what does the fox say?")) {
                animalSoundTokenizer = new StringTokenizer(input);
                animalSoundTokenizer.nextToken();
                animalSoundTokenizer.nextToken();

                notFoxSound.add(animalSoundTokenizer.nextToken());
            }

            while (allSoundTokenizer.hasMoreTokens()) {
                String s = allSoundTokenizer.nextToken();
                if (!notFoxSound.contains(s)) {
                    sb.append(s).append(' ');
                }
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}
