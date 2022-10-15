package 복습;

import java.util.Arrays;

public class 양궁대회복습 {

    public static void main(String[] args) {
        양궁대회복습_Solution solution = new 양궁대회복습_Solution();
        int n = 5;
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int[] result = solution.solution(n, info);

        for (int i = 0; i < result.length; i++) {
            System.out.println("result = " + result[i]);
        }
    }
}

class 양궁대회복습_Solution {

    int[] result = {-1};
    int[] lion = new int[11];
    int MAX_VALUE = Integer.MIN_VALUE;

    public int[] solution(int n, int[] info) {
        Arrays.fill(lion, 0);
        dfs(n, info, 0);
        return result;
    }

    private void dfs(int n, int[] info, int count) {
        if (count == n) {
            int apeachScore = 0;
            int lionScore = 0;

//            for (int i = 0; i < lion.length; i++) {
//                System.out.print(lion[i] + ",");
//            }
//            System.out.println();

            for (int i = 0; i < 11; i++) {
                if ((info[i] == 0 && lion[i] == 0)) {
                    continue;
                }

                if (info[i] >= lion[i]) {
                    apeachScore += 10 - i;
                } else {
                    lionScore += 10 - i;
                }
            }

            int scoreDiff = lionScore - apeachScore;
            if (scoreDiff > 0 && MAX_VALUE <= scoreDiff) {
                result = lion.clone();
                MAX_VALUE = scoreDiff;
            }

            return;
        }

        for (int i = 0; i < 11 && lion[i] <= info[i]; i++) {
            lion[i]++;
            dfs(n, info, count + 1);
            lion[i]--;
        }
    }
}
