package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2680 {
    static char[] alpha = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
    static char[] inputArray;
    static int idx, totalNum;
    static StringBuilder nowBuilder, answerBuilder = new StringBuilder();
    static final int MIN_ASCII = 32, MAX_ASCII = 126;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            totalNum = 0;
            idx = 0;
            nowBuilder = new StringBuilder();
            String input = br.readLine();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < input.length(); i += 2) {
                String binary = String.format("%8s", Integer.toBinaryString(Integer.parseInt(input, i, i + 2, 16))).replace(" ", "0");
                sb.append(binary);
            }


            inputArray =  sb.toString().toCharArray();

            boolean isFinish = false;
            while (!isFinish) {
                int modeIdx = -1;
                for (int i = 0; i < 4; i++) {
                    if (i + idx == inputArray.length) break;
                    if (inputArray[i + idx] == '1') {
                        modeIdx = i;
                        break;
                    }
                }
                idx += 4;
                switch (modeIdx) {
                    case 3:
                        numeric();
                        break;
                    case 2:
                        alphanumeric();
                        break;
                    case 1:
                        eightBit();
                        break;
                    case 0:
                        kanji();
                        break;
                    default:
                        isFinish = true;
                        break;
                }
            }


            answerBuilder.append(totalNum).append(' ').append(nowBuilder).append('\n');
        }
        System.out.println(answerBuilder);
    }



    private static void numeric() {
        int countBit = 10;
        int numOfCharacters = 0;
        int bitCount = 10;

        for (int i = 0; i < countBit; i++) {
            if (inputArray[(idx + countBit - 1 - i)] == '1') {
                numOfCharacters += (1 << i);
            }
        }

        totalNum += numOfCharacters;
        idx += countBit;

        while (numOfCharacters >= 3) {
            int num = 0;
            for (int i = 0; i < bitCount; i++) {
                if (inputArray[(idx + bitCount - 1 - i)] == '1') {
                    num += (1 << i);
                }
            }

            numOfCharacters -= 3;
            idx += bitCount;
            nowBuilder.append(num / 100);
            nowBuilder.append((num % 100) / 10);
            nowBuilder.append((num % 10));
        }


        if (numOfCharacters == 2) {
            int num = 0;
            for (int i = 0; i < 7; i++) {
                if (inputArray[(idx + 6 - i)] == '1') {
                    num += (1 << i);
                }
            }

            idx += 7;
            nowBuilder.append(num / 10);
            nowBuilder.append((num % 10));
        } else if (numOfCharacters == 1) {
            int num = 0;
            for (int i = 0; i < 4; i++) {
                if (inputArray[(idx + 3 - i)] == '1') {
                    num += (1 << i);
                }
            }

            idx += 4;
            nowBuilder.append(num);
        }

    }

    private static void alphanumeric() {
        int countBit = 9;
        int numOfCharacters = 0;
        int bitCount = 11;
        for (int i = 0; i < countBit; i++) {
            if (inputArray[(idx + countBit - 1 - i)] == '1') {
                numOfCharacters += (1 << i);
            }
        }
        totalNum += numOfCharacters;
        idx += countBit;

        while (numOfCharacters >= 2) {
            int num = 0;
            for (int i = 0; i < bitCount; i++) {
                if (inputArray[(idx + bitCount - 1 - i)] == '1') {
                    num += (1 << i);
                }
            }

            numOfCharacters -= 2;
            idx += bitCount;
            nowBuilder.append(alpha[num/45]).append(alpha[num%45]);
        }

        if (numOfCharacters == 1) {
            int num = 0;
            for (int i = 0; i < 6; i++) {
                if (inputArray[(idx + 6 - 1 - i)] == '1') {
                    num += (1 << i);
                }
            }

            idx += 6;
            nowBuilder.append(alpha[num%45]);
        }
    }

    private static void eightBit() {
        int countBit = 8;
        int numOfCharacters = 0;
        int bitCount = 8;

        for (int i = 0; i < countBit; i++) {
            if (inputArray[(idx + countBit - 1 - i)] == '1') {
                numOfCharacters += (1 << i);
            }
        }
        totalNum += numOfCharacters;

        idx += countBit;

        while (numOfCharacters > 0) {
            int num = 0;
            for (int i = 0; i < bitCount; i++) {
                if (inputArray[(idx + bitCount - 1 - i)] == '1') {
                    num += (1 << i);
                }
            }

            numOfCharacters--;
            idx += bitCount;
            if (isInRange(num)) {
                nowBuilder.append((char) num);
            } else {
                String hex = String.format("\\%2S", Integer.toHexString(num)).replace(" ", "0");
                nowBuilder.append(hex);
            }
        }

    }

    private static void kanji() {
        int countBit = 8;
        int numOfCharacters = 0;
        int bitCount = 13;

        for (int i = 0; i < countBit; i++) {
            if (inputArray[(idx + countBit - 1 - i)] == '1') {
                numOfCharacters += (1 << i);
            }
        }
        totalNum += numOfCharacters;
        idx += countBit;

        while (numOfCharacters > 0) {
            int num = 0;
            for (int i = 0; i < bitCount; i++) {
                if (inputArray[(idx + bitCount - 1 - i)] == '1') {
                    num += (1 << i);
                }
            }

            numOfCharacters--;
            idx += bitCount;

            String hex = String.format("#%4S", Integer.toHexString(num)).replace(" ", "0");
            nowBuilder.append(hex);
        }
    }

    private static boolean isInRange(int num) {
        return MIN_ASCII <= num && num <= MAX_ASCII;
    }
}