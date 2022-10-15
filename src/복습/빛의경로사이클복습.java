package 복습;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 빛의경로사이클복습 {

    public static void main(String[] args) {

        String[] grid = {"SL", "LR"};
        빛의경로사이클복습_Solution solution = new 빛의경로사이클복습_Solution();
        int[] result = solution.solution(grid);
        for (int i : result) {
            System.out.print("," + i);
        }
    }
}

class 빛의경로사이클복습_Solution {
    int N;
    int M;

    // 우, 하, 왼, 상
    // 들어온 기준으로는 왼, 상, 우, 하
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    public int[] solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();

        Node[][] nodes = new Node[N][M];
        initNodes(nodes, grid);
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int x = i;
                int y = j;
                for (int index = 0; index < 4; index++) {
                    int idx = index;
                    x = (x + dx[idx]) % N;
                    x = x < 0 ? N - 1 : x;
                    y = (y + dy[idx]) % M;
                    y = y < 0 ? M - 1 : y;
                    int count = 0;

                    while (nodes[x][y].validateMove(idx)) {
                        Node node = nodes[x][y];
                        node.addComeIn(idx);
                        count++;
                        // 왼상우하 // 우하왼상
                        if (node.type != CycleType.S) {
                            if (idx == 0) {
                                idx = node.type == CycleType.L ? 3 : 1;
                            }
                            else if (idx == 1) {
                                idx = node.type == CycleType.L ? 0 : 2;
                            }
                            else if (idx == 2) {
                                idx = node.type == CycleType.L ? 1 : 3;
                            }
                            else if (idx == 3) {
                                idx = node.type == CycleType.L ? 2 : 0;
                            }
                        }


                        x = (x + dx[idx]) % N;
                        x = x < 0 ? N - 1 : x;
                        y = (y + dy[idx]) % M;
                        y = y < 0 ? M - 1 : y;
                    }

                    if (count != 0) {
                        result.add(count);
                    }
                }
            }
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        Arrays.sort(answer);
        return answer;
    }

    private void initNodes(Node[][] nodes, String[] grid) {
        for (int i = 0; i < N; i++) {
            String[] g = grid[i].split("");
            for (int j = 0; j < M; j++) {
                Node node = new Node(g[j], i, j);
                nodes[i][j] = node;
            }
        }
    }
}

class Node {
    CycleType type;
    int x;
    int y;
    int up;
    int down;
    int left;
    int right;

    public Node(String type, int x, int y) {
        this.type = CycleType.valueOf(type);
        this.x = x;
        this.y = y;
        this.up = 0;
        this.down = 0;
        this.left = 0;
        this.right = 0;
    }

    public boolean validateMove(int idx) {
        if (idx == 0 && left == 1) {
            return false;
        }

        if (idx == 1 && up == 1) {
            return false;
        }

        if (idx == 2 && right == 1) {
            return false;
        }

        if (idx == 3 && down == 1) {
            return false;
        }

        return true;
    }

    public void addComeIn(int idx) {
        if (idx == 0) {
            left = 1;
        }

        if (idx == 1) {
            up = 1;
        }

        if (idx == 2) {
            right = 1;
        }

        if (idx == 3) {
            down = 1;
        }
    }
}

enum CycleType {
    S, L, R,
}
