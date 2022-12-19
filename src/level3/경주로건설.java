package level3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 경주로건설 {

    public static void main(String[] args) {
        경주로건설_Solution solution = new 경주로건설_Solution();
        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(solution.solution(board));
    }
}

class 경주로건설_Solution {
    // 0 - 왼쪽, 1 - 오른쪽, 2 - 위쪽, 3 - 아래쪽
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};

    public int solution(int[][] board) {
        int N = board.length;

        int[][][] costArr = new int[N][N][4];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                Arrays.fill(costArr[i][j], Integer.MAX_VALUE);

        // 오른쪽, 위쪽
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, 1));
        queue.add(new Node(0, 0, 0, 3));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int cost = node.cost + (node.direction == i ? 100 : 600);

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 1
                    || costArr[nx][ny][i] <= cost) {
                    continue;
                }
                costArr[nx][ny][i] = cost;
                queue.add(new Node(nx, ny, cost, i));
            }
        }

        return Arrays.stream(costArr[N - 1][N - 1]).min().getAsInt();
    }

    class Node {
        int x;
        int y;
        int cost;
        int direction;

        public Node(int x, int y, int cost, int direction) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.direction = direction;
        }
    }

}
