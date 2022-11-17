package level3;

public class 공이동시뮬레이션 {

    public static void main(String[] args) {

        공이동시뮬레이션_Solution solution = new 공이동시뮬레이션_Solution();
        int n = 2;
        int m = 2;
        int x = 0;
        int y = 0;
        int[][] queries = {{2, 1}, {0, 1}, {1, 1}, {0, 1}, {2, 1}};
        System.out.println(solution.solution(n, m, x, y, queries));
    }
}

/**
 * 0 - 좌 1 - 우 2 - 위 3 - 하
 */

/**
 * 1. 완전탐색을 통해서 하게 되면 시간 복잡도가 날 수밖에 없다.
 * 2.
 */

class 공이동시뮬레이션_Solution {

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long xStart = x;
        long xEnd = x;
        long yStart = y;
        long yEnd = y;

        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0];
            int count = queries[i][1];

            // 좌
            if (command == 0) {
                if (yStart > 0) {
                    yStart += count;
                }
                yEnd = Math.min(m - 1, yEnd + count);
            }

            // 우
            if (command == 1) {
                if (yEnd < m - 1) {
                    yEnd -= count;
                }
                yStart = Math.max(0, yStart - count);
            }

            // 상
            if (command == 2) {
                if (xStart > 0) {
                    xStart += count;
                }
                xEnd = Math.min(n - 1, xEnd + count);
            }

            // 하
            if (command == 3) {
                if (xEnd < n - 1) {
                    xEnd -= count;
                }
                xStart = Math.max(0, xStart - count);
            }

            if (xStart >= n || xEnd < 0 || yStart >= m || yEnd < 0) {
                return 0;
            }
        }

        return (xEnd - xStart + 1) * (yEnd - yStart + 1);
    }
}
