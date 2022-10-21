package level3;

import java.util.ArrayList;
import java.util.List;

public class 자물쇠와열쇠 {

    public static void main(String[] args) {
        자물쇠와열쇠_Solution solution = new 자물쇠와열쇠_Solution();
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println("solution.solution(key, lock) = " + solution.solution(key, lock));
    }
}

/**
 * goal: 자물쇠의 홈 부분을 키로 채울 수 있는지 확인
 */

/**
 * 풀이 방법
 * 1. 상하좌우, 모서리까지 키의 크기 만큼 자물쇠의 크기를 키운다.
 * 2. 처음부터 키를 집어넣고 완전탐색을 하면서 가능한지 확인
 * 3. 가능하다면 true, 불가능하다면 false 반환
 */

class 자물쇠와열쇠_Solution {

    int N; // 자물쇠 크기
    int M; // 키의 크기
    int[][] newLock;

    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        newLock = new int[N + (M * 2)][N + (M * 2)];

        // new Lock 초기화
        initNewLock(lock);

        // 키의 모든 방향 keys에 넣어두기
        List<int[][]> keys = new ArrayList<>();
        initKeys(key, keys);

        for (int i = 0; i < N + M; i++) {
            for (int j = 0; j < N + M; j++) {
                // 키의 모든 방향 확인
                for (int[][] k : keys) {
                    // new Lock에 key 대입해보기.
                    setNewKeyToNewLock(i, j, k);

                    // 현재 키로 열렸는지 확인하고 열렸으면 true 리턴
                    boolean openLock = checkOpenLock();
                    if (openLock) {
                        return true;
                    }

                    // key 대조해본 부분 초기화
                    for (int x = 0; x < M; x++) {
                        for (int y = 0; y < M; y++) {
                            newLock[i + x][j + y] = 0;
                        }
                    }

                    // newLock 초기화
                    initNewLock(lock);
                }
            }
        }

        return false;
    }

    private boolean checkOpenLock() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (newLock[x + M][y + M] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setNewKeyToNewLock(int i, int j, int[][] k) {
        for (int x = 0; x < M; x++) {
            for (int y = 0; y < M; y++) {
                if (newLock[i + x][j + y] == 0 && k[x][y] == 1) {
                    newLock[i + x][j + y] = 1;
                    continue;
                }

                if (newLock[i + x][j + y] == 1 && k[x][y] == 1) {
                    newLock[i + x][j + y] = 0;
                }
            }
        }
    }

    private void initKeys(int[][] key, List<int[][]> keys) {
        for (int turn = 0; turn < 4; turn++) {
            int[][] newKey = new int[M][M];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    newKey[i][j] = key[M - 1 - j][i];
                }
            }

            key = newKey;
            keys.add(newKey);
        }
    }

    private void initNewLock(int[][] lock) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newLock[i + M][j + M] = lock[i][j];
            }
        }
    }
}
