package level2;

import java.util.ArrayList;
import java.util.List;

public class 프렌즈4블록 {

    public static void main(String[] args) {
        프렌즈4블록_Solution solution = new 프렌즈4블록_Solution();
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(solution.solution(m, n, board));
    }

}

class 프렌즈4블록_Solution {

    int[] dx = {0, 1, 0, 1};
    int[] dy = {0, 0, 1, 1};
    char[][] map;
    int result = 0;
    int m = 0;
    int n = 0;

    public int solution(int m, int n, String[] board) {
        map = new char[m][n];
        this.m = m;
        this.n = n;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        updateBlocks();

        return result;
    }

    private void updateBlocks() {
        boolean[][] removeXY = new boolean[m][n];
        int cnt = 0;

        // 지울 것들 체크
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (map[i][j] == '0') {
                    continue;
                }

                if (check(i, j)) {
                    for (int z = 0; z < 4; z++) {
                        int nx = dx[z] + i;
                        int ny = dy[z] + j;
                        removeXY[nx][ny] = true;
                    }
                }
            }
        }

        // 갱신
        for (int i = 0; i < n; i++) {
            List<Character> temp = new ArrayList<>();
            for (int j = m - 1; j >= 0; j--) {
                if (removeXY[j][i]) {
                    cnt++;
                    continue;
                }
                temp.add(map[j][i]);
            }
            for (int j = m - 1, k = 0; j >= 0; j--, k++) {
                if (k < temp.size()) {
                    map[j][i] = temp.get(k);
                    continue;
                }
                map[j][i] = '0';
            }
        }
        result += cnt;
        if (cnt > 0) {
            updateBlocks();
        }
    }

    private boolean check(int i, int j) {
        char std = map[i][j];

        for (int z = 0; z < 4; z++) {
            int nx = dx[z] + i;
            int ny = dy[z] + j;
            if (map[nx][ny] != std) {
                return false;
            }
        }
        return true;
    }
}
