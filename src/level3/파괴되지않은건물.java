package level3;

/**
 * goal: 내구도가 0이하가 된 건물의 개수 반
 * <p>
 * 알아야 할 것 1. skill은 [type, r1, c1, r2, c2, degree] 형태 2. type이 1일 경우 적의 공격, 2일 경우 회복 3. (r1, c1)부터
 * (r2, c2)까지 공격하거나 회복
 */

/**
 * goal: 내구도가 0이하가 된 건물의 개수 반
 */

/**
 * 알아야 할 것
 * 1. skill은 [type, r1, c1, r2, c2, degree] 형태
 * 2. type이 1일 경우 적의 공격, 2일 경우 회복
 * 3. (r1, c1)부터 (r2, c2)까지 공격하거나 회복
 */

/**
 * 풀이 방법 V1
 * 1. 이중 for문으로 직접 값 변경
 * -> 이렇게 하며 시간 복잡도가 250000(skill 길이) * 1000(N) * 1000(M) = 250000000000 2500억이 되어서 실패
 */

import java.util.Arrays;

/**
 * 풀이 방법 V2
 * 1. skill 따로 먼저 계산을 해서 한 번에 넣기.
 */

public class 파괴되지않은건물 {

    public static void main(String[] args) {
        파괴되지않은건물_Solution solution = new 파괴되지않은건물_Solution();
//        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
//        int[][] skill = {{1, 0, 0, 3, 4, 4}, {
//            1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        int[][] board = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] skill = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};

        System.out.println("solution.solution(board, skill) = " + solution.solution(board, skill));
    }
}

class 파괴되지않은건물_Solution {

    //V1
//    public int solution(int[][] board, int[][] skill) {
//        int answer = 0;
//
//        for (int[] s : skill) {
//            Skill sk = new Skill(s[0], s[1], s[2], s[3], s[4], s[5]);
//
//            for (int x = sk.x1; x <= sk.x2; x++) {
//                for (int y = sk.y1; y <= sk.y2; y++) {
//                    if (sk.type == Type.ATTACK) {
//                        board[x][y] -= sk.weight;
//                    }
//
//                    if (sk.type == Type.RECOVERY) {
//                        board[x][y] += sk.weight;
//                    }
//                }
//            }
//        }
//
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                if (board[i][j] > 0) {
//                    answer += 1;
//                }
//            }
//        }
//        return answer;
//    }

    // V2 - skill을 다 더하고 한 번에 값 넣기.(누적합)
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] sumSkill = new int[board.length + 1][board[0].length + 1];
        initSumSkill(sumSkill);

        for (int[] s : skill) {
            Skill sk = new Skill(s[0], s[1], s[2], s[3], s[4], s[5]);

            if (sk.skillType == SkillType.ATTACK) {
                sumSkill[sk.x1][sk.y1] -= sk.weight;
                sumSkill[sk.x2 + 1][sk.y2 + 1] -= sk.weight;
                sumSkill[sk.x1][sk.y2 + 1] += sk.weight;
                sumSkill[sk.x2 + 1][sk.y1] += sk.weight;
            }

            if (sk.skillType == SkillType.RECOVERY) {
                sumSkill[sk.x1][sk.y1] += sk.weight;
                sumSkill[sk.x2 + 1][sk.y2 + 1] += sk.weight;
                sumSkill[sk.x1][sk.y2 + 1] -= sk.weight;
                sumSkill[sk.x2 + 1][sk.y1] -= sk.weight;
            }
        }

        accumulateSumSkill(sumSkill);
        calculateBoardWithSumSkill(board, sumSkill);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > 0) {
                    answer += 1;
                }
            }
        }

        return answer;
    }

    private void calculateBoardWithSumSkill(int[][] board, int[][] sumSkill) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] += sumSkill[i][j];
            }
        }
    }

    // 누적합 구하기
    private void accumulateSumSkill(int[][] sumSkill) {
        // 좌우로 먼저 더하기
        for (int i = 0; i < sumSkill.length; i++) {
            for (int j = 0; j < sumSkill[i].length - 1; j++) {
                sumSkill[i][j + 1] += sumSkill[i][j];
            }
        }

        //상하로 더하기
        for (int i = 0; i < sumSkill.length - 1; i++) {
            for (int j = 0; j < sumSkill[i].length; j++) {
                sumSkill[i + 1][j] += sumSkill[i][j];
            }
        }
    }

    private void initSumSkill(int[][] sumSkill) {
        for (int i = 0; i < sumSkill.length; i++) {
            Arrays.fill(sumSkill[i], 0);
        }
    }
}

class Skill {
    SkillType skillType;
    int x1;
    int y1;
    int x2;
    int y2;
    int weight;

    public Skill(int skillType, int x1, int y1, int x2, int y2, int weight) {
        this.skillType = skillType == 1 ? SkillType.ATTACK : SkillType.RECOVERY;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.weight = weight;
    }
}

enum SkillType {
    ATTACK, RECOVERY
}
