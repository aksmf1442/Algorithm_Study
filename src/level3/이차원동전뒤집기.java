package level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 이차원동전뒤집기 {

    public static void main(String[] args) {
        이차원동전뒤집기_Solution solution = new 이차원동전뒤집기_Solution();
        int[][] beginning = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0},
            {0, 1, 0, 1, 0}};
        int[][] target = {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1}};
        System.out.println(solution.solution(beginning, target));
    }
}

/**
 *
 */

class 이차원동전뒤집기_Solution {

    public int solution(int[][] beginning, int[][] target) {
        int result = Integer.MAX_VALUE;
        int row = beginning.length;
        int column = beginning[0].length;

        List<List<Integer>> flipped = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            flipped.add(new ArrayList<>());
            for (int j = 0; j < column; j++) {
                if (beginning[i][j] == 1) {
                    flipped.get(i).add(0);
                    continue;
                }
                flipped.get(i).add(1);
            }
        }

        for (int unit = 0; unit < (1 << row); unit++) {
            List<List<Integer>> rowFlipped = new ArrayList<>();
            int flipCnt = 0;

            for (int i = 0; i < row; i++) {
                int comp = 1 << i;

                if (unit != 0 && comp != 0) {
                    rowFlipped.add(new ArrayList<>(flipped.get(i)));
                    flipCnt++;
                    continue;
                }
                rowFlipped.add(Arrays.stream(beginning[i]).boxed().collect(Collectors.toList()));
            }

            for (int j = 0; j < column; j++) {
                List<Integer> curCol = new ArrayList<>();
                List<Integer> targetCol = new ArrayList<>();

                for (int i = 0; i < row; i++) {
                    curCol.add(rowFlipped.get(i).get(j));
                    targetCol.add(target[i][j]);
                }

                if (curCol != targetCol) {
                    flipColumn(rowFlipped, j);
                    flipCnt++;
                }
            }

            if (compareList(rowFlipped, target)) {
                result = Math.min(result, flipCnt);
            }
        }

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }

        return result;
    }

    private boolean compareList(List<List<Integer>> rowFlipped, int[][] target) {
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target[i].length; j++) {
                if (rowFlipped.get(i).get(j) != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void flipColumn(List<List<Integer>> arr, int col) {
        for (List<Integer> integers : arr) {
            if (integers.get(col) == 1) {
                integers.remove(col);
                integers.add(col, 0);
                continue;
            }
            integers.remove(col);
            integers.add(col, 1);
        }
    }

}
