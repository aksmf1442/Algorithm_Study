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

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        for (int[] s : skill) {
            Skill sk = new Skill(s[0], s[1], s[2], s[3], s[4], s[5]);

            for (int x = sk.x1; x <= sk.x2; x++) {
                for (int y = sk.y1; y <= sk.y2; y++) {
                    if (sk.type == Type.ATTACK) {
                        board[x][y] -= sk.weight;
                    }

                    if (sk.type == Type.RECOVERY) {
                        board[x][y] += sk.weight;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > 0) {
                    answer += 1;
                }
            }
        }
        return answer;
    }
}

class Skill {
    Type type;
    int x1;
    int y1;
    int x2;
    int y2;
    int weight;

    public Skill(int type, int x1, int y1, int x2, int y2, int weight) {
        this.type = type == 1 ? Type.ATTACK : Type.RECOVERY;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.weight = weight;
    }
}

enum Type {
    ATTACK, RECOVERY
}
