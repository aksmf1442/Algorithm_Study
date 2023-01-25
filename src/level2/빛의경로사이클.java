package level2;

import java.util.ArrayList;
import java.util.List;

public class 빛의경로사이클 {

    public static void main(String[] args) {
        빛의경로사이클_Solution solution = new 빛의경로사이클_Solution();
        String[] grid = {"SL", "LR"};
        int[] result = solution.solution(grid);
        for (int i : result) {
            System.out.print("," + i);
        }
    }
}


class 빛의경로사이클_Solution {

    // 상, 우, 하, 좌
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    public int[] solution(String[] grid) {
        List<Integer> answer = new ArrayList<>();
        Circle[][] circles = new Circle[grid.length][grid[0].length()];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                circles[i][j] = new Circle(grid[i].substring(j, j + 1));
            }
        }


        for (int i = 0; i < circles.length; i++) {
            for (int j = 0; j < circles[i].length; j++) {
                for (int z = 0; z < 4; z++) {
                    if (circles[i][j].check[z]) {
                        continue;
                    }

                    int moveCount = 0;
                    int x = i;
                    int y = j;
                    int idx = z;

                    while (!circles[x][y].check[idx]) {
                        moveCount++;

                        Circle current = circles[x][y];
                        current.check[idx] = true;

                        x = x + dx[idx] < 0 ? circles.length - 1 : (x + dx[idx]) % circles.length;
                        y = y + dy[idx] < 0 ? circles[0].length - 1 : (y + dy[idx]) % circles[0].length;

                        Circle next = circles[x][y];

                        // 아래에서 옴(위로 이동)
                        if (idx == 0) {
                            if (next.direction.equals("L")) {
                                idx = 3;
                            }

                            if (next.direction.equals("R")) {
                                idx = 1;
                            }
                            continue;
                        }

                        // 왼쪽에서 옴(오른쪽으로 이동)
                        if (idx == 1) {
                            if (next.direction.equals("L")) {
                                idx = 0;
                            }

                            if (next.direction.equals("R")) {
                                idx = 2;
                            }
                            continue;
                        }

                        // 위에서 옴(아래로 이동)
                        if (idx == 2) {
                            if (next.direction.equals("L")) {
                                idx = 1;
                            }

                            if (next.direction.equals("R")) {
                                idx = 3;
                            }
                            continue;
                        }

                        // 오른쪽에서 옴(왼쪽으로 이동)
                        if (idx == 3) {
                            if (next.direction.equals("L")) {
                                idx = 2;
                            }

                            if (next.direction.equals("R")) {
                                idx = 0;
                            }
                        }
                    }

                    answer.add(moveCount);
                }
            }
        }
        answer.sort((a, b) -> a - b);

        return answer.stream().mapToInt(a -> a).toArray();
    }

    private int move(Circle[][] circles, int x, int y, int idx, int count) {
        Circle current = circles[x][y];

        if (current.check[idx]) {
            return count;
        }
        current.check[idx] = true;

        x = x + dx[idx] < 0 ? circles.length - 1 : (x + dx[idx]) % circles.length;
        y = y + dy[idx] < 0 ? circles[0].length - 1 : (y + dy[idx]) % circles[0].length;


        Circle next = circles[x][y];

        // 아래에서 옴(위로 이동)
        if (idx == 0) {
            if (next.direction.equals("S")) {
                return move(circles, x, y, 0, count + 1);
            }

            if (next.direction.equals("L")) {
                return move(circles, x, y, 3, count + 1);
            }

            if (next.direction.equals("R")) {
                return move(circles, x, y, 1, count + 1);
            }
        }

        // 왼쪽에서 옴(오른쪽으로 이동)
        if (idx == 1) {
            if (next.direction.equals("S")) {
                return move(circles, x, y, 1, count + 1);
            }

            if (next.direction.equals("L")) {
                return move(circles, x, y, 0, count + 1);
            }

            if (next.direction.equals("R")) {
                return move(circles, x, y, 2, count + 1);
            }
        }

        // 위에서 옴(아래로 이동)
        if (idx == 2) {
            if (next.direction.equals("S")) {
                return move(circles, x, y, 2, count + 1);
            }

            if (next.direction.equals("L")) {
                return move(circles, x, y, 1, count + 1);
            }

            if (next.direction.equals("R")) {
                return move(circles, x, y, 3, count + 1);
            }
        }

        // 오른쪽에서 옴(왼쪽으로 이동)
        if (idx == 3) {
            if (next.direction.equals("S")) {
                return move(circles, x, y, 3, count + 1);
            }

            if (next.direction.equals("L")) {
                return move(circles, x, y, 2, count + 1);
            }

            if (next.direction.equals("R")) {
                return move(circles, x, y, 0, count + 1);
            }
        }

        return move(circles, x, y, idx, count);
    }

    class Circle {
        boolean[] check = new boolean[4];
        String direction;

        public Circle(String direction) {
            this.direction = direction;
        }
    }

}
