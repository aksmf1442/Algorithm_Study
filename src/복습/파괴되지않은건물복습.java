package 복습;

public class 파괴되지않은건물복습 {

    public static void main(String[] args) {
        파괴되지않은건물_Solution solution = new 파괴되지않은건물_Solution();
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

        addY(skills);

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

