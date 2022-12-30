package level3;

public class 공이동시뮬레이션 {

    public static void main(String[] args) {

        공이동시뮬레이션_Solution solution = new 공이동시뮬레이션_Solution();
        int n = 1000;
        int m = 1000;
        int x = 1;
        int y = 1;
        int[][] queries = {{0, 100001}, {2, 100001}};
        System.out.println(solution.solution(n, m, x, y, queries));
    }
}

/**
 * 간단한 문제 설명
 * - n x m 크기의 행렬의 각 칸에서 주어진 쿼리들을 실행했을 때 [x][y]에 도달할 수 있는 개수 구하기
 * -
 */

/**
 * 각 칸에서 각각 쿼리들을 실행했을 때 시간 복잡도
 * -> 10^9 * 10^9 * 200000
 *
 * 하나의 칸에서 쿼리들을 한 번만 실행했을 때 시간 복잡도
 * -> 200000
 */

class 공이동시뮬레이션_Solution {

    public long solution(int n, int m, int x, int y, int[][] queries) {
        n-=1;
        m-=1;
        long xStart = x;
        long xEnd = x;
        long yStart = y;
        long yEnd = y;

        for (int i = queries.length - 1; i >= 0; i--) {
            int[] query = queries[i];
            int idx = query[0];
            int count = query[1];

            // 0 - 좌, 1 - 우, 2 - 상, 3 - 하 - 정방향
            // 0 - 우, 1 - 좌, 2 - 하, 3 - 상  - 반대방향
            if (idx == 0) {
                if (yStart == 0) {
                    yStart -= count;
                }
                yStart = yStart + count;
                yEnd = Math.min(yEnd + count, m);
            }

            if (idx == 1) {
                if (yEnd == m) {
                    yEnd += count;
                }
                yStart = Math.max(yStart - count, 0);
                yEnd = yEnd - count;
            }

            if (idx == 2) {
                if (xStart == 0) {
                    xStart -= count;
                }
                xStart = xStart + count;
                xEnd = Math.min(xEnd + count, n);
            }

            if (idx == 3) {
                if (xEnd == n) {
                    xEnd += count;
                }
                xStart = Math.max(xStart - count, 0);
                xEnd = xEnd - count;
            }
            if(xStart > n || xEnd < 0 || yStart > m || yEnd < 0)
                return 0;
        }

        System.out.println("xStart = " + xStart);
        System.out.println("xEnd = " + xEnd);
        System.out.println("yStart = " + yStart);
        System.out.println("yEnd = " + yEnd);
        return (xEnd - xStart + 1) * (yEnd - yStart + 1);
    }
}
