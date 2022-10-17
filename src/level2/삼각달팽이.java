package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 삼각달팽이 {

    public static void main(String[] args) {

        삼각달팽이_Solution solution = new 삼각달팽이_Solution();
        int n = 4;
        int[] result = solution.solution(n);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}

/**
 * goal: 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 하여 이에 대한 결과 출력
 */

/**
 * 제한사항
 * 1. n은 1 이상 1,000 이하입니다.
 */

/**
 * 풀이 방법
 * 1. n번, n-1, n-2, n-3이 1이 될때까지 반복
 * 2.
 */

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
