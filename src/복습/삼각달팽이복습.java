package 복습;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 삼각달팽이복습 {

    public static void main(String[] args) {

        삼각달팽이_Solution solution = new 삼각달팽이_Solution();
        int n = 4;
        int[] result = solution.solution(n);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}

class 삼각달팽이_Solution {

    int[] dx = {1, 0, -1};
    int[] dy = {0, 1, -1};

    public int[] solution(int n) {

        int[][] snail = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(snail[i], 0);
        }

        int idx = 0;
        int x = -1;
        int y = 0;
        int value = 0;
        
        while (n >= 1) {
            for (int i = 0; i < n; i++) {
                value++;
                x = x + dx[idx];
                y = y + dy[idx];
                snail[x][y] = value;
            }
            idx = (idx + 1) % 3;
            n--;
        }
        return extractResultFromSnail(snail);
    }

    private int[] extractResultFromSnail(int[][] snail) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < snail.length; i++) {
            for (int j = 0; j < snail[0].length; j++) {
                if (snail[i][j] != 0) {
                    result.add(snail[i][j]);
                }
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }


}
