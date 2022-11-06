package level2;

import java.util.ArrayList;
import java.util.List;

public class 행렬테두리회전하기 {

    public static void main(String[] args) {
        행렬테두리회전하기_Solution solution = new 행렬테두리회전하기_Solution();
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        int[] result = solution.solution(rows, columns, queries);
        for (int i : result) {
            System.out.print("i," + i);
        }
    }
}

class 행렬테두리회전하기_Solution {

    public int[] solution(int rows, int columns, int[][] queries) {
        List<Integer> result = new ArrayList<>();
        int[][] arr1 = new int[rows][columns];
        int[][] arr2 = new int[rows][columns];

        int value = 1;
        // 배열에 값 초기화
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr1[i][j] = value;
                arr2[i][j] = value++;
            }
        }

        for (int[] query : queries) {
            int x1 = query[0] - 1;
            int y1 = query[1] - 1;
            int x2 = query[2] - 1;
            int y2 = query[3] - 1;

            int xMoveLength = x2 - x1;
            int yMoveLength = y2 - y1;

            int minValue = Integer.MAX_VALUE;

            // 왼쪽 상단은 오른쪽으로 (x1, y1) ~ (x1, y2-1)
            for (int i = 1; i <= yMoveLength; i++) {
                int moveValue = arr1[x1][y1 + i - 1];
                arr2[x1][y1 + i] = moveValue;
                minValue = Math.min(minValue, moveValue);
            }

            // 왼쪽 하단은 위쪽으로 (x1+1, y1) ~ (x2, y1)
            for (int i = 1; i <= xMoveLength; i++) {
                int moveValue = arr1[x1 + i][y1];
                arr2[x1 + i - 1][y1] = moveValue;
                minValue = Math.min(minValue, moveValue);
            }

            // 우측 상단은 아래로 (x1, y2) ~ (x2 - 1, y2)
            for (int i = 1; i <= xMoveLength; i++) {
                int moveValue = arr1[x1 + i - 1][y2];
                arr2[x1 + i][y2] = moveValue;
                minValue = Math.min(minValue, moveValue);
            }

            // 우측 하단은 왼쪽으로 (x2, y2) ~ (x2, y1 + 1)
            for (int i = 1; i <= yMoveLength; i++) {
                int moveValue = arr1[x2][y2 - i + 1];
                arr2[x2][y2 - i] = moveValue;
                minValue = Math.min(minValue, moveValue);
            }

            // 깊은 복사
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    arr1[i][j] = arr2[i][j];
                }
            }

            result.add(minValue);
        }

        return result.stream().mapToInt(t -> t).toArray();
    }
}
