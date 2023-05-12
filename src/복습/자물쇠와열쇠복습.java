package 복습;

import java.util.ArrayList;
import java.util.List;

public class 자물쇠와열쇠복습 {

    public static void main(String[] args) {
        자물쇠와열쇠_Solution solution = new 자물쇠와열쇠_Solution();
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println("solution.solution(key, lock) = " + solution.solution(key, lock));
    }
}

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
                for (int[][] k : keys) {
                    setNewKeyToNewLock(i, j, k);

                    boolean openLock = checkOpenLock();
                    if (openLock) {
                        return true;
                    }

                    for (int x = 0; x < M; x++) {
                        for (int y = 0; y < M; y++) {
                            newLock[i + x][j + y] = 0;
                        }
                    }

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
