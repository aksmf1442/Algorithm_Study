package level3;

/**
 * goal: 내구도가 1이상인 건물의 수 구하기
 * <p>
 * 알아야 할 것
 * 1. skill은 [type, r1, c1, r2, c2, degree] 형태
 * 2. type이 1일 경우 적의 공격, 2일 경우 회복
 * 3. (r1, c1)부터 (r2, c2)까지 공격하거나 회복
 * <p>
 * 풀이 방법 V1
 * 1. 이중 for문으로 직접 값 변경
 * -> 이렇게 하며 시간 복잡도가 250000(skill 길이) * 1000(N) * 1000(M) = 250000000000 2500억이 되어서 실패
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

/**
 * 풀이 방법 V2
 * 1. skill 따로 먼저 계산을 해서 한 번에 넣기.(부분합)
 */

public class 파괴되지않은건물 {

    public static void main(String[] args) {
        파괴되지않은건물_Solution solution = new 파괴되지않은건물_Solution();
//        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
//        int[][] skill = {{1, 0, 0, 3, 4, 4}, {
//            1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] skill = {{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}};

        System.out.println("solution.solution(board, skill) = " + solution.solution(board, skill));
    }
}

class 파괴되지않은건물_Solution {

    public int solution(int[][] board, int[][] skill) {
        int[][] skills = new int[board.length + 1][board[0].length + 1];

        addSkill(skill, skills);
        addSkillsToBoard(board, skills);
        return findUndestroyedBuilding(board);
    }

    private static void addSkillsToBoard(int[][] board, int[][] skills) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] += skills[i][j];
            }
        }
    }

    private void addSkill(int[][] skill, int[][] skills) {
        //  초기상태     더하기전     +y방향      +x방향
        // 0  0  0    1  0 -1    1  1  0    1  1  0
        // 0  0  0 -> 0  0  0 -> 0  0  0 -> 1  1  0
        // 0  0  0   -1  0  1   -1 -1  0    0  0  0
        for (int[] sk : skill) {
            Skill s = new Skill(sk);
            if (s.type.equals("attack")) {
                skills[s.xStart][s.yStart] -= s.degree;
                skills[s.xStart][s.yEnd + 1] += s.degree;
                skills[s.xEnd + 1][s.yStart] += s.degree;
                skills[s.xEnd + 1][s.yEnd + 1] -= s.degree;
            }

            if (s.type.equals("recovery")) {
                skills[s.xStart][s.yStart] += s.degree;
                skills[s.xStart][s.yEnd + 1] -= s.degree;
                skills[s.xEnd + 1][s.yStart] -= s.degree;
                skills[s.xEnd + 1][s.yEnd + 1] += s.degree;
            }
        }

        // y 방향으로 더하기
        addY(skills);

        //x 방향으로 더하기
        addX(skills);
    }
    
    private void addY(int[][] skills) {
        for (int i = 0; i < skills.length; i++) {
            for (int j = 1; j < skills[i].length; j++) {
                skills[i][j] += skills[i][j - 1];
            }
        }
    }

    private void addX(int[][] skills) {
        for (int i = 1; i < skills.length; i++) {
            for (int j = 0; j < skills[i].length; j++) {
                skills[i][j] += skills[i - 1][j];
            }
        }
    }

    private static int findUndestroyedBuilding(int[][] board) {
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }

    class Skill {
        String type;
        int xStart;
        int xEnd;
        int yStart;
        int yEnd;
        int degree;

        public Skill(int[] skill) {
            this.type = skill[0] == 1 ? "attack" : "recovery";
            this.xStart = skill[1];
            this.xEnd = skill[3];
            this.yStart = skill[2];
            this.yEnd = skill[4];
            this.degree = skill[5];
        }
    }
}

