package baekjoon;

import java.io.*;
import java.util.Arrays;

public class 보물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] candidate1 = br.readLine().split(" ");
        Integer[] arr1 = new Integer[candidate1.length];
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(candidate1[i]);
        }

        String[] candidate2 = br.readLine().split(" ");
        Integer[] arr2 = new Integer[candidate1.length];
        for (int i = 0; i < n; i++) {
            arr2[i] = Integer.parseInt(candidate2[i]);
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2, (a, b) -> b - a);
        int result = 0;

        for (int i = 0; i < n; i++) {
            result += (arr1[i] * arr2[i]);
        }

        bw.write(String.valueOf(result));
        bw.flush();

        bw.close();
    }
}
