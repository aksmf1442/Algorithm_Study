package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수리공항승 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());    // 물이 새는 곳의 개수
        int L = Integer.parseInt(st.nextToken());    // 테이프 길이
        int min = 0;    // 필요한 테이프의 최소 개수
        int[] arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] == (L - 1)) {
                min++;
                i++;
            } else {
                min++;
            }
        }
        System.out.println(min);
        bf.close();
    }
}
