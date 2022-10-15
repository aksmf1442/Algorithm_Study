package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 빛의경로사이클 {

    public static void main(String[] args) {
        빛의경로사이클_Solution solution = new 빛의경로사이클_Solution();
        String[] grid = {"S", "S"};
        int[] result = solution.solution(grid);
        for (int i : result) {
            System.out.print("," + i);
        }
    }
}

/**
 * goal: 주어진 격자를 통해 만들어지는 빛의 경로 사이클의 모든 길이 구하기
 */

/**
 * 제한사항
 * 1. ≤ grid의 길이 ≤ 500
 * 2. ≤ grid의 각 문자열의 길이 ≤ 500
 * 3. grid의 모든 문자열의 길이는 서로 같습니다.
 * 4. grid의 모든 문자열은 'L', 'R', 'S'로 이루어져 있습니다.
 */

/**
 * 알아야 할 것
 * 1. 빛이 "S"가 써진 칸에 도달한 경우, 직진합니다.
 * 2. 빛이 "L"이 써진 칸에 도달한 경우, 좌회전을 합니다.
 * 3. 빛이 "R"이 써진 칸에 도달한 경우, 우회전을 합니다.
 * 4. 빛이 격자의 끝을 넘어갈 경우, 반대쪽 끝으로 다시 돌아옵니다.
 *    예를 들어, 빛이 1행에서 행이 줄어드는 방향으로 이동할 경우, 같은 열의 반대쪽 끝 행으로 다시 돌아옵니다.
 */

/**
 * 핵심 아이디어: 어떤 한 노드가 있을 때 상하좌우 중 한 곳이라도 두 번 방문했을 때 사이클이 끝난다는 아이디어 활용
 */

class 빛의경로사이클_Solution {
    int N;
    int M;

    // 아래, 오른쪽, 위쪽, 왼쪽
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public int[] solution(String[] grid) {
        List<Integer> result = new ArrayList<>();
        N = grid.length;
        M = grid[0].length();

        Node[][] nodes = new Node[N][M];
        initNodes(grid, nodes);

        // 현재 노드가 S인 경우
        // 위에서 진입 시: idx = 0, 왼쪽에서 진입 시: idx = 1, 아래에서 진입 시: idx = 2, 우측에서 진입 시: idx = 3
        // 현재 노드가 R인 경우
        // 위에서 진입 시: idx = 3, 왼쪽에서 진입 시: idx = 0, 아래에서 진입 시: idx = 1, 우측에서 진입 시: idx = 2
        // 현재 노드가 L인 경우
        // 위에서 진입 시: idx = 1, 왼쪽에서 진입 시: idx = 2, 아래에서 진입 시: idx = 3, 우측에서 진입 시: idx = 0
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int index = 0; index < 4; index++) {
                    int idx = index;
                    int[] xy = findXY(i, j, idx);
                    int x = xy[0];
                    int y = xy[1];
                    int count = 0;

                    while (nodes[x][y].validatePossibleMove(idx)) {
                        CycleType type = nodes[x][y].type;
                        nodes[x][y].checkComeIn(idx);
                        count++;

                        idx = findDirection(type, idx);
                        xy = findXY(x, y, idx);
                        x = xy[0];
                        y = xy[1];
                    }

                    if (count > 0) {
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

    private int[] findXY(int x, int y, int idx) {
        x = (x + dx[idx]) % N;
        x = x >= 0 ? x : N - 1;
        y = (y + dy[idx]) % M;
        y = y >= 0 ? y : M - 1;

        return new int[]{x, y};
    }

    private int findDirection(CycleType type, int idx) {
        if (type != CycleType.S) {
            // 위쪽에서 진입
            if (idx == 0) {
                idx = type != CycleType.R ? 3 : 1;
            }
            // 왼쪽에서 진입
            else if (idx == 1) {
                idx = type != CycleType.R ? 0 : 2;
            }
            // 아래쪽에서 진입
            else if (idx == 2) {
                idx = type != CycleType.R ? 1 : 3;
            }
            // 오른쪽에서 진입
            else if (idx == 3) {
                idx = type != CycleType.R ? 2 : 0;
            }
        }
        return idx;
    }


    private void initNodes(String[] grid, Node[][] nodes) {
        for (int i = 0; i < grid.length; i++) {
            String[] gridArr = grid[i].split("");
            for (int j = 0; j < gridArr.length; j++) {
                nodes[i][j] = new Node(gridArr[j]);
            }
        }
    }
}


class Node {
    CycleType type;
    int up;
    int down;
    int right;
    int left;

    public Node(String type) {
        this.type = CycleType.valueOf(type);
        this.up = 0;
        this.down = 0;
        this.right = 0;
        this.left = 0;
    }

    public boolean validatePossibleMove(int idx) {
        // idx가 0은 아래로 이동하기 때문에 들어오는 입장에선 위쪽
        if (idx == 0 && up == 1) return false;

        // idx가 1은 오른쪽으로 이동하기 때문에 들어오는 입장에선 왼쪽
        if (idx == 1 && left == 1) return false;

        // idx가 2은 위로 이동하기 때문에 들어오는 입장에선 아래쪽
        if (idx == 2 && down == 1) return false;

        // idx가 3은 왼쪽으로 이동하기 때문에 들어오는 입장에선 오른쪽
        if (idx == 3 && right == 1) return false;

        return true;
    }

    public void checkComeIn(int idx) {
        up = idx == 0 ? 1 : up;
        left = idx == 1 ? 1 : left;
        down = idx == 2 ? 1 : down;
        right = idx == 3 ? 1 : right;
    }
}

enum CycleType {
    L, R, S
}
